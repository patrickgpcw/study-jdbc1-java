package com.patrick.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dbUrl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException("Error opening database connection: ", e);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException("Error opening database connection: ", e);
            } finally {
                conn = null;
            }
        }
    }

    public static void printDepartmentTable() {
        String sql = "SELECT * FROM  department";

        try (
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            throw new DbException("Error when querying departments :", e);
        } finally {
            closeConnection();
        }
    }

    public static void insertNewSeller() {
        String sql = "INSERT INTO seller"
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, "Carl Purple");
            ps.setString(2, "carl@gmail.com");
            ps.setDate(3, Date.valueOf(LocalDate.of(1985, 12, 11)));
            ps.setDouble(4, 3000.0);
            ps.setInt(5, 4);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException("Error inserting new seller", e);
        } finally {
            closeConnection();
        }
    }

    public static void updateBaseSalarySeller() {
        String sql = "UPDATE seller "
                + "SET baseSalary = baseSalary + ? "
                + "WHERE "
                + "(DepartmentId = ?) ";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, 1000.0);
            ps.setInt(2, 4);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new DbException("Error updating sellers' base salary", e);
        } finally {
            closeConnection();
        }
    }

    private static Properties loadProperties() {

        try (FileInputStream fs = new FileInputStream("db.properties")) {

            Properties props = new Properties();
            props.load(fs);
            return props;

        } catch (IOException e) {
            throw new DbException("Error to load properties file: ", e);
        }
    }
}
