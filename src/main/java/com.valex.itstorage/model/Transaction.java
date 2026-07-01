package com.valex.itstorage.model;
import java.time.LocalDate;

public class Transaction {

    private String unitNB, shortDescr, statusE1, sysAdmin;
    private long oldJobSiteNB, newJobSiteNB;
    private LocalDate date;

    public Transaction(String unitNB, String shortDescr, String sysAdmin, long oldJobSiteNB, long newJobSiteNB, String newStatusE1) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = newStatusE1;
        this.sysAdmin = sysAdmin;
        this.oldJobSiteNB = oldJobSiteNB;
        this.newJobSiteNB = newJobSiteNB;
        this.date = LocalDate.now();
    }
}
