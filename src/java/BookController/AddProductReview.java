/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BookController;

import Dao.ReviewDao;
import Model.Accounts;
import Model.Reviews;
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
@WebServlet(name = "AddProductReview", urlPatterns = {"/addProductReview"})
public class AddProductReview extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid = request.getParameter("userId");
        String bookid = request.getParameter("productId");
        String content = request.getParameter("content");
        Accounts acc = getAccountsInfoSession(request);
        if (acc == null) {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        } else {
            int id = Integer.parseInt(userid);
            int bookId = Integer.parseInt(bookid);
            ReviewDao reviewDao = new ReviewDao();
            Reviews review = new Reviews();
            review.setUserID(id);
            review.setBookID(bookId);
            review.setComment(content);
            review.setCreatedAt(java.time.LocalDateTime.now());

            boolean addreview = reviewDao.addReview(review);
            if (addreview) {
                request.getSession().setAttribute("successAddReviewMessage", "Thêm đánh giá sản phẩm thành công");
            } else {
                request.getSession().setAttribute("errorAddReviewMessage", "Lỗi không thể thêm đánh giá sản phẩm");
            }
        }

        response.sendRedirect("detailbook?id=" + bookid);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
