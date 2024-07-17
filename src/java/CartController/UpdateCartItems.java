/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import Dao.CartItemDao;

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
 * @author Admin
 */
@WebServlet(name = "UpdateCartItems", urlPatterns = {"/updateCartItem"})

public class UpdateCartItems extends HttpServlet {

    private CartItemDao cartitemDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cartitemDao = new CartItemDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cartID = Integer.parseInt(request.getParameter("cartID"));
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        // Logic to update the cart item
        CartItem cartItem = new CartItem();
        cartItem.setCartID(cartID);
        cartItem.setBookID(bookID);
        cartItem.setQuantity(quantity);
        boolean success = cartitemDao.updateCartItem(cartItem);

        if (success) {
            response.getWriter().write("Success");
        } else {
            response.getWriter().write("Error");
        }
        response.sendRedirect("cart");
    }
}
