package servlet;

import DAO.CartDAO;
import DAO.OrdersDAO;
import DAO.StockDAO;
import DAO.UsersDAO;
import entity.Cart;
import entity.Product;
import entity.ProductRate;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author tranh
 */
public class ViewDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        String productID = request.getParameter("productID");
        StockDAO stockDAO = new StockDAO();
        Product product = stockDAO.searchProductByID(productID);
        if (product != null) {
            boolean isBought = false;
            if (userAcc != null) {
                OrdersDAO ordersDAO = new OrdersDAO();
                String username = userAcc.getUsername();
                ArrayList<Product> productList = ordersDAO.loadAllOrderedProductByUsername(username);
                if (productList != null) {
                    for (Product prd : productList) {
                        if (prd.getProductID() == Integer.parseInt(productID)) {
                            isBought = true;
                        }
                    }
                }
            }
            UsersDAO usersDAO = new UsersDAO();
            ArrayList<Users> usersList = usersDAO.loadAllUsers();
            ArrayList<ProductRate> productRateList = stockDAO.loadProductRateByProductID(Integer.parseInt(productID));
            boolean isRated = false;
            int totalStar = 0;
            for (ProductRate productRate : productRateList) {
                if (userAcc != null) {
                    if (productRate.getUsername().equals(userAcc.getUsername())) {
                        isRated = true;
                    }
                }
                totalStar += productRate.getStar();
            }
            if (!productRateList.isEmpty()) {
                float ratePoint = totalStar / productRateList.size();
                request.setAttribute("ratePoint", ratePoint);
            }

            if (userAcc != null) {
                boolean isOutOfRequest = false;
                CartDAO cartDAO = new CartDAO();
                ArrayList<Cart> itemList = cartDAO.loadAllItemInCartByUsername(userAcc.getUsername());
                for (Cart cartItem : itemList) {
                    if (cartItem.getProductID() == Integer.parseInt(productID)) {
                        int quantityOfProductInCart = cartItem.getQuantity();
                        if (quantityOfProductInCart >= product.getQuantity()) {
                            isOutOfRequest = true;
                        }
                    }
                }
                request.setAttribute("isOutOfRequest", isOutOfRequest);
                int itemsInCart = cartDAO.getNumberItemInCart(userAcc.getUsername());
                request.setAttribute("itemsInCart", itemsInCart);
            }
            
            ArrayList<Product> relatedProductList = stockDAO.getProductListByCatalogID(product.getCatalogID());

            request.setAttribute("productRateList", productRateList);
            request.setAttribute("relatedProductList", relatedProductList);
            request.setAttribute("usersList", usersList);
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("product", product);
            request.setAttribute("isBought", isBought);
            request.setAttribute("isRated", isRated);
            request.setAttribute("isExistProduct", true);
        } else {
            request.setAttribute("isExistProduct", false);
        }
        request.getRequestDispatcher("viewDetail.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
