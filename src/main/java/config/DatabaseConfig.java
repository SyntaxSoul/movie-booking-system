package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String url = "jdbc:mysql://localhost:3306/movie_booking";
    private static final String user = "root";
    private static final String password = "root";

    private DatabaseConfig() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
