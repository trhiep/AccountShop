package servlet;

import DAO.CartDAO;
import DAO.StockDAO;
import entity.Cart;
import entity.Product;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author tranh
 */
@WebServlet(name = "ViewCartServlet", urlPatterns = {"/viewCart"})
public class ViewCartServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        if (userAcc != null) {
            String username = userAcc.getUsername();
            CartDAO cartDAO = new CartDAO();
            StockDAO stockDAO = new StockDAO();
            ArrayList<Cart> itemList = cartDAO.loadAllItemInCartByUsername(username);
            ArrayList<Product> productList = new ArrayList<>();
            HashMap<Integer, Integer> cartQuantityMap = new HashMap<>();
            for (Cart item : itemList) {
                Product prd = stockDAO.searchProductByID(item.getProductID() + "");
                productList.add(prd);
                cartQuantityMap.put(item.getProductID(), item.getQuantity());
            }
            session.setAttribute("cartQuantityMap", cartQuantityMap);
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("itemList", itemList);
            request.setAttribute("productList", productList);
            int itemsInCart = cartDAO.getNumberItemInCart(username);
            request.setAttribute("itemsInCart", itemsInCart);
            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
        } else {
            request.setAttribute("userAcc", userAcc);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        String username = userAcc.getUsername();
        CartDAO cartDAO = new CartDAO();
        StockDAO stockDAO = new StockDAO();
        ArrayList<Cart> itemList = cartDAO.loadAllItemInCartByUsername(username);
        ArrayList<Product> productList = new ArrayList<>();
        HashMap<Product, Integer> maxQuantity = new HashMap<>();
        for (Cart item : itemList) {
            Product prd = stockDAO.searchProductByID(item.getProductID() + "");
            int maxCanOrder = stockDAO.getAccountQuantityByProductID(prd.getProductID() + "");
            maxQuantity.put(prd, maxCanOrder);
            productList.add(prd);
        }

        request.setAttribute("userAcc", userAcc);
        request.setAttribute("itemList", itemList);
        request.setAttribute("productList", productList);
        request.setAttribute("maxQuantity", maxQuantity);
        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
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
