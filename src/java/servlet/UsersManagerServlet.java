package servlet;

import DAO.UsersDAO;
import entity.RoleInformation;
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
@WebServlet(name = "UsersManagerServlet", urlPatterns = {"/usersManager"})
public class UsersManagerServlet extends HttpServlet {

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

        if (userAcc == null || userAcc.getRole() != 1) {
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        } else {
            UsersDAO userDAO = new UsersDAO();
            ArrayList<Users> usersList = userDAO.loadAllUsers();
            ArrayList<RoleInformation> roleList = userDAO.loadAllRoleInformationList();
            request.setAttribute("userAcc", userAcc);
            request.setAttribute("usersList", usersList);
            request.setAttribute("roleList", roleList);
            switch (userAcc.getRole()) {
                case 1:
                    request.getRequestDispatcher("manager/usersManager.jsp").forward(request, response);
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
