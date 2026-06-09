package com.valex.itstorage;
import java.time.LocalDate;

public class Item {

    private String unitNB, shortDescr, statusE1, sysAdmin, url;
    private boolean used, tossable;
    private LocalDate lastUpdate;

    public Item(String unitNB, String shortDescr, String statusE1,
                String sysAdmin, String url, boolean used) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.tossable = unitNB.isEmpty();
        this.lastUpdate = LocalDate.now();
    }




    // Getters
    public String getUnitNB() { return unitNB; }
    public String getShortDescr() { return shortDescr; } // name or what it does (cable length, data throughput etc.)
    public String getStatusE1() { return statusE1; } // AC or AV
    public String getSysAdmin() { return sysAdmin; } // name of whoever added its last update to e1
    public String getUrl() { return url; }
    public boolean isUsed() { return used; } // is it sealed and still in original packaging
    public boolean isTossable() { return tossable; } // it means it has no asset tag (29JXXXXX)
    public LocalDate getLastUpdate() { return lastUpdate; }

}
