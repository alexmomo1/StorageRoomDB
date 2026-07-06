package com.valex.itstorage.app;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.valex.itstorage.database.DBManager;
import com.valex.itstorage.model.Item;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);


        try (DBManager db = new DBManager()){
            Item item = new Item(
                    "29J12345",
                    "Dell Latitude 7440",
                    "AC",
                    "ASSIGNED",
                    "LAPTOP",
                    "MTL DO",
                    "VICTOR ALEXANDRE FRINGS-MORALES",
                    "Demo item",
                    "VAFM",
                    null,
                    true
            );

            db.insertItem(item);

            for (Item dbItem : db.getAllItems()) {
                System.out.println(
                        dbItem.getUnitNB() + " | " +
                                dbItem.getShortDescr() + " | " +
                                dbItem.getItemStatus()
                );
            }
            db.close();
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







