package servlet;

import DAO.StockDAO;
import entity.Account;
import entity.Catalog;
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
@WebServlet(name = "UpdateCatalogServlet", urlPatterns = {"/updateCatalog"})
public class UpdateCatalogServlet extends HttpServlet {

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
        response.sendRedirect("homepage");
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
        String btAction = request.getParameter("btAction");
        String catalogID = request.getParameter("catalogID");
        String catalogName = request.getParameter("catalogName");
        String catalogImg = request.getParameter("catalogImg");
        StockDAO stockDAO = new StockDAO();
        boolean isSuccess = false;
        switch (btAction) {
            case "Update":
                isSuccess = stockDAO.updateCatalogByID(catalogID, catalogName, catalogImg);
                break;
            case "Delete":
                isSuccess = stockDAO.deleteCatalogByID(catalogID);
                break;
        }
        String MESSAGE;
        if (isSuccess == true) {
            MESSAGE = "Update catalog successfully!";
        } else {
            MESSAGE = "An error occured when update this catalog!";
        }
        ArrayList<Catalog> catalogList = stockDAO.loadAllCatalog();
        ArrayList<Account> accountList = stockDAO.loadAllAccount();
        ArrayList<Product> productList = stockDAO.loadAllProduct();
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        request.setAttribute("userAcc", userAcc);
        request.setAttribute("catalogList", catalogList);
        request.setAttribute("action", "Catalog List");
        request.setAttribute("accountList", accountList);
        request.setAttribute("productList", productList);
        request.setAttribute("UPDATECATALOGMESSAGE", MESSAGE);
        request.getRequestDispatcher("manager/stockManager.jsp").forward(request, response);
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
