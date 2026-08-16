package repository;

import context.DbContext;
import enums.BookingStatus;
import model.Booking;

import java.sql.*;
import java.time.LocalDateTime;

public class BookingRepository {

    public Booking save(Booking booking) throws SQLException {
        String sql = """
                INSERT INTO booking(
                booking_time,
                total_amount,
                show_id,
                user_id,
                status,
                created_at,
                updated_at)
                VALUES(?,?,?,?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setTimestamp(index++, Timestamp.valueOf(booking.getBookingTime()));
            ps.setBigDecimal(index++, booking.getTotalAmount());
            ps.setLong(index++, Long.parseLong(booking.getShowId()));
            ps.setLong(index++, Long.parseLong(booking.getUserId()));
            ps.setString(index++, booking.getStatus().name());
            ps.setTimestamp(index++, Timestamp.valueOf(booking.getCreatedAt()));
            ps.setTimestamp(index,
                    booking.getUpdatedAt() != null
                            ? Timestamp.valueOf(booking.getUpdatedAt())
                            : null);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return Booking.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .bookingTime(booking.getBookingTime())
                            .totalAmount(booking.getTotalAmount())
                            .showId(booking.getShowId())
                            .userId(booking.getUserId())
                            .status(booking.getStatus())
                            .createdAt(booking.getCreatedAt())
                            .updatedAt(booking.getUpdatedAt())
                            .build();
                }

            }
        }
        throw new SQLException("Failed to save booking");
    }

    public void update(Booking booking) throws SQLException {
        String sql = """
                UPDATE booking SET
                booking_time=?,
                total_amount=?,
                show_id=?,
                user_id=?,
                status=?,
                updated_at=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;

            ps.setTimestamp(index++, Timestamp.valueOf(booking.getBookingTime()));
            ps.setBigDecimal(index++, booking.getTotalAmount());
            ps.setLong(index++, Long.parseLong(booking.getShowId()));
            ps.setLong(index++, Long.parseLong(booking.getUserId()));
            ps.setString(index++, booking.getStatus().name());
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(index, Long.parseLong(booking.getId()));

            ps.executeUpdate();
        }
    }

    public Booking findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM booking WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Booking.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .bookingTime(rs.getTimestamp("booking_time").toLocalDateTime())
                        .totalAmount(rs.getBigDecimal("total_amount"))
                        .showId(String.valueOf(rs.getLong("show_id")))
                        .userId(String.valueOf(rs.getLong("user_id")))
                        .status(BookingStatus.valueOf(rs.getString("status")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("Failed to fetch booking id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM booking WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
