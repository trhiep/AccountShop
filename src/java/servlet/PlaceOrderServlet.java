package servlet;

import DAO.BalanceDAO;
import DAO.CartDAO;
import DAO.OrdersDAO;
import DAO.StockDAO;
import entity.Account;
import entity.Product;
import entity.UserBalance;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import utils.EmailContext;

/**
 *
 * @author tranh
 */
@WebServlet(name = "PlaceOrderServlet", urlPatterns = {"/placeOrder"})
public class PlaceOrderServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Users userAcc = (Users) session.getAttribute("Users");
        String username = userAcc.getUsername();
        String[] productIDList = request.getParameterValues("productID");
        String totalPrice = request.getParameter("totalPrice");
        HashMap<Integer, Integer> cartQuantityMap = (HashMap<Integer, Integer>) session.getAttribute("cartQuantityMap");
        BalanceDAO balanceDAO = new BalanceDAO();
        UserBalance balance = balanceDAO.getBalanceByUsername(username);
        if (balance.getBalance() < Integer.parseInt(totalPrice)) {
            request.setAttribute("balanceErr", "Your balance is not enough for the order request!");
            request.getRequestDispatcher("viewCart").forward(request, response);
        } else {
            ArrayList<Product> productList = new ArrayList<>();
            StockDAO stockDAO = new StockDAO();
            for (String item : productIDList) {
                Product prd = stockDAO.searchProductByID(item);
                productList.add(prd);
            }
            ArrayList<Account> accountList;
            ArrayList<Account> allAccountList = new ArrayList<>();
            for (Product product : productList) {

                accountList = stockDAO.takeAccountByProductID(product, cartQuantityMap.get(product.getProductID()));

                OrdersDAO ordersDAO = new OrdersDAO();
                boolean isSuccess = false;
                for (Account account : accountList) {
                    isSuccess = ordersDAO.saveOrderHistory(username, account);
                }
                if (isSuccess == true) {
                    for (Account account : accountList) {
                        stockDAO.deleteAccountByID(account.getAccountID() + "");
                        CartDAO cartDAO = new CartDAO();
                        cartDAO.deleteInCart(username, product.getProductID() + "");
                    }
                }
                allAccountList.addAll(accountList);
            }
            int newBalance = balance.getBalance() - Integer.parseInt(totalPrice);
            balanceDAO.saveBalance(username, newBalance);
            balanceDAO.saveBalanceHistory(username, totalPrice, newBalance + "");
            if (userAcc.isIsVerified()) {
                EmailContext.sendAccountInformation(username, allAccountList);
            }
            response.sendRedirect("viewCart");
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
