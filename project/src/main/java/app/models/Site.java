package app.models;

import java.util.List;

public class Site {
    private int id;
    private String sname;
    private String saddress;
    private List<ProductSite> productSites;

    public Site(int id, String sname, String saddress) {
        this.id = id;
        this.sname = sname;
        this.saddress = saddress;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public List<ProductSite> getProductSites() {
        return productSites;
    }

    public void setProductSites(List<ProductSite> productSites) {
        this.productSites = productSites;
    }
}