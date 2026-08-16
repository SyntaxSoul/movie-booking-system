package context;

import java.sql.Connection;

public class DbContext {
    private static final ThreadLocal<Connection> connectionHolder=new ThreadLocal<>();

    public static void setConnection(Connection connection){
        connectionHolder.set(connection);
    }

    public static Connection getConnection(){
        Connection con=connectionHolder.get();
        if(con==null){
            throw new IllegalStateException("No active connection bound to current thread.");
        }
        return con;
    }

    public static void clear(){
        connectionHolder.remove();
    }
}