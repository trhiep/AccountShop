package entity;

import java.sql.Timestamp;

/**
 *
 * @author tranh
 */
public class Order {
    private int orderID;
    private String username;
    private int accountID;
    private int productID;
    private String email;
    private String password;
    private Timestamp dueDate;

    public Order() {
    }

    public Order(int orderID, String username, int accountID, int productID, String email, String password, Timestamp dueDate) {
        this.orderID = orderID;
        this.username = username;
        this.accountID = accountID;
        this.productID = productID;
        this.email = email;
        this.password = password;
        this.dueDate = dueDate;
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", username=" + username + ", accountID=" + accountID + ", productID=" + productID + ", email=" + email + ", password=" + password + ", dueDate=" + dueDate + '}';
    }

}
