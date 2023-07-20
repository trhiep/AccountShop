package servlet;

import DAO.UsersDAO;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.EmailContext;

/**
 *
 * @author tranh
 */
public class ForgotPasswordServlet extends HttpServlet {

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
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        UsersDAO usersDAO = new UsersDAO();
        boolean isExisted = usersDAO.checkExistsUsername(username);
        if (isExisted == false) {
            request.setAttribute("errMsg", "Username does not exist!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        } else {
            Users user = usersDAO.searchUserByUsername(username);
            if (user.isIsVerified()) {
                String verificationCode = EmailContext.generateRandomVerificationCode(6);
                boolean isSentCaptcha = EmailContext.sendVerificationCode(username, verificationCode);
                request.setAttribute("username", username);
                request.setAttribute("isSentCaptcha", isSentCaptcha);
                session.setAttribute("generatedCode", verificationCode);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("username", username);
                request.setAttribute("isNotVerified", true);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
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
