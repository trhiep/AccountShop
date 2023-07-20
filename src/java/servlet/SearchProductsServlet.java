package servlet;

import DAO.StockDAO;
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
@WebServlet(name = "SearchProductsServlet", urlPatterns = {"/searchProducts"})
public class SearchProductsServlet extends HttpServlet {

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
        StockDAO stockDAO = new StockDAO();
        String btAction = request.getParameter("btAction");
        if (btAction.equals("Search By Name")) {
            String productName = request.getParameter("productName");
            ArrayList<Product> productListByName = stockDAO.getProductListByName(productName);
            request.setAttribute("productList", productListByName);
            request.setAttribute("oldRequest", productName);
        } else if (btAction.equals("Search By Catalog") || btAction.equals("searchByCatalogID")) {
            String catalogID = request.getParameter("catalogID");
            ArrayList<Product> productListByCatalogID = stockDAO.getProductListByCatalogID(catalogID);
            request.setAttribute("productList", productListByCatalogID);
            request.setAttribute("oldRequestCatalogID", catalogID);
        }
        ArrayList<Catalog> catalogList = stockDAO.loadAllCatalog();
        request.setAttribute("userAcc", userAcc);
        request.setAttribute("catalogList", catalogList);
        request.getRequestDispatcher("searchProducts.jsp").forward(request, response);
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
