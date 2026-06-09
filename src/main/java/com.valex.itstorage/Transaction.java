package com.valex.itstorage;

import java.time.LocalDate;

public class Transaction {

    private String unitNB, shortDescr, statusE1, sysAdmin;
    private boolean used, tossable;
    private long jobSiteNB;
    private LocalDate date;

    public Transaction(String unitNB, String shortDescr, String sysAdmin, long jobSiteNB) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.used = used;
        this.tossable = tossable;
        this.jobSiteNB = jobSiteNB;
        this.date = date;
    }
}
