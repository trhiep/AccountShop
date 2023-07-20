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
@WebServlet(name = "StockManagerServlet", urlPatterns = {"/stockManager"})
public class StockManagerServlet extends HttpServlet {

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

        if (userAcc == null || userAcc.getRole() != 1 && userAcc.getRole() != 2) {
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        } else {
            StockDAO stockDAO = new StockDAO();
            ArrayList<Catalog> catalogList = stockDAO.loadAllCatalog();
            ArrayList<Account> accountList = stockDAO.loadAllAccount();
            ArrayList<Product> productList = stockDAO.loadAllProduct();
            for (Product product : productList) {
                stockDAO.deleteExpiredAccountByProduct(product);
            }
            String action;
            try {
                action = request.getParameter("btAction");
                switch (action) {
                    case "Catalog List":
                        request.setAttribute("action", "Catalog List");
                        break;
                    case "Product List":
                        request.setAttribute("action", "Product List");
                        break;
                    case "Account List":
                        int totalAccount = accountList.size();
                        request.setAttribute("totalAccount", totalAccount);
                        request.setAttribute("action", "Account List");
                        break;
                    case "Search":
                        String id = request.getParameter("filterBtProductID");
                        accountList = stockDAO.getAccountListByProductID(id);
                        if (id.equals("-1")) {
                            accountList = stockDAO.loadAllAccount();
                        }
                        totalAccount = accountList.size();
                        request.setAttribute("totalAccount", totalAccount);
                        request.setAttribute("oldSearchValue", Integer.parseInt(id));
                        request.setAttribute("action", "Account List");
                        break;
                }
            } catch (NullPointerException e) {
                request.setAttribute("action", "Catalog List");
            }

            request.setAttribute("userAcc", userAcc);
            request.setAttribute("catalogList", catalogList);
            request.setAttribute("accountList", accountList);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("manager/stockManager.jsp").forward(request, response);
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
