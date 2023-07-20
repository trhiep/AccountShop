package entity;

/**
 *
 * @author tranh
 */
public class UserBalance {
    private String username;
    private int balance;

    public UserBalance() {
    }

    public UserBalance(String username, int balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserBalance{" + "username=" + username + ", balance=" + balance + '}';
    }
    
}
