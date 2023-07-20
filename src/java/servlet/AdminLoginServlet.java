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
@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/adminLogin"})
public class AdminLoginServlet extends HttpServlet {

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
        String MESSAGE = "WARNING<br>"
                + "ONLY ADMIN CAN CONTINUE TO ACCESS";
        request.setAttribute("MESSAGE", MESSAGE);
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
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
            String MESSAGE = "Users does not exist!";
            request.setAttribute("MESSAGE", MESSAGE);
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        } else if (userAcc.getRole() == 0) {
            String MESSAGE = "You do not have permission to access the management page!";
            request.setAttribute("MESSAGE", MESSAGE);
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Users", userAcc);
            StockDAO stockDAO = new StockDAO();
            ArrayList<Product> productList = stockDAO.loadAllProduct();
            for (Product product : productList) {
                stockDAO.deleteExpiredAccountByProduct(product);
            }
            switch (userAcc.getRole()) {
                case 1:
                    request.getRequestDispatcher("usersManager").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("stockManager").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("balanceManager").forward(request, response);
                    break;
            }
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
