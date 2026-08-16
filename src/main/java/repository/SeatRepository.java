package repository;

import context.DbContext;
import enums.SeatType;
import model.Seat;

import java.sql.*;
import java.time.LocalDateTime;

public class SeatRepository {

    public Seat save(Seat seat) throws SQLException {
        String sql = """
                INSERT INTO seat(
                seat_number,
                seat_type,
                screen_id,
                created_at,
                updated_at)
                VALUES(?,?,?,?,?)
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setLong(index++, Long.parseLong(seat.getSeatNumber()));
            ps.setString(index++, seat.getSeatType().name());
            ps.setLong(index++, Long.parseLong(seat.getScreenId()));
            ps.setTimestamp(index++, Timestamp.valueOf(seat.getCreatedAt()));
            ps.setTimestamp(index++,
                    seat.getUpdatedAt() != null
                            ? Timestamp.valueOf(seat.getUpdatedAt())
                            : null);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Seat.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .seatNumber(seat.getSeatNumber())
                            .seatType(seat.getSeatType())
                            .screenId(seat.getScreenId())
                            .createdAt(seat.getCreatedAt())
                            .updatedAt(seat.getUpdatedAt())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save seat");
    }

    public void update(Seat seat) throws SQLException {
        String sql = """
                UPDATE seat SET
                seat_number=?,
                seat_type=?,
                screen_id=?,
                updated_at=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            int index = 1;
            ps.setString(index++, seat.getSeatNumber());
            ps.setString(index++, seat.getSeatType().name());
            ps.setLong(index++, Long.parseLong(seat.getScreenId()));
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(index, Long.parseLong(seat.getId()));

            ps.executeUpdate();
        }
    }

    public Seat findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM seat WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Seat.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .seatNumber(rs.getString("seat_number"))
                        .seatType(SeatType.valueOf(rs.getString("seat_type")))
                        .screenId(String.valueOf(rs.getLong("screen_id")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("Failed to fetch seat");
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM seat WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
