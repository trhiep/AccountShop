package DAO;

import entity.Cart;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author tranh
 */
public class CartDAO extends DBContext {

    public boolean addToCart(String username, String productID, String quantity, int total) {
        PreparedStatement stm;
        boolean isNewRecord = true;
        int oldQuantity = 0;
        CartDAO cartDAO = new CartDAO();
        ArrayList<Cart> itemList = cartDAO.loadAllItemInCartByUsername(username);
        for (Cart item : itemList) {
            if (item.getProductID() == Integer.parseInt(productID)) {
                isNewRecord = false;
                oldQuantity = item.getQuantity();
            }
        }
        if (isNewRecord == false) {
            try {
                String sql = "DELETE FROM Cart WHERE username = ? AND productID = ?";
                stm = connection.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, productID);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        int quantityInt = Integer.parseInt(quantity) + oldQuantity;
        try {
            String sql = "INSERT INTO Cart VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, productID);
            stm.setInt(3, quantityInt);
            stm.setInt(4, total);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

    public ArrayList<Cart> loadAllItemInCartByUsername(String user) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Cart WHERE username = ? ORDER BY productID";
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            rs = stm.executeQuery();
            ArrayList<Cart> cartList = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("username");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                Cart itemInCart = new Cart(username, productID, quantity, total);
                cartList.add(itemInCart);
            }
            return cartList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteInCart(String username, String productID) {
        PreparedStatement stm;
        try {
            String sql = "DELETE FROM Cart WHERE username = ? AND productID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, productID);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean decreaseItemInCart(String username, String productID) {
        PreparedStatement stm;
        ResultSet rs;
        CartDAO cartDAO = new CartDAO();
        try {
            String selectProductInCartSQL = "SELECT * FROM Cart WHERE username = ? and productID = ?";
            stm = connection.prepareStatement(selectProductInCartSQL);
            stm.setString(1, username);
            stm.setString(2, productID);
            rs = stm.executeQuery();
            Cart cart = new Cart();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                cart = new Cart(username, Integer.parseInt(productID), quantity, total);
            }

            if (cart.getQuantity() == 1) {
                cartDAO.deleteInCart(username, productID);
            } else if (cart.getQuantity() > 1) {
                int quantity = cart.getQuantity() - 1;
                String sql = "UPDATE Cart SET quantity = ? WHERE username = ? and productID = ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, username);
                stm.setString(3, productID);
                stm.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public int getNumberItemInCart(String username) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT COUNT(productID) FROM Cart WHERE username = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        CartDAO dao = new CartDAO();
        int i = dao.getNumberItemInCart("tranhiwp");
        System.out.println(i);
    }

}
