package com.valex.itstorage.app;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.valex.itstorage.database.DBManager;
import com.valex.itstorage.model.Item;
import com.valex.itstorage.model.Transaction;

public class Main {
    static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);

        try {

            DBManager db = new DBManager();
            Item item1 = new Item(
                    "29J61522",
                    "DELL Performance",
                    "AV - IT SPARE",
                    "VAFM",
                    "https://www.dell.com/en-ca/shop/alienware/gaming/desktops",
                    false
            );
            Item item2 = new Item(
                    "29J59708",
                    "DELL  Laptop Pro+ 14",
                    "AC - SSE",
                    "VAFM",
                    "https://www.dell.com/en-ca/shop/alienware/gaming/desktops",
                    true
            );

            db.insertItem(item1);
            db.insertItem(item1);
            List<Item> items = db.getAllItems();

            for (Item item : items) {
                System.out.println(
                        item.getUnitNB() + " | " +
                        item.getShortDescr() + " | " +
                        item.getStatusE1()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
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







