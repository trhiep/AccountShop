package entity;

import java.sql.Timestamp;

/**
 *
 * @author tranh
 */
public class BalanceHistory {
    private String username;
    private boolean status;
    private int amount;
    private String reason;
    private Timestamp date;
    private int newBalance;

    public BalanceHistory() {
    }

    public BalanceHistory(String username, boolean status, int amount, String reason, Timestamp date, int newBalance) {
        this.username = username;
        this.status = status;
        this.amount = amount;
        this.reason = reason;
        this.date = date;
        this.newBalance = newBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }

    @Override
    public String toString() {
        return "BalanceHistory{" + "username=" + username + ", status=" + status + ", amount=" + amount + ", reason=" + reason + ", date=" + date + ", newBalance=" + newBalance + '}';
    }
    
}
