package DAO;

import entity.BalanceHistory;
import entity.DepositHistory;
import entity.DepositRequest;
import entity.UserBalance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author tranh
 */
public class BalanceDAO extends DBContext {

    public ArrayList<DepositRequest> loadAllDepositRequest() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM DepositRequest ORDER BY date";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<DepositRequest> requestList = new ArrayList<>();
            while (rs.next()) {
                DepositRequest request = new DepositRequest(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4));
                requestList.add(request);
            }
            return requestList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<DepositHistory> loadAllDepositHistory() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM DepositHistory ORDER BY actionDate DESC";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<DepositHistory> depositHistory = new ArrayList<>();
            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                String username = rs.getString("username");
                int amount = rs.getInt("amount");
                Timestamp requestDate = rs.getTimestamp("requestDate");
                boolean status = rs.getBoolean("status");
                Timestamp actionDate = rs.getTimestamp("actionDate");
                String actionBy = rs.getString("actionBy");
                String reason = rs.getString("reason");
                DepositHistory history = new DepositHistory(requestID, username, amount, requestDate, status, actionDate, actionBy, reason);
                depositHistory.add(history);
            }
            return depositHistory;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<DepositHistory> loadDepositHistoryByUsername(String user) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = " SELECT * FROM DepositHistory "
                    + "WHERE username = ? "
                    + "ORDER BY requestDate DESC";
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            rs = stm.executeQuery();
            ArrayList<DepositHistory> depositHistory = new ArrayList<>();
            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                String username = rs.getString("username");
                int amount = rs.getInt("amount");
                Timestamp requestDate = rs.getTimestamp("requestDate");
                boolean status = rs.getBoolean("status");
                Timestamp actionDate = rs.getTimestamp("actionDate");
                String actionBy = rs.getString("actionBy");
                String reason = rs.getString("reason");
                DepositHistory history = new DepositHistory(requestID, username, amount, requestDate, status, actionDate, actionBy, reason);
                depositHistory.add(history);
            }
            return depositHistory;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<BalanceHistory> loadBalanceHistoryByUsername(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT TOP(10) * FROM BalanceHistory "
                    + "WHERE username = ? "
                    + "ORDER BY date DESC";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            ArrayList<BalanceHistory> balanceHistory = new ArrayList<>();
            while (rs.next()) {
                boolean status = rs.getBoolean("status");
                int amount = rs.getInt("amount");
                String reason = rs.getString("reason");
                Timestamp date = rs.getTimestamp("date");
                int newBalance = rs.getInt("newBalance");
                balanceHistory.add(new BalanceHistory(username, status, amount, reason, date, newBalance));
            }
            return balanceHistory;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<DepositRequest> loadDepositRequestingByUsername(String user) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM DepositRequest "
                    + "WHERE username = ? "
                    + "ORDER BY date DESC;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            rs = stm.executeQuery();
            ArrayList<DepositRequest> depositRequestList = new ArrayList<>();
            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                String username = rs.getString("username");
                int amount = rs.getInt("amount");
                Timestamp requestDate = rs.getTimestamp("date");
                DepositRequest request = new DepositRequest(requestID, username, amount, requestDate);
                depositRequestList.add(request);
            }
            return depositRequestList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean requestDeposit(String username, String amount) {
        PreparedStatement stm;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String requestDate = timestamp.toString();
        try {
            String sql = "INSERT INTO DepositRequest VALUES (?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, amount);
            stm.setString(3, requestDate);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public void acceptDeposit(String id, String username, String amount, String requestDate, String reason, String actionBy) {
        PreparedStatement stm;
        ResultSet rs;
        int amountInAcc = 0;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String actionDate = timestamp.toString();

        // Lấy ra số tiền hiện tại của username
        try {
            String sql = "SELECT * FROM UserBalance WHERE username = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                amountInAcc = rs.getInt(2);
            } else {
                amountInAcc = 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        int amountInt = Integer.parseInt(amount);
        int amountAfterDeposit = amountInAcc + amountInt;

        try {
            String sql = "SELECT * FROM UserBalance WHERE username = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                // Cập nhật số tiền của username đã có
                try {
                    sql = "UPDATE UserBalance SET balance = ? WHERE username = ?";
                    stm = connection.prepareStatement(sql);
                    stm.setInt(1, amountAfterDeposit);
                    stm.setString(2, username);
                    stm.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } else {
                // Thêm mới username vào bảng và balance
                try {
                    sql = "INSERT INTO UserBalance VALUES (?, ?)";
                    stm = connection.prepareStatement(sql);
                    stm.setString(1, username);
                    stm.setInt(2, amountAfterDeposit);
                    stm.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        // Cập nhật danh sách phê duyệt
        try {
            String sql = "DELETE FROM DepositRequest WHERE requestID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        // Cập nhật lịch sử nạp tiền
        try {
            String sql = "INSERT INTO DepositHistory VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, username);
            stm.setString(3, amount);
            stm.setString(4, requestDate);
            stm.setString(5, "1");
            stm.setString(6, actionDate);
            stm.setString(7, actionBy);
            stm.setString(8, reason);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        // Cập nhật lịch sử số dư
        try {
            String sql = "INSERT INTO BalanceHistory VALUES (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, "1");
            stm.setString(3, amount);
            stm.setString(4, "Deposit");
            stm.setString(5, actionDate);
            stm.setInt(6, amountAfterDeposit);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void denyDeposit(String id, String username, String amount, String requestDate, String reason, String actionBy) {
        PreparedStatement stm;
        ResultSet rs;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String actionDate = timestamp.toString();
        // Cập nhật danh sách phê duyệt
        try {
            String sql = "DELETE FROM DepositRequest WHERE requestID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        // Cập nhật lịch sử nạp tiền
        try {
            String sql = "INSERT INTO DepositHistory VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, username);
            stm.setString(3, amount);
            stm.setString(4, requestDate);
            stm.setString(5, "0");
            stm.setString(6, actionDate);
            stm.setString(7, actionBy);
            stm.setString(8, reason);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        BalanceDAO dao = new BalanceDAO();
        ArrayList<BalanceHistory> DepositHistory = dao.loadBalanceHistoryByUsername("tranhiwp");
        for (BalanceHistory balanceHistory : DepositHistory) {
            System.out.println(balanceHistory);
        }
    }

    public UserBalance getBalanceByUsername(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM UserBalance WHERE username = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                UserBalance userBalance = new UserBalance(rs.getString(1), rs.getInt(2));
                return userBalance;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void saveBalance(String username, int balance) {
        PreparedStatement stm;
        try {
            String saveBalanceSQL = "UPDATE UserBalance SET balance = ? WHERE username = ?";
            stm = connection.prepareStatement(saveBalanceSQL);
            stm.setInt(1, balance);
            stm.setString(2, username);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void saveBalanceHistory(String username, String amount, String newBalance) {
        PreparedStatement stm;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String actionDate = timestamp.toString();
        try {
            String sql = "INSERT INTO BalanceHistory VALUES (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, "0");
            stm.setString(3, amount);
            stm.setString(4, "Order");
            stm.setString(5, actionDate);
            stm.setString(6, newBalance);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
