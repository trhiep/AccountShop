package entity;

/**
 *
 * @author tranh
 */
public class Catalog {
    
    private String catalogID;
    private String catalogName;
    private String catalogImg;

    public Catalog() {
    }

    public Catalog(String catalogID, String catalogName, String catalogImg) {
        this.catalogID = catalogID;
        this.catalogName = catalogName;
        this.catalogImg = catalogImg;
    }

    public String getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(String catalogID) {
        this.catalogID = catalogID;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogImg() {
        return catalogImg;
    }

    public void setCatalogImg(String catalogImg) {
        this.catalogImg = catalogImg;
    }

    @Override
    public String toString() {
        return "Catalog{" + "catalogID=" + catalogID + ", catalogName=" + catalogName + ", catalogImg=" + catalogImg + '}';
    }
    
}
