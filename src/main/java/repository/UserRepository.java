package repository;

import config.DatabaseConfig;
import enums.Gender;
import enums.UserStatus;
import enums.UserType;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;

public class UserRepository {

    public User save(User user) throws SQLException {

        String sql = """
                INSERT INTO user
                (
                name,
                user_type,
                email,
                phone,
                gender,
                dob,
                status,
                password,
                created_at,
                updated_at,
                last_login)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            int index = 1;
            ps.setString(index++, user.getName());
            ps.setString(index++, user.getUserType().name());
            ps.setString(index++, user.getEmail());
            ps.setString(index++, user.getPhone());
            ps.setString(index++, user.getGender().name());
            ps.setDate(index++, Date.valueOf(user.getDob()));
            ps.setString(index++, user.getStatus().name());
            ps.setString(index++, user.getPassword());
            ps.setTimestamp(index++, Timestamp.valueOf(user.getCreatedAt()));
            ps.setTimestamp(index++,
                    user.getUpdatedAt() != null
                            ? Timestamp.valueOf(user.getUpdatedAt())
                            : null);
            ps.setTimestamp(index,
                    user.getLastLogin() != null
                            ? Timestamp.valueOf(user.getLastLogin())
                            : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return User.builder()
                            .id(String.valueOf(rs.getLong(1)))
                            .name(user.getName())
                            .userType(user.getUserType())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .gender(user.getGender())
                            .dob(user.getDob())
                            .status(user.getStatus())
                            .password(user.getPassword())
                            .createdAt(user.getCreatedAt())
                            .updatedAt(user.getUpdatedAt())
                            .lastLogin(user.getLastLogin())
                            .build();
                }
            }
        }

        throw new SQLException("Failed to retrieve generated user ID.");
    }

    public void update(User user) throws SQLException {
        String sql = """
                UPDATE user SET
                name=?,
                user_type=?,
                email=?,
                phone=?,
                gender=?,
                dob=?,
                status=?,
                password=?,
                updated_at=?,
                last_login=?
                WHERE
                id=?
                """;

        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            int index = 1;

            ps.setString(index++, user.getName());
            ps.setString(index++, user.getUserType().name());
            ps.setString(index++, user.getEmail());
            ps.setString(index++, user.getPhone());
            ps.setString(index++, user.getGender().name());
            ps.setDate(index++, Date.valueOf(user.getDob()));
            ps.setString(index++, user.getStatus().name());
            ps.setString(index++, user.getPassword());
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(index++,
                    user.getLastLogin() != null
                            ? Timestamp.valueOf(user.getLastLogin())
                            : null);
            ps.setLong(index, Long.parseLong(user.getId()));

            ps.executeUpdate();
        }
    }

    public User findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM user  WHERE id=?;
                """;

        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, Long.parseLong(id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return User.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .name(rs.getString("name"))
                        .userType(UserType.valueOf(rs.getString("user_type")))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .gender(Gender.valueOf(rs.getString("gender")))
                        .dob(rs.getDate("dob").toLocalDate())
                        .status(UserStatus.valueOf(rs.getString("status")))
                        .password(rs.getString("password"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .lastLogin(rs.getTimestamp("last_login").toLocalDateTime())
                        .build();
            }
        }
        throw new SQLException("No user found for id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = """
                DELETE FROM user WHERE id=?;
                """;

        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
