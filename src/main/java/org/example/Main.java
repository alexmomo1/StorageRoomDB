package org.example;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static void main() {
        System.out.println("Enter User then PWord");
        Scanner scanner = new Scanner(System.in);
        String u,p;
        u = scanner.nextLine();
        p = scanner.nextLine();

        this.connectToAndQueryDatabase(u,p);
    }


    public void connectToAndQueryDatabase(String username, String password) {

        Connection con = DriverManager.getConnection(
                "jdbc:myDriver:myDatabase", username,password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");

        while (rs.next()) {
            int x = rs.getInt("a");
            String s = rs.getString("b");
            float f = rs.getFloat("c");
        }
    }
}
