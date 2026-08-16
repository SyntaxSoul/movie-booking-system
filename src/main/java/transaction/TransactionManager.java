package transaction;

import config.DatabaseConfig;
import context.DbContext;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    public static void begin() throws SQLException {
        Connection con = DatabaseConfig.getConnection();
        con.setAutoCommit(false);
        DbContext.setConnection(con);
    }

    public static void commit() throws SQLException {
        DbContext.getConnection().commit();
    }

    public static void rollback() throws SQLException {
        DbContext.getConnection().rollback();
    }

    public static void close() throws SQLException {
        try {
            DbContext.getConnection().close();
        } catch (SQLException e) {
            throw new SQLException("Failed to close connection", e);
        } finally {
            DbContext.clear();
        }
    }
}
