package com.valex.itstorage.app;

import java.time.LocalDate;

public class Transaction {

    private String unitNB, shortDescr, statusE1, sysAdmin;
    private boolean used, serialized;
    private long oldJobSiteNB, newJobSiteNB;
    private LocalDate date;

    public Transaction(String unitNB, String shortDescr, String sysAdmin, long oldJobSiteNB, long newJobSiteNB) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.used = used;
        this.serialized = serialized;
        this.oldJobSiteNB = oldJobSiteNB;
        this.newJobSiteNB = newJobSiteNB;
        this.date = date;
    }
}
