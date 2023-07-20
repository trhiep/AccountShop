package DAO;

import entity.RoleInformation;
import entity.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author tranh
 */
public class UsersDAO extends DBContext {

    public Users login(String username, String password) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String selectAllSQL = "SELECT * FROM Users WHERE username = ? AND password = ?";
            stm = connection.prepareStatement(selectAllSQL);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                Users user = new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean checkExistsUsername(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String selectByUsernameSQL = "SELECT * FROM Users WHERE username = ?";
            stm = connection.prepareStatement(selectByUsernameSQL);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Users> loadAllUsers() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String selectAllSQL = "SELECT * FROM Users ORDER BY role";
            stm = connection.prepareStatement(selectAllSQL);
            rs = stm.executeQuery();
            ArrayList<Users> usersList = new ArrayList<>();
            while (rs.next()) {
                Users user = new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean addNewUser(String username, String password, String fullname, String email, int role, boolean isVerificated) {
        PreparedStatement stm;
        try {
            String createUserSQL = "INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(createUserSQL);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, fullname);
            stm.setString(4, email);
            stm.setInt(5, role);
            stm.setBoolean(6, isVerificated);
            stm.executeUpdate();

            String createUserBalanceSQL = "INSERT INTO UserBalance VALUES (?, ?)";
            stm = connection.prepareStatement(createUserBalanceSQL);
            stm.setString(1, username);
            stm.setInt(2, 0);
            stm.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public void deleteUserByUsername(String username) {
        PreparedStatement stm;
        try {
            String deleteUserFromUserBalanceSQL = "DELETE FROM UserBalance WHERE username = ?";
            String deleteUserFromBalanceHistorySQL = "DELETE FROM BalanceHistory WHERE username = ?";
            String deleteUserFromDepositRequestSQL = "DELETE FROM DepositRequest WHERE username = ?";
            String deleteUserFromDepositHistorySQL = "DELETE FROM DepositHistory WHERE username = ?";
            String deleteUserFromCartSQL = "DELETE FROM Cart WHERE username = ?";
            String deleteUserFromOrdersHistorySQL = "DELETE FROM OrdersHistory WHERE username = ?";
            String deleteUserFromProductRateSQL = "DELETE FROM ProductRate WHERE username = ?";
            String deleteUserFromUsersSQL = "DELETE FROM Users WHERE username = ?";
            stm = connection.prepareStatement(deleteUserFromUserBalanceSQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromBalanceHistorySQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromDepositRequestSQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromDepositHistorySQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromCartSQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromOrdersHistorySQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromProductRateSQL);
            stm.setString(1, username);
            stm.executeUpdate();
            stm = connection.prepareStatement(deleteUserFromUsersSQL);
            stm.setString(1, username);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean updateUserByUsername(String username, String password, String fullname, String email, int role, boolean isVerified) {
        PreparedStatement stm;
        try {
            String updateUserSQL = "UPDATE Users\n"
                    + "SET password = ?, fullname = ?, email = ?, role = ?, isVerificated = ?\n"
                    + "WHERE username = ?";
            stm = connection.prepareStatement(updateUserSQL);
            stm.setString(1, password);
            stm.setString(2, fullname);
            stm.setString(3, email);
            stm.setInt(4, role);
            stm.setBoolean(5, isVerified);
            stm.setString(6, username);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Users searchUserByUsername(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Users WHERE username = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                String pass = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                int role = rs.getInt("role");
                boolean isVerificated = rs.getBoolean("isVerificated");
                Users user = new Users(username, pass, fullname, email, role, isVerificated);
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<RoleInformation> loadAllRoleInformationList() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String selectAllSQL = "SELECT * FROM RoleInformation";
            stm = connection.prepareStatement(selectAllSQL);
            rs = stm.executeQuery();
            ArrayList<RoleInformation> roleList = new ArrayList<>();
            while (rs.next()) {
                RoleInformation role = new RoleInformation(rs.getInt(1), rs.getString(2));
                roleList.add(role);
            }
            return roleList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // Test DAO
    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();
        Users user = dao.login("hiwp3", "hiwp3");
        System.out.println(user);
    }
}
