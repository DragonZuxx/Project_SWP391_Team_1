/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BookController;

import Dao.ReviewDao;
import Model.Accounts;
import Model.Reviews;
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
@WebServlet(name = "DeleteProductReview", urlPatterns = {"/deleteProductReview"})
public class DeleteProductReview extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid = request.getParameter("productReviewId");
        String bookid = request.getParameter("productId");
        int bookId = Integer.parseInt(bookid);
        String content = request.getParameter("contentdele");
        int id = Integer.parseInt(userid);
        Accounts acc = getAccountsInfoSession(request);
        if (acc == null) {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        } else {
            ReviewDao reviewDao = new ReviewDao();
            Reviews review = new Reviews();
            review.setUserID(id);
            review.setBookID(bookId);
            review.setComment(content);
            boolean deleteReview = reviewDao.deleteReview(id, bookId, content);
            if (deleteReview) {
                request.getSession().setAttribute("successDeleteReviewMessage", "Đã xóa đánh giá thành công!");
            } else {
                request.getSession().setAttribute("errorDeleteReviewMessage", "Lỗi không thể xóa đánh giá!");
            }
        }
        response.sendRedirect("detailbook?id=" + bookid);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
