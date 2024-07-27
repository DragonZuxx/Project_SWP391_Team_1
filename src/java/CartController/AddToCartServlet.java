/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import Dao.BookDao;
import Dao.CartDao;
import Dao.CartItemDao;
import Model.Books;

import Model.Cart;
import Model.CartItem;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.print.Book;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addtocart"})
public class AddToCartServlet extends HttpServlet {

    private CartDao cartDao;
    private CartItemDao cartitemDao;
    private BookDao bookDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cartDao = new CartDao();
        cartitemDao = new CartItemDao();
        bookDao = new BookDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String bookID_STR = request.getParameter("productId");
        int bookID = Integer.parseInt(bookID_STR);
        String quantity_STR = request.getParameter("quantity");
        int quantity = Integer.parseInt(quantity_STR);
        String nameBook = request.getParameter("productName");

        Books book = bookDao.getBookById(bookID); // Fetch the book details

        if (book == null) {
            request.getSession().setAttribute("errorAddToCartMessage", "Sản phẩm không tồn tại.");
            response.sendRedirect("detailbook?id=" + bookID);
            return;
        }

        int availableQuantity = bookDao.getStockByBookID(bookID); // Assuming this method exists in Book model

        Cart cartOfAccount = cartDao.getCartByUserID(userId);
        if (cartOfAccount == null) {
            if (quantity > availableQuantity) {
                request.getSession().setAttribute("errorAddToCartMessage", "Số lượng bạn thêm vượt quá số lượng có sẵn. Chỉ còn " + availableQuantity + " quyển sách.");
                response.sendRedirect("detailbook?id=" + bookID);
                return;
            }

            Cart cart = new Cart();
            cart.setUserID(userId);
            boolean result = cartDao.addNewCart(cart);

            int cartID = cartDao.getCartByUserID(userId).getCartID();
            CartItem cartItem = new CartItem();
            cartItem.setCartID(cartID);
            cartItem.setBookID(bookID);
            cartItem.setQuantity(quantity);
            boolean result2 = cartitemDao.addNewCartItem(cartItem);

            if (result && result2) {
                request.getSession().setAttribute("successAddToCartMessage", "Thêm " + quantity + " quyển sách " + nameBook + " vào giỏ hàng thành công");
            } else {
                request.getSession().setAttribute("errorAddToCartMessage", "Thêm " + nameBook + " vào giỏ hàng không thành công");
            }
        } else {
            int cartID = cartDao.getCartByUserID(userId).getCartID();
            int bookIdFromCart = cartitemDao.getBookIdFromCartItem(cartID, bookID);

            if (bookIdFromCart == 0) {
                if (quantity > availableQuantity) {
                    request.getSession().setAttribute("errorAddToCartMessage", "Số lượng bạn thêm vượt quá số lượng có sẵn. Chỉ còn " + availableQuantity + " quyển sách.");
                    response.sendRedirect("detailbook?id=" + bookID);
                    return;
                }

                CartItem cartItem = new CartItem();
                cartItem.setCartID(cartID);
                cartItem.setBookID(bookID);
                cartItem.setQuantity(quantity);
                boolean result = cartitemDao.addNewCartItem(cartItem);

                if (result) {
                    request.getSession().setAttribute("successAddToCartMessage", "Thêm " + quantity + " quyển sách " + nameBook + " vào giỏ hàng thành công");
                } else {
                    request.getSession().setAttribute("errorAddToCartMessage", "Thêm " + nameBook + " vào giỏ hàng không thành công");
                }
            } else {
                int quantityFromCart = cartitemDao.getQuantityFromCart(cartID, bookID);
                int newQuantity = quantityFromCart + quantity;

                if (newQuantity > availableQuantity) {
                    int allowedQuantity = availableQuantity - quantityFromCart;
                    request.getSession().setAttribute("errorAddToCartMessage", "Số lượng bạn thêm vượt quá số lượng có sẵn. Bạn chỉ có thể thêm " + allowedQuantity + " quyển sách.");
                    response.sendRedirect("detailbook?id=" + bookID);
                    return;
                }

                CartItem cartItem = new CartItem();
                cartItem.setCartID(cartID);
                cartItem.setBookID(bookID);
                cartItem.setQuantity(newQuantity);
                boolean result = cartitemDao.updateQuantityCartItem(cartItem);

                if (result) {
                    request.getSession().setAttribute("successAddToCartMessage", "Thêm " + quantity + " quyển sách " + nameBook + " vào giỏ hàng thành công");
                } else {
                    request.getSession().setAttribute("errorAddToCartMessage", "Thêm " + nameBook + " vào giỏ hàng không thành công");
                }
            }
        }

        response.sendRedirect("detailbook?id=" + bookID);
    }
}
