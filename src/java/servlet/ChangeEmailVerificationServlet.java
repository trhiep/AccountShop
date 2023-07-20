
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
@WebServlet(name="ChangeEmailVerificationServlet", urlPatterns={"/changeEmailVerification"})
public class ChangeEmailVerificationServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeEmailVerificationServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeEmailVerificationServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        Users userInfor = (Users) session.getAttribute("userInfor");
        String newEmail = request.getParameter("newEmail");
        String enteredCode = request.getParameter("enteredCode");
        if (generatedCode.equals(enteredCode)) {
            UsersDAO usersDAO = new UsersDAO();
            usersDAO.updateUserByUsername(userInfor.getUsername(), userInfor.getPassword(), userInfor.getFullname(), newEmail, userInfor.getRole(), false);
            session.removeAttribute("userInfor");
            session.removeAttribute("generatedCode");
            Users userAcc = usersDAO.login(userInfor.getUsername(), userInfor.getPassword());
            request.setAttribute("action", "Information");
            request.setAttribute("userAcc", userAcc);
            if (userAcc.getRole() == 0) {
                request.getRequestDispatcher("viewAccount.jsp").forward(request, response);
            } else {
                response.sendRedirect("changeManagerInformation");
            }
            
        } else {
            request.setAttribute("wrongCode", "Wrong code!");
            request.setAttribute("oldEmail", userInfor.getEmail());
            request.setAttribute("isSuccess", false);
            request.getRequestDispatcher("changeEmailVerification.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
