import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String ar[]){
        try{
            Connection con= DatabaseConfig.getConnection();
            if(con!=null){
                System.out.println("Connection Successful!: " +con);
            }
        }
        catch (SQLException e){
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
