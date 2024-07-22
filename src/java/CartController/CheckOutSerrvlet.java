
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import Dao.BookDao;
import Dao.CartDao;
import Dao.CartItemDao;
import Dao.OrderDao;
import Dao.PromotionDao;
import Dao.ShippingDao;
import Model.Accounts;
import Model.Books;
import Model.CartItem;
import Model.Order;

import Model.Promotions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "CheckOutSerrvlet", urlPatterns = {"/cartItem"})
public class CheckOutSerrvlet extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }
    private BookDao bookDao;
    private PromotionDao promotionDao;
    private CartItemDao cartitemDao;
    private CartDao cartDao;
    

    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDao();
        promotionDao = new PromotionDao();
        cartitemDao = new CartItemDao();
        cartDao = new CartDao();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
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
        Accounts user = getAccountsInfoSession(request);
        if (user != null) {
            int userid = user.getUserID();
            int cartid = cartDao.getCartIdByUserID(userid);

            ArrayList<CartItem> cartItems = cartitemDao.getCartItemsByCartID(cartid);
            String[] selectedBookIds = request.getParameterValues("selectedBooks");

            List<Books> selectedBooks = new ArrayList<>();
            for (String bookId : selectedBookIds) {
                Books book = bookDao.getBookByID(Integer.parseInt(bookId));
                if (book != null) {
                    selectedBooks.add(book);
                }
            }
            Promotions promotion = promotionDao.getPromotionValid();

            request.setAttribute("promotion", promotion);

            request.setAttribute("cartItems", cartItems);
            request.setAttribute("selectedBooks", selectedBooks);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
    }
}
