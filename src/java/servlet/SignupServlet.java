package servlet;

import DAO.UsersDAO;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author tranh
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {

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
        request.setAttribute("signupFailed", true);
        request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        UsersDAO usersDAO = new UsersDAO();
        boolean isExistedUser = usersDAO.checkExistsUsername(username);
        if (isExistedUser == true) {
            String UsernameErr = "Username has already exists!";
            request.setAttribute("UsernameErr", UsernameErr);
            request.setAttribute("signupFailed", true);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else if (!password.equals(rePassword)) {
            String RePasswordErr = "Password repeat is not matched!";
            request.setAttribute("RePasswordErr", RePasswordErr);
            request.setAttribute("signupFailed", true);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else {
            UsersDAO userDAO = new UsersDAO();
            boolean isAdded = userDAO.addNewUser(username, password, fullname, email, 0, false);
            if (isAdded == true) {
                Users newUserInformation = new Users(username, password, fullname, email, 0, false);
                HttpSession session = request.getSession();
                session.setAttribute("userInformation", newUserInformation);
                request.setAttribute("signupFailed", false);
                request.setAttribute("isSuccess", true);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else {
                request.setAttribute("isSuccess", false);
                request.setAttribute("signupFailed", true);
                request.getRequestDispatcher("signup.jsp").forward(request, response);
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
