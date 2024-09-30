package rikkey.academy.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "duong123";
    public  static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public  static void closeConnection(Connection conn) {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        System.out.println(ConnectDB.getConnection());
    }
}
