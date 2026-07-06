package com.valex.itstorage.model;
import java.time.LocalDate;

public class Transaction {

    private String unitNB, shortDescr, statusE1, sysAdmin;
    private long id, oldJobSiteNB, newJobSiteNB;
    private LocalDate date;
    private TransactionType type;
    private static long idCounter = 0;

    public Transaction(TransactionType type,String unitNB, String shortDescr, String sysAdmin, long oldJobSiteNB, long newJobSiteNB, String newStatusE1) {
        this.id = idCounter++;
        this.type = type;
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = newStatusE1;
        this.sysAdmin = sysAdmin;
        this.oldJobSiteNB = oldJobSiteNB;
        this.newJobSiteNB = newJobSiteNB;
        this.date = LocalDate.now();
    }

    public String getUnitNB() {
        return unitNB;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public String getStatusE1() {
        return statusE1;
    }

    public String getSysAdmin() {
        return sysAdmin;
    }

    public long getOldJobSiteNB() {
        return oldJobSiteNB;
    }

    public long getNewJobSiteNB() {
        return newJobSiteNB;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
