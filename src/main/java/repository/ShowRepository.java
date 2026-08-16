package repository;

import context.DbContext;
import enums.ShowStatus;
import model.Show;

import java.sql.*;
import java.time.LocalDateTime;

public class ShowRepository {

    public Show save(Show show) throws SQLException {
        String sql = """
                INSERT INTO show(
                movie_id,
                screen_id,
                start_time,
                end_time,
                status,
                price,
                created_at,
                updated_at)
                VALUES(?,?,?,?,?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setLong(index++, Long.parseLong(show.getMovieId()));
            ps.setLong(index++, Long.parseLong(show.getScreenId()));
            ps.setTimestamp(index++, Timestamp.valueOf(show.getStartTime()));
            ps.setTimestamp(index++, Timestamp.valueOf(show.getEndTime()));
            // Either Modify Model or DB and update document
            ps.setString(index++, show.getStatus().name());
            ps.setBigDecimal(index++, show.getPrice());
            ps.setTimestamp(index++, Timestamp.valueOf(show.getCreatedAt()));
            ps.setTimestamp(index++,
                    show.getUpdatedAt() != null
                            ? Timestamp.valueOf(show.getUpdatedAt())
                            : null);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Show.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .movidId(show.getMovieId())
                            .screenId(show.getScreenId())
                            .startTime(show.getStartTime())
                            .endTime(show.getEndTime())
                            .status(show.getStatus())
                            .price(show.getPrice())
                            .createdAt(show.getCreatedAt())
                            .updatedAt(show.getUpdatedAt())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save show");
    }

    public void update(Show show) throws SQLException {
        String sql = """
                UPDATE show SET
                movie_id=?,
                screen_id=?,
                start_time=?,
                end_time=?,
                status=?,
                price=?,
                updated_at=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;
            ps.setLong(index++, Long.parseLong(show.getMovieId()));
            ps.setLong(index++, Long.parseLong(show.getScreenId()));
            ps.setTimestamp(index++, Timestamp.valueOf(show.getStartTime()));
            ps.setTimestamp(index++, Timestamp.valueOf(show.getEndTime()));
            ps.setString(index++, show.getStatus().name());
            ps.setBigDecimal(index++, show.getPrice());
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(index, Long.parseLong(show.getId()));

            ps.executeUpdate();
        }
    }

    public Show findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM show WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Show.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .movidId(String.valueOf(rs.getLong("movie_id")))
                        .screenId(String.valueOf(rs.getLong("screen_id")))
                        .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                        .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                        .status(ShowStatus.valueOf(rs.getString("status")))
                        .price(rs.getBigDecimal("price"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("Failed to fetch show id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM show WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
