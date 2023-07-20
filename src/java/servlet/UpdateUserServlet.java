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
import utils.EmailContext;

/**
 *
 * @author tranh
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/updateUser"})
public class UpdateUserServlet extends HttpServlet {

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
        Users userAcc = (Users) session.getAttribute("Users");
        request.setAttribute("action", "Information");
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
        String btAction = request.getParameter("btAction");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String newEmail = request.getParameter("email");
        String roleInStr = request.getParameter("role");
        String isVerifiedInStr = request.getParameter("isVerified");
        int role = Integer.parseInt(roleInStr);
        boolean isVerified = false;
        if (isVerifiedInStr.equals("Yes")) {
            isVerified = true;
        }
        if (userAcc.getRole() == 0) {
            if (!newEmail.equals(userAcc.getEmail()) && userAcc.isIsVerified() == true) {
                String generatedCode = EmailContext.generateRandomVerificationCode(6);
                boolean isSentVerificationCode = EmailContext.sendVerificationCodeToEmail(userAcc.getEmail(), generatedCode);
                if (isSentVerificationCode) {
                    String oldEmail = request.getParameter("oldEmail");
                    Users userInfor = new Users(username, password, fullname, oldEmail, role, isVerified);
                    session.setAttribute("userInfor", userInfor);
                    session.setAttribute("generatedCode", generatedCode);
                    session.setAttribute("newEmail", newEmail);
                    request.setAttribute("isSuccess", false);
                    request.getRequestDispatcher("changeEmailVerification.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("viewAccount").forward(request, response);
                }
            }
        }
        UsersDAO usersDAO = new UsersDAO();
        switch (btAction) {
            case "Update":
                usersDAO.updateUserByUsername(username, password, fullname, newEmail, role, isVerified);
                break;
            case "Delete":
                usersDAO.deleteUserByUsername(username);
                break;

        }
        ArrayList<Users> usersList = usersDAO.loadAllUsers();
        request.setAttribute("usersList", usersList);
        if (userAcc.getRole() != 0) {
            ArrayList<RoleInformation> roleList = usersDAO.loadAllRoleInformationList();
            request.setAttribute("roleList", roleList);
            request.setAttribute("userAcc", userAcc);
            request.getRequestDispatcher("manager/usersManager.jsp").forward(request, response);
        } else {
            userAcc = usersDAO.login(username, password);
            request.setAttribute("action", "Information");
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
