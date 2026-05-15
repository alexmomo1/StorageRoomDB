package example;
import java.time.LocalDate;

public class Item {

    private String unitNB, shortDescr, statusE1, sysAdmin, url;
    private boolean used, tossable;
    private LocalDate date;

    public Item(String unitNB, String shortDescr, String statusE1,
                String sysAdmin, String url, boolean used) {
        this.unitNB = unitNB;
        this.shortDescr = shortDescr;
        this.statusE1 = statusE1;
        this.sysAdmin = sysAdmin;
        this.url = url;
        this.used = used;
        this.tossable = unitNB.isEmpty();
        this.date = LocalDate.now();
    }




    // Getters
    public String getUnitNB() { return unitNB; }
    public String getShortDescr() { return shortDescr; }
    public String getStatusE1() { return statusE1; }
    public String getSysAdmin() { return sysAdmin; }
    public String getUrl() { return url; }
    public boolean isUsed() { return used; }
    public boolean isTossable() { return tossable; }
    public LocalDate getDate() { return date; }

}
