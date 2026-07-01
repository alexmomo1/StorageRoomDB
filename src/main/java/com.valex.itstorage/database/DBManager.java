package com.valex.itstorage.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.valex.itstorage.model.Item;
import java.time.LocalDate;

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
                notes TEXT,
                itemStatus TEXT NOT NULL,
                itemType TEXT NOT NULL,
                assignedTo TEXT,
                jobSite TEXT,
                used INTEGER NOT NULL CHECK (used IN (0,1)),
                serialized INTEGER NOT NULL CHECK (serialized IN (0,1)),
                lastUpdateDate TEXT NOT NULL
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
                statusE1, sysAdmin, url, notes, itemStatus, itemType, 
                assignedTo, jobSite, used, serialized, lastUpdateDate)
                VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
                """;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getUnitNB());
            pstmt.setString(2, item.getShortDescr());
            pstmt.setString(3, item.getStatusE1());
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
                        rs.getString("notes"),
                        rs.getString("itemStatus"),
                        rs.getString("itemType"),
                        rs.getString("assignedTo"),
                        rs.getString("jobSite"),
                        rs.getInt("used") == 1,
                        rs.getInt("serialized") == 1,
                        LocalDate.parse(rs.getString("lastUpdateDate"))
                );
                items.add(item);
            }
        }
        return items;
    }

    private Item mapRowToItem(ResultSet rs){

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

    public void close(){
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection.");
                e.printStackTrace();
            }
        }
    }
}
