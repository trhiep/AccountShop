package servlet;

import DAO.BalanceDAO;
import DAO.CartDAO;
import entity.DepositHistory;
import entity.DepositRequest;
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
@WebServlet(name = "DepositServlet", urlPatterns = {"/deposit"})
public class DepositServlet extends HttpServlet {

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
        BalanceDAO balanceDAO = new BalanceDAO();
        if (userAcc != null) {
            if (userAcc.getRole() != 0) {
                response.sendRedirect("usersManager");
            }
            String username = userAcc.getUsername();
            UserBalance userBalance = balanceDAO.getBalanceByUsername(username);
            ArrayList<DepositHistory> depositHistoryList = balanceDAO.loadDepositHistoryByUsername(username);
            ArrayList<DepositRequest> requestingList = balanceDAO.loadDepositRequestingByUsername(username);
            request.setAttribute("userBalance", userBalance);
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("depositHistoryList", depositHistoryList);
            request.setAttribute("requestingList", requestingList);
            CartDAO cartDAO = new CartDAO();
            int itemsInCart = cartDAO.getNumberItemInCart(username);
            request.setAttribute("itemsInCart", itemsInCart);
            request.getRequestDispatcher("deposit.jsp").forward(request, response);
        } else {
            request.setAttribute("userAcc", userAcc);
            request.getRequestDispatcher("login").forward(request, response);
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
        String amount = request.getParameter("amount");
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        String username = userAcc.getUsername();
        BalanceDAO balanceDAO = new BalanceDAO();
        balanceDAO.requestDeposit(username, amount);
        response.sendRedirect("deposit");
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
