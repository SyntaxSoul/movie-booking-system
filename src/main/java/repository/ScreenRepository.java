package repository;

import context.DbContext;
import enums.ScreenType;
import model.Screen;

import java.sql.*;
import java.time.LocalDateTime;

public class ScreenRepository {

    public Screen save(Screen screen) throws SQLException {
        String sql = """
                INSERT INTO screen(
                screen_number,
                theatre_id,
                capacity,
                screen_name,
                screen_type,
                created_at,
                updated_at)
                VALUES(?,?,?,?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {


            int index = 1;
            ps.setString(index++, screen.getScreenNumber());
            ps.setLong(index++, Long.parseLong(screen.getTheatreId()));
            ps.setInt(index++, screen.getCapacity());
            ps.setString(index++, screen.getScreenName());
            ps.setString(index++, screen.getScreenType().name());
            ps.setTimestamp(index++, Timestamp.valueOf(screen.getCreatedAt()));
            ps.setTimestamp(index++,
                    screen.getUpdatedAt() != null
                            ? Timestamp.valueOf(screen.getUpdatedAt())
                            : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Screen.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .screenNumber(screen.getScreenNumber())
                            .theatreId(screen.getTheatreId())
                            .capacity(screen.getCapacity())
                            .screenName(screen.getScreenName())
                            .screenType(screen.getScreenType())
                            .createdAt(screen.getCreatedAt())
                            .createdAt(screen.getUpdatedAt())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save screen");
    }

    public void update(Screen screen) throws SQLException {
        String sql = """
                UPDATE screen SET
                screen_number=?,
                theatre_id=?,
                capacity=?,
                screen_name=?,
                screen_type=?,
                updated_at=?
                WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;
            ps.setString(index++, screen.getScreenNumber());
            ps.setLong(index++, Long.parseLong(screen.getTheatreId()));
            ps.setInt(index++, screen.getCapacity());
            ps.setString(index++, screen.getScreenName());
            ps.setString(index++, screen.getScreenType().name());
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(index, Long.parseLong(screen.getId()));

            ps.executeUpdate();
        }
    }

    public Screen findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM screen WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Screen.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .screenNumber(rs.getString("screen_number"))
                        .theatreId(String.valueOf(rs.getLong("theatre_id")))
                        .capacity(rs.getInt("capacity"))
                        .screenName(rs.getString("screen_name"))
                        .screenType(ScreenType.valueOf(rs.getString("screen_type")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("Failed to fetch screen");
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM screen WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
