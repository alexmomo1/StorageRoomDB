package com.valex.itstorage.app;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.valex.itstorage.database.DBManager;
import com.valex.itstorage.model.E1Status;
import com.valex.itstorage.model.Item;
import com.valex.itstorage.model.ItemStatus;
import com.valex.itstorage.model.ItemType;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);

        Scanner scanner = new Scanner(System.in);

        try (DBManager db = new DBManager()){
            db.resetDatabase();

            Item item = new Item(
                    "29J12345test",
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

            while (true) {
                System.out.println("1. Add Item");
                System.out.println("2. View Items");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        //add item
                        System.out.print("Enter the unit number:");
                        System.out.flush();
                        String unitNB = scanner.nextLine();
                        System.out.print("Enter a short description:");
                        String shortDescr = scanner.nextLine();
                        E1Status e1Status = null;
                        ItemType itemType = null;
                        ItemStatus itemStatus = null;
                        while (e1Status == null || itemType == null || itemStatus == null ) {
                            System.out.println("Enter the new E1 status:" +
                                    "\n AV,AC or UK");
                            String e1String = scanner.nextLine().toUpperCase();
                            System.out.println("Enter the new item status:" +
                                    "\n AVAILABLE, ASSIGNED, REPAIR, RETIRED or LOST");
                            String statusString = scanner.nextLine().toUpperCase();
                            System.out.println("Enter the item type:" +
                                    "\n LAPTOP, MONITOR, UPS, CABLE, DOCK, NETWORK, MISC or PERIPHERAL");
                            String typeString = scanner.nextLine().toUpperCase();
                            try {
                                e1Status = E1Status.valueOf(e1String);
                                itemType = ItemType.valueOf(typeString);
                                itemStatus = ItemStatus.valueOf(statusString);
                            } catch (Exception e) {
                                System.out.println("Wrong values entered, try again.");
                            }
                        }
                        System.out.print("Enter the job site:");
                        String jobSite = scanner.nextLine().toUpperCase();
                        System.out.print("Who is the item assigned to?");
                        String assignedTo = scanner.nextLine().toUpperCase();
                        System.out.print("Enter any additional notes:");
                        String notes = scanner.nextLine();
                        System.out.print("Enter your SysAdmin:");
                        String sysAdmin = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the URL (optional):");
                        String url = scanner.nextLine();
                        System.out.print("Is the item used? (y/n)");
                        boolean used = scanner.nextLine().equalsIgnoreCase("Y");
                        item = new Item(
                                unitNB,
                                shortDescr,
                                e1Status,
                                itemStatus,
                                itemType,
                                jobSite,
                                assignedTo,
                                notes,
                                sysAdmin,
                                url,
                                used,
                                unitNB==null,
                                LocalDate.now(),
                                LocalDate.now()
                        );
                        db.insertItem(item);
                        break;
                    case 2:
                        // print items
                        for (Item dbItem : db.getAllItems()) {
                            System.out.println(
                                    dbItem.getUnitNB() + " | " +
                                            dbItem.getShortDescr() + " | " +
                                            dbItem.getItemStatus()
                            );
                        }
                        break;
                    case 3:
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







