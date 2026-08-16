package repository;

import context.DbContext;
import enums.TicketStatus;
import model.Ticket;

import java.sql.*;

public class TicketRepository {
    public Ticket save(Ticket ticket) throws SQLException {
        String sql = """
                INSERT INTO ticket(
                booking_id,
                issued_at,
                status,
                qr_code)
                VALUES(?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setLong(index++, Long.parseLong(ticket.getBookingId()));
            ps.setTimestamp(index++, Timestamp.valueOf(ticket.getIssuedAt()));
            ps.setString(index++, ticket.getStatus().name());
            ps.setString(index, ticket.getQrCode());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Ticket.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .bookingId(ticket.getBookingId())
                            .issuedAt(ticket.getIssuedAt())
                            .status(ticket.getStatus())
                            .qrCode(ticket.getQrCode())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save ticket");
    }

    public void update(Ticket ticket) throws SQLException {
        String sql = """
                UPDATE ticket SET
                booking_id=?,
                issued_at=?,
                status=?,
                qr_code=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;
            ps.setLong(index++, Long.parseLong(ticket.getBookingId()));
            ps.setTimestamp(index++, Timestamp.valueOf(ticket.getIssuedAt()));
            ps.setString(index++, ticket.getStatus().name());
            ps.setString(index++, ticket.getQrCode());
            ps.setLong(index, Long.parseLong(ticket.getId()));

            ps.executeUpdate();
        }
    }

    public Ticket findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM ticket WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Ticket.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .bookingId(String.valueOf(rs.getLong("booking_id")))
                        .issuedAt(rs.getTimestamp("issued_at").toLocalDateTime())
                        .status(TicketStatus.valueOf(rs.getString("status")))
                        .qrCode(rs.getString("qr_code"))
                        .build();
            }
        }
        throw new SQLException("Failed to fetch ticket id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM ticket WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
