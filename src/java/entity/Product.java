package entity;

/**
 *
 * @author tranh
 */
public class Product {
    private int productID;
    private String catalogID;
    private String title;
    private String type;
    private int price;
    private String shortDetail;
    private String longDetail;
    private String productImg;
    private int quantity;

    public Product() {
    }

    public Product(int productID, String catalogID, String title, String type, int price, String shortDetail, String longDetail, String productImg, int quantity) {
        this.productID = productID;
        this.catalogID = catalogID;
        this.title = title;
        this.type = type;
        this.price = price;
        this.shortDetail = shortDetail;
        this.longDetail = longDetail;
        this.productImg = productImg;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(String catalogID) {
        this.catalogID = catalogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShortDetail() {
        return shortDetail;
    }

    public void setShortDetail(String shortDetail) {
        this.shortDetail = shortDetail;
    }

    public String getLongDetail() {
        return longDetail;
    }

    public void setLongDetail(String longDetail) {
        this.longDetail = longDetail;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", catalogID=" + catalogID + ", title=" + title + ", type=" + type + ", price=" + price + ", shortDetail=" + shortDetail + ", longDetail=" + longDetail + ", productImg=" + productImg + ", quantity=" + quantity + '}';
    }

}
