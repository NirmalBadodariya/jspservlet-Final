package dao;


import java.sql.Connection;

import java.sql.DriverManager;

public class adminDBConnection {
    static Connection conn = null;
    public static final String dbURL = "jdbc:mysql://localhost:3306/users_management";
    public static final String dbUname = "root";
    public static final String dbPass = "nirmal15";
    
    protected adminDBConnection() {

    }
    
    public static Connection getadminDBConnection() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(dbURL, dbUname, dbPass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
