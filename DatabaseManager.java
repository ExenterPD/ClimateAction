package com.ClimateActions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Climate_Action";
    private static final String USER = "root";
    private static final String PASSWORD = "Shreya123_";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean registerUser(String username, String password, String email) {
        return false;
    }

    public int loginUser(String username, String password) {
        return 0;
    }
}
