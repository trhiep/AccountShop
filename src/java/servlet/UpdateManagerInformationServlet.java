package servlet;

import DAO.UsersDAO;
import entity.RoleInformation;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import utils.EmailContext;

/**
 *
 * @author tranh
 */
@WebServlet(name = "UpdateManagerInformationServlet", urlPatterns = {"/updateManagerInformation"})
public class UpdateManagerInformationServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateManagerInformationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateManagerInformationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        UsersDAO usersDAO = new UsersDAO();
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String newEmail = request.getParameter("email");
        if (!newEmail.equals(userAcc.getEmail()) && userAcc.isIsVerified() == true) {
            String generatedCode = EmailContext.generateRandomVerificationCode(6);
            boolean isSentVerificationCode = EmailContext.sendVerificationCodeToEmail(userAcc.getEmail(), generatedCode);
            if (isSentVerificationCode) {
                String oldEmail = request.getParameter("oldEmail");
                Users userInfor = new Users(username, password, fullname, oldEmail, userAcc.getRole(), userAcc.isIsVerified());
                session.setAttribute("userInfor", userInfor);
                session.setAttribute("generatedCode", generatedCode);
                session.setAttribute("newEmail", newEmail);
                request.setAttribute("isSuccess", false);
                request.getRequestDispatcher("changeEmailVerification.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("manager/changeManagerInformation.jsp").forward(request, response);
            }
        }
        usersDAO.updateUserByUsername(username, password, fullname, newEmail, userAcc.getRole(), userAcc.isIsVerified());
        userAcc = usersDAO.login(username, password);
        request.setAttribute("action", "Information");
        request.setAttribute("userAcc", userAcc);
        request.getRequestDispatcher("manager/changeManagerInformation.jsp").forward(request, response);
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
