package repository;

import context.DbContext;
import enums.Language;
import enums.MovieCertificate;
import enums.MovieGenre;
import model.Movie;

import java.sql.*;
import java.time.LocalDateTime;

public class MovieRepository {

    public Movie save(Movie movie) throws SQLException {
        String sql = """
                INSERT INTO movie(
                title,
                genre,
                language,
                duration_minutes,
                description,
                release_date,
                poster_url,
                trailer_url,
                certificate,
                created_at,
                updated_at
                )
                VALUES(?,?,?,?,?,?,?,?,?,?,?)
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS)) {

            int index = 1;
            ps.setString(index++, movie.getTitle());
            ps.setString(index++, movie.getGenre().name());
            ps.setString(index++, movie.getLanguage().name());
            ps.setInt(index++, movie.getDurationMinutes());
            ps.setString(index++, movie.getDescription());
            ps.setDate(index++, Date.valueOf(movie.getReleaseDate()));
            ps.setString(index++, movie.getPosterUrl());
            ps.setString(index++, movie.getTrailerUrl());
            ps.setString(index++, movie.getCertificate().name());
            ps.setTimestamp(index++, Timestamp.valueOf(movie.getCreatedAt()));
            ps.setTimestamp(index, movie.getUpdatedAt() != null
                    ? Timestamp.valueOf(movie.getUpdatedAt())
                    : null
            );

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return Movie.builder()
                            .id(String.valueOf(rs.getLong("id")))
                            .title(movie.getTitle())
                            .genre(movie.getGenre())
                            .language(movie.getLanguage())
                            .durationMinutes(movie.getDurationMinutes())
                            .description(movie.getDescription())
                            .releaseDate(movie.getReleaseDate())
                            .posterUrl(movie.getPosterUrl())
                            .trailerUrl(movie.getTrailerUrl())
                            .certificate(movie.getCertificate())
                            .createdAt(movie.getCreatedAt())
                            .build();
                }
            }
        }
        throw new SQLException("Failed to save movie");
    }

    public void update(Movie movie) throws SQLException {
        String sql = """
                UPDATE movie SET
                title=?,
                genre=?,
                language=?,
                duration_minutes=?,
                description=?,
                release_date=?,
                poster_url=?,
                trailer_url=?,
                certificate=?,
                updated_at=?
                WHERE
                id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            // CreatedAt should not be updated/modified
            // As this is the update action need to update the updatedAt to current time
            int index = 1;
            ps.setString(index++, movie.getTitle());
            ps.setString(index++, movie.getGenre().name());
            ps.setString(index++, movie.getLanguage().name());
            ps.setInt(index++, movie.getDurationMinutes());
            ps.setString(index++, movie.getDescription());
            ps.setDate(index++, Date.valueOf(movie.getReleaseDate()));
            ps.setString(index++, movie.getPosterUrl());
            ps.setString(index++, movie.getTrailerUrl());
            ps.setString(index++, movie.getCertificate().name());
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(index, Long.parseLong(movie.getId()));
        }
    }

    public Movie findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM movie WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Movie.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .title(rs.getString("title"))
                        .genre(MovieGenre.valueOf(rs.getString("genre")))
                        .language(Language.valueOf(rs.getString("language")))
                        .durationMinutes(rs.getInt("duration_minutes"))
                        .description(rs.getString("description"))
                        .releaseDate(rs.getDate("release_date").toLocalDate())
                        .posterUrl(rs.getString("poster_url"))
                        .trailerUrl(rs.getString("trailer_url"))
                        .certificate(MovieCertificate.valueOf(rs.getString("certificate")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_url").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("Failed to fetch user: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM movie WHERE id=?
                """;

        try (PreparedStatement ps =
                     DbContext.getConnection().prepareStatement(
                             sql)) {

            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();

        }
    }

}
