package DAO;

import entity.Account;
import entity.Order;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author tranh
 */
public class OrdersDAO extends DBContext {

    public ArrayList<Order> loadAllOrderedHistory() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String getProductSQL = "SELECT * FROM OrdersHistory ORDER BY orderID DESC";
            stm = connection.prepareStatement(getProductSQL);
            rs = stm.executeQuery();
            ArrayList<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String username = rs.getString("username");
                int accountID = rs.getInt("accountID");
                int productID = rs.getInt("productID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                orderList.add(new Order(orderID, username, accountID, productID, email, password, dueDate));
            }
            return orderList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<Product> loadAllOrderedProductByUsername(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String getProductSQL = "SELECT * FROM ProductList p\n"
                    + "FULL OUTER JOIN OrdersHistory o\n"
                    + "ON p.productID = o.productID\n"
                    + "WHERE o.username = ?";
            stm = connection.prepareStatement(getProductSQL);
            stm.setString(1, username);
            rs = stm.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String catalogID = rs.getString("catalogID");
                String title = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, title, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Order> loadAllOrderedAccountByUsername(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String getAccountIDSQL = "SELECT * FROM OrdersHistory WHERE username = ? ORDER BY orderID DESC";
            stm = connection.prepareStatement(getAccountIDSQL);
            stm.setString(1, username);
            rs = stm.executeQuery();
            ArrayList<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int accountID = rs.getInt("accountID");
                int productID = rs.getInt("productID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                orderList.add(new Order(orderID, username, accountID, productID, email, password, dueDate));
            }
            return orderList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
     public int getNumberOrderd() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT COUNT(orderID) FROM OrdersHistory";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public boolean saveOrderHistory(String username, Account account) {
        PreparedStatement stm;
        try {
            String sql = "INSERT INTO OrdersHistory VALUES (?, ?, ?, ?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, account.getAccountID());
            stm.setString(3, account.getProductID());
            stm.setString(4, account.getEmail());
            stm.setString(5, account.getPassword());
            stm.setTimestamp(6, account.getDueDate());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public ArrayList<Order> getOrderedHistoryListByPaging(int pageNum) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String querySQL = "SELECT * FROM OrdersHistory ORDER BY orderID DESC OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY;";
            stm = connection.prepareStatement(querySQL);
            stm.setInt(1, (pageNum - 1) * 20);
            rs = stm.executeQuery();
            ArrayList<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String username = rs.getString("username");
                int accountID = rs.getInt("accountID");
                int productID = rs.getInt("productID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                orderList.add(new Order(orderID, username, accountID, productID, email, password, dueDate));
            }
            return orderList;
        } catch (SQLException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        OrdersDAO ord = new OrdersDAO();
        ArrayList<Order> ordL = ord.getOrderedHistoryListByPaging(2);
        int i = 1;
        for (Order order : ordL) {
            System.out.println(i +" - "+ order);
            i++;
        }
    }

}
