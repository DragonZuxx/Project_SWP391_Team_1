
package CartController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import Dao.BookDao;
import Dao.CartDao;
import Dao.CartItemDao;
import Dao.CouponDao;
import Dao.PromotionDao;
import Model.Accounts;
import Model.Books;
import Model.Cart;
import Model.CartItem;
import Model.Coupons;
import Model.Promotions;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aplal
 */
@WebServlet(urlPatterns = {"/cart"})
public class CartView extends HttpServlet {

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
        Accounts user = getAccountsInfoSession(request);
        if (user != null) {
            int userid = user.getUserID();
            int cartid = cartDao.getCartIdByUserID(userid);

            ArrayList<CartItem> cartItems = cartitemDao.getCartItemsByCartID(cartid);
            List<Books> books = new ArrayList<>();
            for (int i = 0; i < cartItems.size(); i++) {
                CartItem cartitem = cartItems.get(i);
                Books bookbyid = bookDao.getBookByID(i);
                books.add(bookbyid);
            }
            Promotions promotion = promotionDao.getPromotionValid();

            request.setAttribute("promotion", promotion);
            request.setAttribute("books", books);
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cartid", cartid);
            

        } else {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        }
        request.getRequestDispatcher("cartView.jsp").forward(request, response);
    }

    
}
