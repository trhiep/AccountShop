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

/**
 *
 * @author tranh
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changePassword"})
public class ChangePasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
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
        Users userAcc = (Users) session.getAttribute("Users");
        request.setAttribute("action", "Security");
        request.setAttribute("userAcc", userAcc);
        request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        boolean isVerificated = userAcc.isIsVerified();
        if (oldPassword.equals(userAcc.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                String password = newPassword;
                UsersDAO usersDAO = new UsersDAO();
                usersDAO.updateUserByUsername(username, password, fullname, email, userAcc.getRole(), isVerificated);
                userAcc = usersDAO.login(username, password);
                request.setAttribute("successMessage", "Change password successfully!");
                request.setAttribute("action", "Security");
                request.setAttribute("userAcc", userAcc);
                request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
            } else {
                request.setAttribute("confirmPassErr", "Confirm password is wrong!");
                request.setAttribute("action", "Security");
                request.setAttribute("userAcc", userAcc);
                request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("oldPassErr", "Wrong password!");
            request.setAttribute("action", "Security");
            request.setAttribute("userAcc", userAcc);
            request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
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
