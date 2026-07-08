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

KNOWN LIMITATIONS
---

-NO user interface (everything done through main)

-Incomplete CRUD

-NO search


-NO transaction history (not implemented yet)

---
FUTURES IMPROVEMENTS 
---

-RoadMap: addItem(Item item), updateItem(Item item), deleteItem(Item item), getItemByType(Type T), addTransaction(Transaction transaction), getTransactionBetween(String Start, String end), getTransactionForItem(Item item)

-CLI → Add item, View item, Search items, Update item, Delete item

-REST API

-CSV export

-Notifications on low inventory

-GUI

-Web integration

-Bar code scanner support

-role-based authentication

-cloud sync

 HOW TO RUN THE MVP
---
### Clone the repository
'''bash
git clone https://github.com/alexmomo1/StorageRoom
cd StorageRoomDB
