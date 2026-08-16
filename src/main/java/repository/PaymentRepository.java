package repository;

import context.DbContext;
import enums.PaymentStatus;
import model.Payment;

import java.sql.*;

public class PaymentRepository {
    public Payment save(Payment payment) throws SQLException {
        String sql = """
                INSERT INTO payment(
                currency,
                transaction_id,
                booking_id,
                amount,
                paid_at,
                gateway_response,
                status)
                VALUES(?,?,?,?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setString(index++, payment.getCurrency().name());
            ps.setLong(index++, Long.parseLong(payment.getTransactionId()));
            ps.setLong(index++, Long.parseLong(payment.getBookingId()));
            ps.setBigDecimal(index++, payment.getAmount());
            ps.setTimestamp(index++, Timestamp.valueOf(payment.getPaidAt()));
            ps.setString(index++, payment.getGatewayResponse());
            ps.setString(index, payment.getStatus().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Payment.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .currency(payment.getCurrency())
                            .bookingId(payment.getBookingId())
                            .amount(payment.getAmount())
                            .paidAt(payment.getPaidAt())
                            .gatewayResponse(payment.getGatewayResponse())
                            .status(payment.getStatus())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save payment");
    }

    public void update(Payment payment) throws SQLException {
        String sql = """
                UPDATE payment SET
                currency=?,
                transaction_id=?,
                booking_id=?,
                amount=?,
                gateway_response=?,
                status=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;
            ps.setString(index++, payment.getCurrency().name());
            ps.setString(index++, payment.getTransactionId());
            ps.setLong(index++, Long.parseLong(payment.getBookingId()));
            ps.setBigDecimal(index++, payment.getAmount());
            ps.setString(index++, payment.getGatewayResponse());
            ps.setString(index++, payment.getStatus().name());
            ps.setLong(index, Long.parseLong(payment.getId()));

            ps.executeUpdate();
        }
    }

    public Payment findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM payment WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            ps.setLong(1, Long.parseLong(id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Payment.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .transactionId(rs.getString("transaction_id"))
                        .bookingId(String.valueOf(rs.getLong("booking_id")))
                        .amount(rs.getBigDecimal("amount"))
                        .paidAt(rs.getTimestamp("paid_at").toLocalDateTime())
                        .gatewayResponse(rs.getString("gateway_response"))
                        .status(PaymentStatus.valueOf(rs.getString("status")))
                        .build();
            }
        }
        throw new SQLException("Failed to fetch payment id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM payment WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
