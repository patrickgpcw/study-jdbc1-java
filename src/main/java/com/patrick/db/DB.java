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

            ps.setString(1, "Flavinha Pessanha");
            ps.setString(2, "flavinha@pessanha.com");
            ps.setDate(3, Date.valueOf(LocalDate.of(1990, 02, 01)));
            ps.setDouble(4, 1000000.0);
            ps.setInt(5, 4);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException("Error inserting new seller", e);
        } finally {
            closeConnection();
        }
    }

    public static void insetNewDepartment() {
        String sql = "INSERT INTO department "
                + "(Name)"
                + "VALUES(?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, "D1");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error inserting new department: ", e);
        }

    }

    public static void updateBaseSalarySeller() {
        String sql = "UPDATE seller "
                + "SET baseSalary = baseSalary + ? "
                + "WHERE "
                + "(Name = ?) ";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, 9);
            ps.setString(2, "Flavinha Pessanha");
            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new DbException("Error updating sellers' base salary", e);
        } finally {
            closeConnection();
        }
    }

    public static void removeDepartment() {
        String sql = "DELETE FROM department "
                + "WHERE "
                + "(Id = ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, 5);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public static void transactionBaseSalaryFromSeller() {
        try (Statement s = getConnection().createStatement()) {

            conn.setAutoCommit(false);

            int rows1 = s.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

//            if (true) {
//                throw new SQLException("Fake Error");
//            }

            int rows2 = s.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();
            System.out.println("rows1 = " + rows1);
            System.out.println("rows2 = " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: ", e);
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! ", e1);
            }
        }
    }

        private static Properties loadProperties () {

            try (FileInputStream fs = new FileInputStream("db.properties")) {

                Properties props = new Properties();
                props.load(fs);
                return props;

            } catch (IOException e) {
                throw new DbException("Error to load properties file: ", e);
            }
        }
    }
