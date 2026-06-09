package com.valex.itstorage.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

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
                unitNB TEXT UNIQUE PRIMARY KEY,
                shortDescr TEXT NOT NULL,
                statusE1 TEXT NOT NULL,
                sysAdmin TEXT NOT NULL,
                url TEXT,
                assignedTo TEXT,
                jobSite
                used INTEGER NOT NULL CHECK (used IN (0,1)),
                serialized INTEGER NOT NULL CHECK (used IN (0,1)),
                date TEXT NOT NULL
            );
        """;

        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    //insert item function
    public void insertItem(Item item) throws SQLException {
        String sql = """
                INSERT INTO items(unitNB, shortDescr,
                statusE1, sysAdmin, url, used, tossable, date)
                VALUES(?,?,?,?,?,?,?,?)
                """;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getUnitNB());
            pstmt.setString(2, item.getShortDescr());
            pstmt.setString(3, item.getStatusE1());
            pstmt.setString(4, item.getSysAdmin());
            pstmt.setString(5, item.getUrl());
            pstmt.setInt(6, item.isUsed() ? 1 : 0);
            pstmt.setInt(7, item.isTossable() ? 1 : 0);
            pstmt.setString(8, item.getDate().toString());

            pstmt.executeUpdate();
        }
    }


    // return list of all items
    public List<Item> getAllItems() throws SQLException{
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Item item = new Item(
                        rs.getString("unitNB"),
                        rs.getString("shortDescr"),
                        rs.getString("statusE1"),
                        rs.getString("sysAdmin"),
                        rs.getString("url"),
                        rs.getInt("used") == 1
                );
                items.add(item);
            }
        }
        return items;
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
}
