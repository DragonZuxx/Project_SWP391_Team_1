
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import Dao.CartDao;
import Dao.CartItemDao;
import Model.Accounts;
import Model.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "DeleteOutCart", urlPatterns = {"/deleteOutCart"})
public class DeleteOutCart extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    private CartItemDao cartitemDao;
    private CartDao cartDao;

    @Override
    public void init() throws ServletException {
        super.init();

        cartitemDao = new CartItemDao();
        cartDao = new CartDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Accounts user = getAccountsInfoSession(request);
        
        if (user == null) {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        } else {
            String cartID = request.getParameter("cartID");
            String bookID = request.getParameter("bookID");
            int cartid = Integer.parseInt(cartID);
            int bookid = Integer.parseInt(bookID);
            
            CartItem cartItem = new CartItem();
            cartItem.setCartID(cartid);
            cartItem.setBookID(bookid);
            boolean deleteBookInCart = cartitemDao.deleteCartItem(cartid, bookid);
            if (deleteBookInCart) {
                request.getSession().setAttribute("successDeleteBookInCart", "Đã xóa sản phẩm ra khỏi giỏ hàng thành công!");
            } else {
                request.getSession().setAttribute("errorDeleteBookInCart", "Lỗi không thể xóa sản phảm!");
            }
        }
        response.sendRedirect("cart");
    }

}
