package entity;

import java.sql.Timestamp;

/**
 *
 * @author tranh
 */
public class DepositHistory {
    private int requestID;
    private String username;
    private int amount;
    private Timestamp requestDate;
    private boolean status;
    private Timestamp actionDate;
    private String actionBy;
    private String reason;

    public DepositHistory() {
    }

    public DepositHistory(int requestID, String username, int amount, Timestamp requestDate, boolean status, Timestamp actionDate, String actionBy, String reason) {
        this.requestID = requestID;
        this.username = username;
        this.amount = amount;
        this.requestDate = requestDate;
        this.status = status;
        this.actionDate = actionDate;
        this.actionBy = actionBy;
        this.reason = reason;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
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

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "DepositHistory{" + "requestID=" + requestID + ", username=" + username + ", amount=" + amount + ", requestDate=" + requestDate + ", status=" + status + ", actionDate=" + actionDate + ", actionBy=" + actionBy + ", reason=" + reason + '}';
    }

}
