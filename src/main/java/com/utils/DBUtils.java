package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtils {

    // JDBC URL for Windows Authentication
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=master;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    // Make sure sqljdbc_auth.dll is in your PATH (for Windows Auth)

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException("DB Connection Failed", e);
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query); // Caller should close connection
        } catch (Exception e) {
            throw new RuntimeException("Query execution failed", e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
