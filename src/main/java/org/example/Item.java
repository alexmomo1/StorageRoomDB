package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Item {

    String unitNB, shortDescr, statusE1, sysAdmin, url;
    boolean used, tossable;
    LocalDate date;

    public Item(String unitNB, String shortDescr, String statusE1, String sysAdmin, String url, boolean used, LocalDate date) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.tossable = unitNB.isEmpty();
        this.date = date;
    }
}
