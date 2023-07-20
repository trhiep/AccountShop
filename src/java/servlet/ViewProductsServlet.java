package servlet;

import DAO.CartDAO;
import DAO.StockDAO;
import entity.Cart;
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
@WebServlet(name = "ViewProductsServlet", urlPatterns = {"/viewProducts"})
public class ViewProductsServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        StockDAO stockDAO = new StockDAO();
        ArrayList<Product> availableProductList = stockDAO.getAllTotalAvailableProduct();
        int totalProduct = availableProductList.size();
        int endPage = totalProduct / 8;
        if (totalProduct % 8 != 0) {
            endPage++;
        }
        ArrayList<Catalog> catalogList = stockDAO.loadAllCatalog();
        ArrayList<Product> productList = stockDAO.getProductListByPaging(page);
        request.setAttribute("userAcc", userAcc);
        request.setAttribute("endPage", endPage);
        request.setAttribute("productList", productList);
        request.setAttribute("catalogList", catalogList);
        request.setAttribute("page", page);
        if (userAcc != null) {
            CartDAO cartDAO = new CartDAO();
            int itemsInCart = cartDAO.getNumberItemInCart(userAcc.getUsername());
            request.setAttribute("itemsInCart", itemsInCart);
            ArrayList<Cart> itemList = cartDAO.loadAllItemInCartByUsername(userAcc.getUsername());
            request.setAttribute("itemList", itemList);
        }
        request.getRequestDispatcher("viewProducts.jsp").forward(request, response);
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
