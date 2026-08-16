package repository;

import config.DatabaseConfig;
import context.DbContext;
import model.BookedSeat;

import java.sql.*;

public class BookedSeatRepository {
    public BookedSeat save(BookedSeat bookedSeat) throws SQLException {
        String sql = """
                INSERT INTO bookedSeat(
                price,
                seat_id,
                booking_id)
                VALUES(?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setBigDecimal(index++, bookedSeat.getPrice());
            ps.setLong(index++, Long.parseLong(bookedSeat.getSeatId()));
            ps.setLong(index, Long.parseLong(bookedSeat.getBookingId()));

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return BookedSeat.builder()
                            .id(String.valueOf(rs.getLong(1)))
                            .price(bookedSeat.getPrice())
                            .seatId(bookedSeat.getSeatId())
                            .bookingId(bookedSeat.getBookingId())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save booked seat");
    }

    public void update(BookedSeat bookedSeat) throws SQLException {
        String sql = """
                UPDATE bookedSeat SET
                price=?,
                seat_id=?,
                booking_id=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;
            ps.setBigDecimal(index++, bookedSeat.getPrice());
            ps.setLong(index++, Long.parseLong(bookedSeat.getSeatId()));
            ps.setLong(index++, Long.parseLong(bookedSeat.getBookingId()));
            ps.setLong(index, Long.parseLong(bookedSeat.getId()));

            ps.executeUpdate();
        }
    }

    public BookedSeat findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM bookedSeat WHERE id=?
                """;

        try (Connection con =
                     DatabaseConfig.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return BookedSeat.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .price(rs.getBigDecimal("price"))
                        .seatId(String.valueOf(rs.getLong("seat_id")))
                        .bookingId(String.valueOf(rs.getLong("booking_id")))
                        .build();
            }
        }
        throw new SQLException("Failed to fetch bookedSeat id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM bookedSeat WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
