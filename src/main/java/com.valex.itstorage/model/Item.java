package com.valex.itstorage.model;
import java.time.LocalDate;

public class Item {

    private String unitNB, shortDescr, e1Status, sysAdmin, url, assignedTo, notes;
    private ItemStatus itemStatus;
    private ItemType itemType;
    private String jobSite;
    private boolean used, serialized;
    private LocalDate lastUpdateDate, creationDate ;

    public Item(String unitNB,
                String shortDescr,
                String e1Status,
                String itemStatus,
                String itemType,
                String jobSite,
                String assignedTo,
                String notes,
                String sysAdmin,
                String url,
                boolean used) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.e1Status = e1Status;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.jobSite = jobSite;
        this.assignedTo = assignedTo;
        this.itemStatus = ItemStatus.valueOf(itemStatus);
        this.itemType = ItemType.valueOf(itemType);
        this.serialized = unitNB != null && !unitNB.isEmpty();
        this.lastUpdateDate = LocalDate.now();
        this.creationDate = LocalDate.now();
        this.notes = notes;
    }

    // DataBase constructor only
    public Item(String unitNB,
                String shortDescr,
                String e1Status,
                ItemStatus itemStatus,
                ItemType itemType,
                String jobSite,
                String assignedTo,
                String notes,
                String sysAdmin,
                String url,
                boolean used,
                boolean serialized,
                LocalDate lastUpdateDate,
                LocalDate creationDate) {

        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.e1Status = e1Status;
        this.itemStatus = itemStatus;
        this.itemType = itemType;
        this.jobSite = jobSite;
        this.assignedTo = assignedTo;
        this.notes = notes;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.serialized = serialized;
        this.lastUpdateDate = lastUpdateDate;
        this.creationDate = creationDate;
    }



    // Getters
    public String getUnitNB() { return unitNB; }
    public String getShortDescr() { return shortDescr; } // name or what it does (cable length, data throughput etc.)
    public String getE1Status() { return e1Status; } // AC or AV
    public String getSysAdmin() { return sysAdmin; } // name of whoever added its last update to e1
    public String getUrl() { return url; }
    public boolean isUsed() { return used; } // is it sealed and still in original packaging
    public boolean isSerialized() { return serialized; } // it means it has no asset tag (29JXXXXX)
    public LocalDate getLastUpdateDate() { return lastUpdateDate; }
    public LocalDate getCreationDate() {return creationDate;    }
    public String getAssignedTo() {return assignedTo;}
    public String getNotes() {return notes;}
    public ItemStatus getItemStatus() {return itemStatus;}
    public ItemType getItemType() {return itemType;}
    public String getJobSite() {return jobSite;}
}
