package com.valex.itstorage.model;
import java.time.LocalDate;

public class Transaction {

    private String unitNB, shortDescr, e1Status, sysAdmin, reqNB;
    private long id, oldJobSiteNB, newJobSiteNB;
    private LocalDate date;
    private TransactionType type;
    private static long idCounter = 0;

    public Transaction(TransactionType type,String unitNB, String shortDescr,
                       String sysAdmin,String reqNB, long oldJobSiteNB, long newJobSiteNB,
                       String newE1Status) {
        this.id = ++idCounter;
        this.type = type;
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.e1Status = newE1Status;
        this.sysAdmin = sysAdmin;
        this.reqNB = reqNB;
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

    public String getE1Status() {
        return e1Status;
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
