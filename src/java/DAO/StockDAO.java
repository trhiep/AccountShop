package DAO;

import entity.Account;
import entity.Catalog;
import entity.Product;
import entity.ProductRate;
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
public class StockDAO extends DBContext {

    public ArrayList<Catalog> loadAllCatalog() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM CatalogList";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<Catalog> catalogList = new ArrayList<>();
            while (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String catalogName = rs.getString("catalogDisplayName");
                String catalogImg = rs.getString("catalogImg");
                Catalog catalog = new Catalog(catalogID, catalogName, catalogImg);
                catalogList.add(catalog);
            }
            return catalogList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Account> loadAllAccount() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM AccountList ORDER BY productID";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<Account> accountList = new ArrayList<>();
            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                String productID = rs.getString("productID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                Account account = new Account(accountID, productID, email, password, dueDate);
                accountList.add(account);
            }
            return accountList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Product> loadAllProduct() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM ProductList";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String name = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                int productID = rs.getInt("productID");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, name, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<ProductRate> loadAllProductRate() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM ProductRate";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<ProductRate> productRateList = new ArrayList<>();
            while (rs.next()) {
                int rateID = rs.getInt("rateID");
                String username = rs.getString("username");
                int productID = rs.getInt("productID");
                int star = rs.getInt("star");
                String comment = rs.getString("comment");
                Timestamp rateDate = rs.getTimestamp("rateDate");
                productRateList.add(new ProductRate(rateID, username, productID, star, comment, rateDate));
            }
            return productRateList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<ProductRate> loadProductRateByProductID(int productID) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM ProductRate WHERE productID = ? ORDER BY rateDate DESC";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
            rs = stm.executeQuery();
            ArrayList<ProductRate> productRateList = new ArrayList<>();
            while (rs.next()) {
                int rateID = rs.getInt("rateID");
                String username = rs.getString("username");
                int star = rs.getInt("star");
                String comment = rs.getString("comment");
                Timestamp rateDate = rs.getTimestamp("rateDate");
                productRateList.add(new ProductRate(rateID, username, productID, star, comment, rateDate));
            }
            return productRateList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteProductRateByID(String rateID) {
        PreparedStatement stm;
        try {
            String deleteInAccountListSQL = "DELETE FROM ProductRate WHERE rateID = ?";
            stm = connection.prepareStatement(deleteInAccountListSQL);
            stm.setString(1, rateID);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addNewCatalog(String id, String name, String img) {
        PreparedStatement stm;
        try {
            String sql = "INSERT INTO CatalogList VALUES (?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, img);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addNewProduct(String catalogID, String title, String type, String price, String shortDetail, String longDetail, String productImg) {
        PreparedStatement stm;
        try {
            String sql = "INSERT INTO ProductList VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, catalogID);
            stm.setString(2, title);
            stm.setString(3, type);
            stm.setString(4, price);
            stm.setString(5, shortDetail);
            stm.setString(6, longDetail);
            stm.setString(7, productImg);
            stm.setInt(8, 0);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addNewAccount(String productID, String email, String password, String dueDate) {
        PreparedStatement stm;
        try {
            int oldQuantity = getAccountQuantityByProductID(productID);
            int newQuantity = 0;
            if (oldQuantity != 0) {
                newQuantity = oldQuantity + 1;
            } else {
                newQuantity = 1;
            }

            String insertToAccountListSQL = "INSERT INTO AccountList VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(insertToAccountListSQL);
            stm.setString(1, productID);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setString(4, dueDate);
            stm.executeUpdate();

            String updateProductQuantitySQL = "UPDATE ProductList SET quantity = ? WHERE productID = ?";
            stm = connection.prepareStatement(updateProductQuantitySQL);
            stm.setInt(1, newQuantity);
            stm.setString(2, productID);
            stm.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addNewProductRate(String username, int productID, int star, String comment) {
        PreparedStatement stm;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String rateDate = timestamp.toString();
        try {
            String sql = "INSERT INTO ProductRate VALUES (?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, productID);
            stm.setInt(3, star);
            stm.setString(4, comment);
            stm.setString(5, rateDate);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateCatalogByID(String catalogID, String catalogName, String catalogImg) {
        PreparedStatement stm;
        try {
            String sql = "UPDATE CatalogList "
                    + "SET catalogDisplayName = ?, catalogImg = ?"
                    + " WHERE catalogID =?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, catalogName);
            stm.setString(2, catalogImg);
            stm.setString(3, catalogID);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteCatalogByID(String catalogID) {
        PreparedStatement stm;
        ResultSet rs;
        try {

            ArrayList<Product> productList = getAllProductsByCatalogID(catalogID);
            for (Product product : productList) {
                deleteProductByID(product.getProductID()+"");
            }
            String deleteInCatalogListSQL = "DELETE FROM CatalogList WHERE catalogID = ?";
            stm = connection.prepareStatement(deleteInCatalogListSQL);
            stm.setString(1, catalogID);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateProductByID(String productID, String catalogID, String title, String type, int price, String shortDetail, String longDetail, String productImg, int quantity) {
        PreparedStatement stm;
        try {
            String sql = "UPDATE ProductList \n"
                    + "  SET catalogID = ?, title = ?, type = ?, price = ?, shortDetail = ?, longDetail = ?, productImg = ?, quantity = ?\n"
                    + "  WHERE productID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, catalogID);
            stm.setString(2, title);
            stm.setString(3, type);
            stm.setInt(4, price);
            stm.setString(5, shortDetail);
            stm.setString(6, longDetail);
            stm.setString(7, productImg);
            stm.setInt(8, quantity);
            stm.setString(9, productID);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteProductByID(String productID) {
        PreparedStatement stm;
        try {
            String deleteInAccountListSQL = "DELETE FROM AccountList WHERE productID = ?";
            stm = connection.prepareStatement(deleteInAccountListSQL);
            stm.setString(1, productID);
            stm.executeUpdate();

            String deleteInProductRateSQL = "DELETE FROM ProductRate WHERE productID = ?";
            stm = connection.prepareStatement(deleteInProductRateSQL);
            stm.setString(1, productID);
            stm.executeUpdate();

            String deleteProductInCartSQL = "DELETE FROM Cart WHERE productID = ?";
            stm = connection.prepareStatement(deleteProductInCartSQL);
            stm.setString(1, productID);
            stm.executeUpdate();

            String deleteProductInHistorySQL = "DELETE FROM OrdersHistory WHERE productID = ?";
            stm = connection.prepareStatement(deleteProductInHistorySQL);
            stm.setString(1, productID);
            stm.executeUpdate();

            String deleteInProductListSQL = "DELETE FROM ProductList WHERE productID = ?";
            stm = connection.prepareStatement(deleteInProductListSQL);
            stm.setString(1, productID);
            stm.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateAccountByID(String accountID, String productID, String email, String password, String dueDate) {
        PreparedStatement stm;
        try {
            String sql = "UPDATE AccountList \n"
                    + "  SET productID = ?, email = ?, password = ?, dueDate = ?\n"
                    + "  WHERE accountID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, productID);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setString(4, dueDate);
            stm.setString(5, accountID);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public String getProductIDByAccountID(String accountID) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT productID FROM AccountList WHERE accountID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, accountID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteAccountByID(String accountID) {
        PreparedStatement stm;
        try {
            String productID = getProductIDByAccountID(accountID);
            int oldQuantity = getAccountQuantityByProductID(productID);
            int newQuantity = oldQuantity - 1;

            String deleteInAccountListSQL = "DELETE FROM AccountList WHERE accountID = ?";
            stm = connection.prepareStatement(deleteInAccountListSQL);
            stm.setString(1, accountID);
            stm.executeUpdate();

            String updateProductQuantitySQL = "UPDATE ProductList SET quantity = ? WHERE productID = ?";
            stm = connection.prepareStatement(updateProductQuantitySQL);
            stm.setInt(1, newQuantity);
            stm.setString(2, productID);
            stm.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteExpiredAccountByProduct(Product product) {
        PreparedStatement stm;
        String accType = product.getType();
        char front = accType.charAt(0);
        char after = accType.charAt(1);
        int afterValue = 1;
        if (after == 'm') {
            afterValue = 30;
        } else if (after == 'y') {
            afterValue = 365;
        }
        int frontValue = Integer.parseInt(front + "");
        int dateRemain = frontValue * afterValue;
        try {
            StockDAO stockDAO = new StockDAO();
            ArrayList<Account> accountList = stockDAO.getAccountListByProductID(product.getProductID() + "");
            for (Account account : accountList) {
                int oldQuantity = getAccountQuantityByProductID(product.getProductID() + "");
                String deleteInAccountListSQL = "DELETE FROM AccountList WHERE accountID = ? AND DATEDIFF(day, GETDATE(), dueDate) <= ?";
                stm = connection.prepareStatement(deleteInAccountListSQL);
                stm.setString(1, account.getAccountID() + "");
                stm.setInt(2, dateRemain - 5);
                int affectedRows = stm.executeUpdate();
                int newQuantity = oldQuantity - affectedRows;
                String updateProductQuantitySQL = "UPDATE ProductList SET quantity = ? WHERE productID = ?";
                stm = connection.prepareStatement(updateProductQuantitySQL);
                stm.setInt(1, newQuantity);
                stm.setString(2, product.getProductID() + "");
                stm.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Product searchProductByID(String productID) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM ProductList WHERE productID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String title = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                int productIDInt = Integer.parseInt(productID);
                Product product = new Product(productIDInt, catalogID, title, type, price, shortDetail, longDetail, productImg, quantity);
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account searchAccountByID(int accountID) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM AccountList WHERE accountID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String productID = rs.getString("productID");
                String emailAcc = rs.getString("email");
                String passwordAcc = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                Account account = new Account(accountID, productID, emailAcc, passwordAcc, dueDate);
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Account> takeAccountByProductID(Product product, int quantity) {
        PreparedStatement stm;
        ResultSet rs;

        String accType = product.getType();
        char front = accType.charAt(0);
        char after = accType.charAt(1);
        int afterValue = 1;
        if (after == 'm') {
            afterValue = 30;
        } else if (after == 'y') {
            afterValue = 365;
        }
        int frontValue = Integer.parseInt(front + "");
        int dateRemain = frontValue * afterValue;

        try {
            String sql = "SELECT TOP(?) * FROM AccountList WHERE productID = ? AND DATEDIFF(day, GETDATE(), dueDate) > ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setString(2, product.getProductID() + "");
            stm.setInt(3, dateRemain - 5);
            rs = stm.executeQuery();
            ArrayList<Account> accountList = new ArrayList<>();
            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                String productID = rs.getString("productID");
                String emailAcc = rs.getString("email");
                String passwordAcc = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                Account account = new Account(accountID, productID, emailAcc, passwordAcc, dueDate);
                accountList.add(account);
            }
            return accountList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // NHO FIX CAI NAY NHE
    public int getAccountQuantityByProductID(String productID) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String getQuantitySQL = "SELECT COUNT(productID) FROM AccountList WHERE productID = ?";
            stm = connection.prepareStatement(getQuantitySQL);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt(1);
                return quantity;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public ArrayList<Catalog> getRandomProduct(int quantity) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String querySQL = "SELECT TOP(?) * FROM CatalogList ORDER BY NEWID()";
            stm = connection.prepareStatement(querySQL);
            stm.setInt(1, quantity);
            rs = stm.executeQuery();
            ArrayList<Catalog> catalogList = new ArrayList<>();
            while (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String catalogName = rs.getString("catalogDisplayName");
                String catalogImg = rs.getString("catalogImg");
                catalogList.add(new Catalog(catalogID, catalogName, catalogImg));
            }
            return catalogList;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Product> getAllTotalAvailableProduct() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM ProductList WHERE quantity > 0";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String name = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                int productID = rs.getInt("productID");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, name, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Product> getProductListByPaging(int pageNum) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String querySQL = "SELECT * FROM ProductList WHERE quantity > 0 ORDER BY productID OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
            stm = connection.prepareStatement(querySQL);
            stm.setInt(1, (pageNum - 1) * 8);
            rs = stm.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String name = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                int productID = rs.getInt("productID");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, name, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Product> getProductListByName(String productName) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ProductList WHERE title LIKE ? AND quantity > 0";
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + productName + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String catalogID = rs.getString("catalogID");
                String name = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                int productID = rs.getInt("productID");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, name, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Product> getProductListByCatalogID(String catalogID) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ProductList WHERE catalogID = ? AND quantity > 0";
            stm = connection.prepareStatement(sql);
            stm.setString(1, catalogID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                int productID = rs.getInt("productID");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, name, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<Product> getAllProductsByCatalogID(String catalogID) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ProductList WHERE catalogID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, catalogID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("title");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                String shortDetail = rs.getString("shortDetail");
                String longDetail = rs.getString("longDetail");
                int productID = rs.getInt("productID");
                String productImg = rs.getString("productImg");
                int quantity = rs.getInt("quantity");
                Product product = new Product(productID, catalogID, name, type, price, shortDetail, longDetail, productImg, quantity);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Account> getAccountListByProductID(String productID) {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AccountList WHERE productID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                String emailAcc = rs.getString("email");
                String passwordAcc = rs.getString("password");
                Timestamp dueDate = rs.getTimestamp("dueDate");
                Account account = new Account(accountID, productID, emailAcc, passwordAcc, dueDate);
                accountList.add(account);
            }
            return accountList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        StockDAO stockDAO = new StockDAO();
        ArrayList<Product> productList = stockDAO.getProductListByCatalogID("win11pro");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

}
