package com.valex.itstorage.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.AutoCloseable;
import com.valex.itstorage.model.Item;
import com.valex.itstorage.model.ItemStatus;
import com.valex.itstorage.model.ItemType;

import java.time.LocalDate;

public class DBManager implements AutoCloseable {

    String url = "jdbc:sqlite:inventory.db";
    private Connection conn;

    public DBManager() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);
        System.out.println("Connected to SQLite.");

        createTable();
    }

    //creates a database table
    private void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS items (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                unitNB TEXT UNIQUE,
                shortDescr TEXT NOT NULL,
                e1Status TEXT NOT NULL,
                sysAdmin TEXT NOT NULL,
                url TEXT,
                notes TEXT,
                itemStatus TEXT NOT NULL,
                itemType TEXT NOT NULL,
                assignedTo TEXT,
                jobSite TEXT,
                used INTEGER NOT NULL CHECK (used IN (0,1)),
                serialized INTEGER NOT NULL CHECK (serialized IN (0,1)),
                lastUpdateDate TEXT NOT NULL,
                creationDate TEXT NOT NULL
            );
        """;

        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    //insert item function
    public void insertItem(Item item) throws SQLException {
        String sql = """
                INSERT INTO items(
                    id, unitNB, shortDescr,e1Status, sysAdmin, url, notes,
                    itemStatus, itemType,assignedTo, jobSite,
                    used, serialized, lastUpdateDate, creationDate
                )
                VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                """;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getID());
            pstmt.setString(1, item.getUnitNB());
            pstmt.setString(2, item.getShortDescr());
            pstmt.setString(3, item.getE1Status());
            pstmt.setString(4, item.getSysAdmin());
            pstmt.setString(5, item.getUrl());
            pstmt.setString(6, item.getNotes());
            pstmt.setString(7, item.getItemStatus().toString());
            pstmt.setString(8, item.getItemType().toString());
            pstmt.setString(9, item.getAssignedTo());
            pstmt.setString(10, item.getJobSite());
            pstmt.setInt(11, item.isUsed() ? 1 : 0);
            pstmt.setInt(12, item.isSerialized() ? 1 : 0);
            pstmt.setString(13, item.getLastUpdateDate().toString());
            pstmt.setString(14, item.getCreationDate().toString());

            pstmt.executeUpdate();
        }
    }
    // return list of all items
    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(mapRowToItem(rs));
            }
        }

        return items;
    }

    private Item mapRowToItem(ResultSet rs) throws SQLException {
        return new Item(
                rs.getInt("id"),
                rs.getString("unitNB"),
                rs.getString("shortDescr"),
                rs.getString("e1Status"),

                // convert DB string → enum safely
                safeStatus(rs.getString("itemStatus")),
                safeType(rs.getString("itemType")),

                rs.getString("jobSite"),
                rs.getString("assignedTo"),
                rs.getString("notes"),
                rs.getString("sysAdmin"),
                rs.getString("url"),

                rs.getInt("used") == 1,
                rs.getInt("serialized") == 1,

                // convert DB string → LocalDate
                LocalDate.parse(rs.getString("lastUpdateDate")),
                LocalDate.parse(rs.getString("creationDate"))
        );
    }

    private ItemStatus safeStatus(String value) {
        try {
            return ItemStatus.valueOf(value);
        } catch (Exception e) {
            return ItemStatus.AVAILABLE; // if invalid
        }
    }

    private ItemType safeType(String value) {
        try {
            return ItemType.valueOf(value);
        } catch (Exception e) {
            return ItemType.MISC; // if invalid
        }
    }



  /*

connect()
createTables()
addItem(Item item)
updateItem(Item item)
deleteItem(int itemId)
getItemById(int id)
getAllItems()
searchItems(String keyword)
addTransaction(Transaction transaction)
getTransactionsBetween(String startDate, String endDate)
getTransactionsForItem(int itemId)

     */


    @Override
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing DB: " + e.getMessage());
        }
    }

}
