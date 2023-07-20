package entity;

import java.sql.Timestamp;

/**
 *
 * @author tranh
 */
public class Account {
    private int accountID;
    private String productID;
    private String email;
    private String password;
    private Timestamp dueDate;

    public Account() {
    }

    public Account(int accountID, String productID, String email, String password, Timestamp dueDate) {
        this.accountID = accountID;
        this.productID = productID;
        this.email = email;
        this.password = password;
        this.dueDate = dueDate;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
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
        return "Account{" + "accountID=" + accountID + ", productID=" + productID + ", email=" + email + ", password=" + password + ", dueDate=" + dueDate + '}';
    }
    
}
