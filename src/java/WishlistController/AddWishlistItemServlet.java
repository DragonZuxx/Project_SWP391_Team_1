/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WishlistController;

import Dao.WishlistDao;
import Model.Wishlist;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "AddWishlistItemServlet", urlPatterns = {"/wishlist"})
public class AddWishlistItemServlet extends HttpServlet {

    private WishlistDao wishlistDao;

    @Override
    public void init() throws ServletException {
        super.init();
        wishlistDao = new WishlistDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        int userIdInt = Integer.parseInt(userId);
        int productIdInt = Integer.parseInt(productId);
        // Add to wishlist logic here
        Wishlist wishlist = new Wishlist();
        wishlist.setUserID(userIdInt);
        wishlist.setBookID(productIdInt);

        boolean result = wishlistDao.addWishlist(wishlist);

        if (result) {
            request.getSession().setAttribute("success_addwishlist", "Thêm " + productName + " vào danh sách yêu thích thành công");
        } else {
            request.getSession().setAttribute("error_addwishlist", "Lỗi không thể thêm " + productName + " vào danh sách yêu thích thành công");
        }
        response.sendRedirect("detailbook?id=" + productIdInt);
    }

}
