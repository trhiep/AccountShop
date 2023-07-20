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
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/updateAccount"})
public class UpdateAccountServlet extends HttpServlet {

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
        String accountID = request.getParameter("accountID");
        String productID = request.getParameter("productID");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dueDate = request.getParameter("dueDate");

        StockDAO stockDAO = new StockDAO();
        boolean isSuccess = false;
        switch (btAction) {
            case "Update":
                String oldProductID = request.getParameter("oldProductID");
                if (!productID.equals(oldProductID)) {
                    // Decrease 1 in old product
                    Product productInfor = stockDAO.searchProductByID(oldProductID);
                    stockDAO.updateProductByID(productInfor.getProductID() + "",
                            productInfor.getCatalogID(), productInfor.getTitle(),
                            productInfor.getType(), productInfor.getPrice(),
                            productInfor.getShortDetail(), productInfor.getLongDetail(),
                            productInfor.getProductImg(), productInfor.getQuantity() - 1);

                    // Increase 1 in new product
                    Product newProductInfor = stockDAO.searchProductByID(productID);
                    stockDAO.updateProductByID(newProductInfor.getProductID() + "",
                            newProductInfor.getCatalogID(), newProductInfor.getTitle(),
                            newProductInfor.getType(), newProductInfor.getPrice(),
                            newProductInfor.getShortDetail(), newProductInfor.getLongDetail(),
                            newProductInfor.getProductImg(), newProductInfor.getQuantity() + 1);
                }
                isSuccess = stockDAO.updateAccountByID(accountID, productID, email, password, dueDate);
                break;
            case "Delete":
                isSuccess = stockDAO.deleteAccountByID(accountID);
                break;
        }
        String UpdateAccountMessage;
        if (isSuccess == true) {
            UpdateAccountMessage = "Update account successfully!";
        } else {
            UpdateAccountMessage = "An error occured when update this product";
        }
        ArrayList<Catalog> catalogList = stockDAO.loadAllCatalog();
        ArrayList<Account> accountList = stockDAO.loadAllAccount();
        ArrayList<Product> productList = stockDAO.loadAllProduct();
        for (Product product : productList) {
            stockDAO.deleteExpiredAccountByProduct(product);
        }
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        request.setAttribute("userAcc", userAcc);
        int totalAccount = accountList.size();
        request.setAttribute("totalAccount", totalAccount);
        request.setAttribute("action", "Account List");
        request.setAttribute("catalogList", catalogList);
        request.setAttribute("accountList", accountList);
        request.setAttribute("productList", productList);
        request.setAttribute("UpdateAccountMessage", UpdateAccountMessage);
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
