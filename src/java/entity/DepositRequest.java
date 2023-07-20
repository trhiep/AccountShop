package entity;

import java.sql.Timestamp;

/**
 *
 * @author tranh
 */
public class DepositRequest {
    private int id;
    private String username;
    private int amount;
    private Timestamp date;

    public DepositRequest() {
    }

    public DepositRequest(int id, String username, int amount, Timestamp date) {
        this.id = id;
        this.username = username;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DepositRequest{" + "id=" + id + ", username=" + username + ", amount=" + amount + ", date=" + date + '}';
    }
   
}
