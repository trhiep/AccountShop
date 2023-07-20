package entity;

import java.sql.Timestamp;

/**
 *
 * @author tranh
 */
public class ProductRate {
    
    private int rateID;
    private String username;
    private int productID;
    private int star;
    private String comment;
    private Timestamp rateDate;

    public ProductRate() {
    }

    public ProductRate(int rateID, String username, int productID, int star, String comment, Timestamp rateDate) {
        this.rateID = rateID;
        this.username = username;
        this.productID = productID;
        this.star = star;
        this.comment = comment;
        this.rateDate = rateDate;
    }

    public int getRateID() {
        return rateID;
    }

    public void setRateID(int rateID) {
        this.rateID = rateID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getRateDate() {
        return rateDate;
    }

    public void setRateDate(Timestamp rateDate) {
        this.rateDate = rateDate;
    }

    @Override
    public String toString() {
        return "ProductRate{" + "rateID=" + rateID + ", username=" + username + ", productID=" + productID + ", star=" + star + ", comment=" + comment + ", rateDate=" + rateDate + '}';
    }

}
