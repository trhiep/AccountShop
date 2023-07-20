package entity;

/**
 *
 * @author tranh
 */
public class Cart {
    
    private String username;
    private int productID;
    private int quantity;
    private int total;

    public Cart() {
    }

    public Cart(String username, int productID, int quantity, int total) {
        this.username = username;
        this.productID = productID;
        this.quantity = quantity;
        this.total = total;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" + "username=" + username + ", productID=" + productID + ", quantity=" + quantity + ", total=" + total + '}';
    }

}
