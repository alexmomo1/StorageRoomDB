package org.example;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    static void main() {
        String url = "jdbc.sqlite:inventory.db";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);


        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connected to SQLite.");

            createTable(conn);
            insertItem(conn, "Laptop", "Available");
            insertItem(conn, "Mouse", "Borrowed");

            printAllItems(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //creates a database table
    private static void createTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS items (
                unitNB TEXT PRIMARY KEY,
                shortDescr TEXT NOT NULL,
                statusE1 TEXT NOT NULL,
                sysAdmin TEXT NOT NULL,
                url TEXT,
                used INTEGER NOT NULL CHECK (used IN (0,1))
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    //insertion command
    private static void insertItem(Connection conn, String name, String status) throws SQLException {
        String sql = "INSERT INTO items(name, status) VALUES(?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, status);
            pstmt.executeUpdate();
        }
    }


    private static void printAllItems(Connection conn) throws SQLException {
        String sql = "SELECT * FROM items";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Items in database:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("status")
                );
            }
        }
    }

}



