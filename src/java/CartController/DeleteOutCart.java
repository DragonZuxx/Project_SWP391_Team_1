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
@WebServlet(name = "DeleteOutCart", urlPatterns = {"/deleteCartItem"})
public class DeleteOutCart extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    private CartItemDao cartitemDao;

    @Override
    public void init() throws ServletException {
        super.init();

        cartitemDao = new CartItemDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cartID = request.getParameter("cartID");
        String bookID = request.getParameter("bookID");
        int cartid = Integer.parseInt(cartID);
        int bookid = Integer.parseInt(bookID);
        // Logic to delete the cart item
        
        boolean success = cartitemDao.deleteCartItem(cartid, bookid);

        if (success) {
            request.getSession().setAttribute("deleteOutCartSucssec", "Đã xóa sách thành công!");
        } else {
            request.getSession().setAttribute("deleteOutCartError", "Lỗi không thể xóa được!");
        }
        response.sendRedirect("cart");
    }

}
