package com.valex.itstorage.model;
import java.time.LocalDate;

public class Item {

    private String unitNB, shortDescr, statusE1, sysAdmin, url, assignedTo, notes;
    private ItemStatus itemStatus;
    private ItemType itemType;
    private long jobSite;
    private boolean used, serialized;
    private LocalDate lastUpdateDate;

    //serialized
    public Item(String unitNB, String shortDescr, String statusE1,
                String itemStatuss, String itemTypee, long jobSite,
                String assignedTo, String notes,
                String sysAdmin, String url, boolean used String assignedTo) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.jobSite = jobSite;
        this.assignedTo = assignedTo;
        this.itemStatus = itemStatuss;
        this.itemType = itemTypee;
        this.serialized = true;
        this.lastUpdateDate = LocalDate.now();
        this.assignedTo = assignedTo;
        this.notes = notes;
    }

    //non serialized
    public Item(String shortDescr, String statusE1,
                String itemStatuss, String itemTypee, String assignedTo,
                long jobSite, String notes,
                String sysAdmin, String url, boolean used) {
        this.unitNB = null;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.jobSite = jobSite;
        this.itemStatus = itemStatuss;
        this.itemType = itemTypee;
        this.serialized = false;
        this.lastUpdateDate = LocalDate.now();
        this.assignedTo = assignedTo;
        this.notes = notes;
    }




    // Getters
    public String getUnitNB() { return unitNB; }
    public String getShortDescr() { return shortDescr; } // name or what it does (cable length, data throughput etc.)
    public String getStatusE1() { return statusE1; } // AC or AV
    public String getSysAdmin() { return sysAdmin; } // name of whoever added its last update to e1
    public String getUrl() { return url; }
    public boolean isUsed() { return used; } // is it sealed and still in original packaging
    public boolean isSerialized() { return serialized; } // it means it has no asset tag (29JXXXXX)
    public LocalDate getLastUpdateDate() { return lastUpdateDate; }

}
