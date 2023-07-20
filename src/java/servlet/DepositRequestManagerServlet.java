package servlet;

import DAO.BalanceDAO;
import DAO.UsersDAO;
import entity.DepositHistory;
import entity.DepositRequest;
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
@WebServlet(name = "DepositManagerServlet", urlPatterns = {"/depositRequestManager"})
public class DepositRequestManagerServlet extends HttpServlet {

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
        response.sendRedirect("homepage");
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
        UsersDAO userDAO = new UsersDAO();
        ArrayList<Users> usersList = userDAO.loadAllUsers();
        if (userAcc == null || userAcc.getRole() != 1 || userAcc.getRole() != 4) {
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        } else {
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("usersList", usersList);
            request.getRequestDispatcher("manager/usersManager.jsp").forward(request, response);
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
        String btAction = request.getParameter("btAction");
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String amount = request.getParameter("amount");
        String requestDate = request.getParameter("requestDate");
        String reason = request.getParameter("reason");
        String actionBy = userAcc.getUsername();
        BalanceDAO balanceDAO = new BalanceDAO();
        switch (btAction) {
            case "Accept":
                reason = "Completed payment!";
                balanceDAO.acceptDeposit(id, username, amount, requestDate, reason, actionBy);
                break;
            case "Deny":
                if (reason.equals("")) {
                    reason = "Payment has not been completed!";
                }
                balanceDAO.denyDeposit(id, username, amount, requestDate, reason, actionBy);
                break;

        }
        ArrayList<DepositRequest> requestList = balanceDAO.loadAllDepositRequest();
        ArrayList<DepositHistory> depositHistoryList = balanceDAO.loadAllDepositHistory();
        request.setAttribute("userAcc", userAcc);
        request.setAttribute("requestList", requestList);
        request.setAttribute("depositHistoryList", depositHistoryList);
        request.getRequestDispatcher("manager/balanceManager.jsp").forward(request, response);
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
