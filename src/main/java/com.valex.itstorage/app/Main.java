package com.valex.itstorage.app;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.valex.itstorage.database.DBManager;
import com.valex.itstorage.model.Item;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);
        DBManager db = null;

        try {
            db = new DBManager();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
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







