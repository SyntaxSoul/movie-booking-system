package repository;

import context.DbContext;
import model.Feedback;

import java.sql.*;

public class FeedbackRepository {
    public Feedback save(Feedback feedback) throws SQLException {
        String sql = """
                INSERT INTO feedback(
                ticket_id,
                theatre_rating,
                movie_rating,
                comment,
                created_at)
                VALUES(?,?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setLong(index++, Long.parseLong(feedback.getTicketId()));
            ps.setInt(index++, feedback.getTheatreRating());
            ps.setInt(index++, feedback.getMovieRating());
            ps.setString(index++, feedback.getComment());
            ps.setTimestamp(index, Timestamp.valueOf(feedback.getCreatedAt()));

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Feedback.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .ticketId(feedback.getTicketId())
                            .theatreRating(feedback.getTheatreRating())
                            .movieRating(feedback.getMovieRating())
                            .comment(feedback.getComment())
                            .createdAt(feedback.getCreatedAt())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save feedback");
    }

    public void update(Feedback feedback) throws SQLException {
        String sql = """
                UPDATE feedback SET
                ticket_id=?,
                theatre_rating=?,
                movie_rating=?,
                comment=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            int index = 1;
            ps.setLong(index++, Long.parseLong(feedback.getTicketId()));
            ps.setInt(index++, feedback.getTheatreRating());
            ps.setInt(index++, feedback.getMovieRating());
            ps.setString(index++, feedback.getComment());
            ps.setLong(index, Long.parseLong(feedback.getId()));

            ps.executeUpdate();
        }
    }

    public Feedback findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM feedback WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Feedback.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .ticketId(String.valueOf(rs.getLong("ticket_id")))
                        .theatreRating(rs.getInt("theatre_rating"))
                        .movieRating(rs.getInt("movie_rating"))
                        .comment(rs.getString("comment"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("Failed to fetch feedback id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM feedback WHERE id=?
                """;
        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}