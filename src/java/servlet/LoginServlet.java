package servlet;

import DAO.StockDAO;
import DAO.UsersDAO;
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

/**
 *
 * @author tranh
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        Users user = (Users) session.getAttribute("Users");
        if (user != null) {
            response.sendRedirect("homepage");
        } else {
            response.sendRedirect("login.jsp");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsersDAO usersDAO = new UsersDAO();
        Users userAcc = usersDAO.login(username, password);
        if (userAcc == null) {
            String LMESSAGE = "Users does not exist!";
            request.setAttribute("LMESSAGE", LMESSAGE);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Users", userAcc);
            StockDAO stockDAO = new StockDAO();
            ArrayList<Product> productList = stockDAO.loadAllProduct();
            for (Product product : productList) {
                stockDAO.deleteExpiredAccountByProduct(product);
            }
            if (userAcc.getRole() != 0) {
                switch (userAcc.getRole()) {
                    case 1:
                        response.sendRedirect("usersManager");
                        break;
                    case 2:
                        response.sendRedirect("stockManager");
                        break;
                    case 3:
                        response.sendRedirect("balanceManager");
                        break;
                }
            }
            response.sendRedirect("homepage");
        }
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
