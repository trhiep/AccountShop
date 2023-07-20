package servlet;

import DAO.UsersDAO;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.EmailContext;

/**
 *
 * @author tranh
 */
@WebServlet(name = "EmailSignupVerificationServlet", urlPatterns = {"/emailSignupVerification"})
public class EmailSignupVerificationServlet extends HttpServlet {

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
            out.println("<title>Servlet EmailSignupVerificationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmailSignupVerificationServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInformation");
        if (user == null) {
            user = (Users) session.getAttribute("Users");
        }
        String generatedCode = EmailContext.generateRandomVerificationCode(6);
        boolean isSentVerificationCode = EmailContext.sendVerificationCodeToEmail(user.getEmail(), generatedCode);
        if (isSentVerificationCode == true) {
            session.setAttribute("generatedCode", generatedCode);
            session.setAttribute("userInformation", user);
            request.setAttribute("isSuccess", false);
            request.getRequestDispatcher("emailVerification.jsp").forward(request, response);
        } else {
            request.setAttribute("isSentCodeFailed", true);
            request.setAttribute("isSuccess", false);
            session.setAttribute("userInformation", user);
            request.getRequestDispatcher("emailVerification.jsp").forward(request, response);
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
        String generatedCode = (String) session.getAttribute("generatedCode");
        Users userInformation = (Users) session.getAttribute("userInformation");
        String enteredCode = request.getParameter("enteredCode");
        if (generatedCode.equals(enteredCode)) {
            UsersDAO usersDAO = new UsersDAO();
            usersDAO.updateUserByUsername(userInformation.getUsername(), userInformation.getPassword(), userInformation.getFullname(), userInformation.getEmail(), userInformation.getRole(), true);
            request.setAttribute("email", userInformation.getEmail());
            request.setAttribute("successVerifyMess", "Email verified!");
            request.setAttribute("isSuccess", true);
            Users getLoginUser = (Users) session.getAttribute("Users");
            boolean isLoggedin = false; 
            if (getLoginUser != null) {
                isLoggedin = true;
            }
            request.setAttribute("isLoggedin", isLoggedin);
            session.removeAttribute("userInformation");
            session.removeAttribute("generatedCode");
            request.getRequestDispatcher("emailVerification.jsp").forward(request, response);
        } else {
            request.setAttribute("wrongCode", "Wrong code!");
            request.setAttribute("isSuccess", false);
            request.getRequestDispatcher("emailVerification.jsp").forward(request, response);
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
