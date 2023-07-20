
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
@WebServlet(name="UpdateProductServlet", urlPatterns={"/updateProduct"})
public class UpdateProductServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        String btAction = request.getParameter("btAction");
        String productID = request.getParameter("productID");
        String catalogID = request.getParameter("catalogID");
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String shortDetail = request.getParameter("shortDetail");
        String longDetail = request.getParameter("longDetail");
        String productImg = request.getParameter("productImg");
        String quantity = request.getParameter("quantity");
        StockDAO stockDAO = new StockDAO();
        boolean isSuccess = false;
        switch (btAction) {
            case "Update":
                int priceInInt = Integer.parseInt(price);
                int quantityInt = 0;
                if (quantity != null) {
                    quantityInt = Integer.parseInt(quantity);
                }
                isSuccess = stockDAO.updateProductByID(productID, catalogID, title, type, priceInInt, shortDetail, longDetail, productImg, quantityInt);
                break;
            case "Delete":
                isSuccess = stockDAO.deleteProductByID(productID);
                break;
        }
        String UpdateProductMessage;
        if (isSuccess == true) {
            UpdateProductMessage = "Update product successfully!";
        } else {
            UpdateProductMessage = "An error occured when update this product";
        }
        ArrayList<Catalog> catalogList = stockDAO.loadAllCatalog();
        ArrayList<Account> accountList = stockDAO.loadAllAccount();
        ArrayList<Product> productList = stockDAO.loadAllProduct();
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        request.setAttribute("userAcc", userAcc);
        request.setAttribute("action", "Product List");
        request.setAttribute("catalogList", catalogList);
        request.setAttribute("accountList", accountList);
        request.setAttribute("productList", productList);
        request.setAttribute("UPDATEMESSAGE", UpdateProductMessage);
        request.getRequestDispatcher("manager/stockManager.jsp").forward(request, response);
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
