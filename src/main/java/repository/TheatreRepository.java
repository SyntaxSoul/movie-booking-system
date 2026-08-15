package repository;

import config.DatabaseConfig;
import enums.TheatreStatus;
import enums.TheatreVerifiedStatus;
import model.Theatre;

import java.sql.*;
import java.time.LocalDateTime;

public class TheatreRepository {
    public Theatre save(Theatre theatre) throws SQLException {
        String sql = """
                INSERT INTO
                theatre(
                name,
                address,
                phone,
                email,
                owner_id,
                verified_status,
                license_number,
                created_at,
                updated_at,
                status)
                VALUES(?,?,?,?,?,?,?,?,?,?);
                """;

        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            ps.setString(index++, theatre.getName());
            ps.setString(index++, theatre.getAddress());
            ps.setString(index++, theatre.getPhone());
            ps.setString(index++, theatre.getEmail());
            ps.setLong(index++, Long.parseLong(theatre.getOwnerId()));
            ps.setString(index++, theatre.getVerifiedStatus().name());
            ps.setString(index++, theatre.getLicenseNumber());
            ps.setTimestamp(index++, Timestamp.valueOf(theatre.getCreatedAt()));
            ps.setTimestamp(index++, theatre.getUpdateAt() != null
                    ? Timestamp.valueOf(theatre.getUpdateAt())
                    : null
            );
            ps.setString(index++, theatre.getStatus().name());

            ps.executeQuery();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return Theatre.builder()
                        .id(rs.getString("id"))
                        .name(theatre.getName())
                        .address(theatre.getAddress())
                        .phone(theatre.getPhone())
                        .email(theatre.getEmail())
                        .ownerId(theatre.getEmail())
                        .verifiedStatus(theatre.getVerifiedStatus())
                        .licenseNumber(theatre.getLicenseNumber())
                        .createdAt(theatre.getCreatedAt())
                        .updatedAt(theatre.getCreatedAt())
                        .status(theatre.getStatus())
                        .build();
            }
        }
    }

    public void update(Theatre theatre) throws SQLException {
        String sql = """
                UPDATE theatre SET
                name=?,
                address=?,
                phone=?,
                email=?,
                owner_id=?,
                verified_status=?,
                license_number=?,
                created_at=?,
                updated_at=?,
                status=?,
                WHERE id=?
                """;
        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, theatre.getName());
            ps.setString(index++, theatre.getAddress());
            ps.setString(index++, theatre.getPhone());
            ps.setString(index++, theatre.getEmail());
            ps.setLong(index++, Long.parseLong(theatre.getOwnerId()));
            ps.setString(index++, theatre.getVerifiedStatus().name());
            ps.setString(index++, theatre.getLicenseNumber());
            ps.setTimestamp(index++, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(index++, theatre.getStatus().name());
            ps.setLong(index, Long.parseLong(theatre.getId()));

            ps.executeUpdate();

        }
    }

    public Theatre findById(String id) throws SQLException {
        String sql = """
                SELECT * FROM user WHERE id=?;
                """;

        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Theatre.builder()
                        .id(String.valueOf(rs.getLong("id")))
                        .name(rs.getString("name"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .ownerId(String.valueOf(rs.getLong("owner_id")))
                        .verifiedStatus(TheatreVerifiedStatus.valueOf(rs.getString("verified_status")))
                        .licenseNumber(rs.getString("license_number"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .status(TheatreStatus.valueOf(rs.getString("status")))
                        .build();
            }
        }
        throw new SQLException("Failed finding theatre id: " + id);
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?;";

        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, Long.parseLong(id));
            ps.executeQuery();
        }
    }
}
