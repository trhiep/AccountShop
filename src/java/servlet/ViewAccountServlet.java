package servlet;

import DAO.BalanceDAO;
import DAO.CartDAO;
import DAO.OrdersDAO;
import DAO.StockDAO;
import DAO.UsersDAO;
import entity.BalanceHistory;
import entity.DepositHistory;
import entity.DepositRequest;
import entity.Order;
import entity.Product;
import entity.UserBalance;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author tranh
 */
@WebServlet(name = "ViewAccountServlet", urlPatterns = {"/viewAccount"})
public class ViewAccountServlet extends HttpServlet {

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
            BalanceDAO balanceDAO = new BalanceDAO();
            UsersDAO userDAO = new UsersDAO();
            String username = userAcc.getUsername();
            Users user = userDAO.searchUserByUsername(username);
            UserBalance userBalance = balanceDAO.getBalanceByUsername(username);
            ArrayList<DepositHistory> depositHistoryList = balanceDAO.loadDepositHistoryByUsername(username);
            ArrayList<DepositRequest> requestingList = balanceDAO.loadDepositRequestingByUsername(username);
            session.setAttribute("Users", user);
            request.setAttribute("userBalance", userBalance);
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("depositHistoryList", depositHistoryList);
            request.setAttribute("requestingList", requestingList);
            request.setAttribute("action", "Information");
            CartDAO cartDAO = new CartDAO();
            int itemsInCart = cartDAO.getNumberItemInCart(username);
            request.setAttribute("itemsInCart", itemsInCart);
            request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
        } else {
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
        String action = request.getParameter("btAction");
        if (action.equals("Ordered")) {
            OrdersDAO ordersDAO = new OrdersDAO();
            ArrayList<Order> orderList = ordersDAO.loadAllOrderedAccountByUsername(userAcc.getUsername());
            StockDAO stockDAO = new StockDAO();
            ArrayList<Product> productList = stockDAO.loadAllProduct();
            request.setAttribute("orderList", orderList);
            request.setAttribute("productList", productList);
        } else if (action.equals("Balance")) {
            BalanceDAO balanceDAO = new BalanceDAO();
            ArrayList<BalanceHistory> balanceHistoryList = balanceDAO.loadBalanceHistoryByUsername(userAcc.getUsername());
            request.setAttribute("balanceHistoryList", balanceHistoryList);
        }
        UsersDAO userDAO = new UsersDAO();
        String username = userAcc.getUsername();
        Users user = userDAO.searchUserByUsername(username);
        session.setAttribute("Users", user);
        request.setAttribute("action", action);
        request.setAttribute("userAcc", userAcc);
        CartDAO cartDAO = new CartDAO();
        int itemsInCart = cartDAO.getNumberItemInCart(username);
        request.setAttribute("itemsInCart", itemsInCart);
        request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
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
