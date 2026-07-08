## **StorageRoomDB**
is a program destined to adapt to a storage unit in a building, project or personal room. 
This *simple* Java and SQLite program will allow users to track transactions and storage items.
--- 
**FOR NOW** 

This project is a **Minimum Viable Product (MVP)**.

---
FEATURES
---
-Item and transaction objects

-SQLite allowing persistent data

-Retrieve and display items

-**Item data**: unit number, description, item type, job site, metadata

-**Track**: creation date, last update time, serialized items, print all items

TECHNOLOGY
---

-Java (JDK 17+)

-SQLite (Local DB)

-JDBC (sqlite-jdbc)

### Clone the repository
'''bash
git clone https://github.com/alexmomo1/StorageRoom
cd StorageRoomDB

---
FUTURES IMPROVEMENTS
---
-Notifications low inventory
-GUI
-Web integration
-Bar code scanner support
-role-based authentication
-cloud sync
 Additionnally, each item will have trackable IDs. Notifications will be sent whenever a consumable item is low in stock.
