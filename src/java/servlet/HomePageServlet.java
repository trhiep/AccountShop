package servlet;

import DAO.CartDAO;
import DAO.StockDAO;
import DAO.UsersDAO;
import entity.Catalog;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author tranh
 */
public class HomePageServlet extends HttpServlet {

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
        if (userAcc != null) {
            if (userAcc.getRole() != 0) {
                response.sendRedirect("usersManager");
            }
            UsersDAO usersDAO = new UsersDAO();
            String username = userAcc.getUsername();
            Users userReload = usersDAO.searchUserByUsername(username);
            session.setAttribute("Users", userReload);
            CartDAO cartDAO = new CartDAO();
            int itemsInCart = cartDAO.getNumberItemInCart(username);
            request.setAttribute("itemsInCart", itemsInCart);
        }
        String backgroundImg = getServletContext().getInitParameter("homepageImg");
        String hompageCatalog = getServletContext().getInitParameter("hompageCatalog");
        StockDAO stockDAO = new StockDAO();
        ArrayList<Catalog> calalogList = stockDAO.getRandomProduct(5);
        request.setAttribute("calalogList", calalogList);
        request.setAttribute("backgroundImg", backgroundImg);
        request.setAttribute("hompageCatalog", hompageCatalog);
        request.setAttribute("userAcc", userAcc);
        request.getRequestDispatcher("HomePage.jsp").forward(request, response);
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
