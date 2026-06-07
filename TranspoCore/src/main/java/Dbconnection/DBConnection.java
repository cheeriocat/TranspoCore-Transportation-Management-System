package Dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection conn;

    public static synchronized Connection getConnection() {
        try {
            if (conn == null || conn.isClosed() || !conn.isValid(2)) {
                // Create a new connection if none exists, or if it's closed or invalid
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
        return conn;
    }
}
