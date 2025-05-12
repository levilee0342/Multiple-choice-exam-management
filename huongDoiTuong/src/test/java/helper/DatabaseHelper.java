package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {

    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=ThiTracNghiem;encrypt=true;trustServerCertificate=true";
        
        String username = "sa";
        String password = "1234";
        
        Connection con = DriverManager.getConnection(connectionURL, username, password);
        return con;
    }

}
