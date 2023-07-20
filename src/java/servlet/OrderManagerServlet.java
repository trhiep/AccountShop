package servlet;

import DAO.OrdersDAO;
import entity.Order;
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
@WebServlet(name = "OrderManagerServlet", urlPatterns = {"/orderManager"})
public class OrderManagerServlet extends HttpServlet {

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
        OrdersDAO ordersDAO = new OrdersDAO();
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        if (userAcc == null || userAcc.getRole() != 1 && userAcc.getRole() != 3) {
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        } else {
            String pageStr = request.getParameter("page");
            int page = 1;
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            int numberOfOrdered = ordersDAO.getNumberOrderd();
            int endPage = numberOfOrdered / 20;
            if (numberOfOrdered % 20 != 0) {
                endPage++;
            }
            ArrayList<Order> ordersHistoryList = ordersDAO.getOrderedHistoryListByPaging(page);
            request.setAttribute("endPage", endPage);
            request.setAttribute("totalOrdered", numberOfOrdered);
            request.setAttribute("page", page);
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("ordersHistoryList", ordersHistoryList);
            request.getRequestDispatcher("manager/orderManager.jsp").forward(request, response);
        }
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
