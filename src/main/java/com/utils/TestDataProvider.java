package com.utils;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "ValidloginData")
    public Object[][] validLogin() throws IOException {

        ExcelUtil ex = new ExcelUtil();
        ex.excelSetup(
                ConfigReader.get("ExcelFilePath"),
                "ValidLogin"
        );

        int rowCount = ex.getRowCount();   // example: 4

        Object[][] data = new Object[rowCount][2];

        // Start from row 1 because row 0 = header
        for (int i = 1; i <= rowCount; i++) {
            data[i - 1][0] = ex.getData("Email", i);
            data[i - 1][1] = ex.getData("Password", i);
        }

        ex.close();
        return data;
    }

    @DataProvider(name = "InvalidloginData")
    public Object[][] inValidLogin() throws IOException {

        ExcelUtil ex = new ExcelUtil();
        ex.excelSetup(
                ConfigReader.get("ExcelFilePath"),
                "InvalidLogin"
        );

        int rowCount = ex.getRowCount();   // example: 4

        Object[][] data = new Object[rowCount][2];

        // Start from row 1 because row 0 = header
        for (int i = 1; i <= rowCount; i++) {
            data[i - 1][0] = ex.getData("Email", i);
            data[i - 1][1] = ex.getData("Password", i);
        }

        ex.close();
        return data;
    }

    @DataProvider(name = "DBValidData")
    public static Object[][] DBValidData(ITestContext context) {
        // Get query parameter from testng.xml
        //String query = context.getCurrentXmlTest().getParameter("query");

        String query = "SELECT * FROM dbo.LoginTestData WHERE Status='Valid'";

        if (query == null || query.isEmpty()) {
            throw new RuntimeException("SQL query parameter is missing in testng.xml!");
        }

        List<Object[]> data = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection(); // Windows Auth connection
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                data.add(new Object[]{email, password});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            DBUtils.closeConnection(con);
        }

        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "DBInValidData")
    public static Object[][] DBInValidData(ITestContext context) {

        String query = "SELECT * FROM dbo.LoginTestData WHERE Status='inValid'";

        if (query == null || query.isEmpty()) {
            throw new RuntimeException("SQL query parameter is missing in testng.xml!");
        }

        List<Object[]> data = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection(); // Windows Auth connection
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                data.add(new Object[]{email, password});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            DBUtils.closeConnection(con);
        }

        return data.toArray(new Object[0][]);
    }
}



