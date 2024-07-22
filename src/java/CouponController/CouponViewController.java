
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CouponController;

import Dao.BookDao;
import Dao.CouponDao;
import Dao.PromotionDao;
import Model.Books;
import Model.Coupons;
import Model.Promotions;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "CouponViewController", urlPatterns = {"/couponView"})
public class CouponViewController extends HttpServlet {

    private BookDao bookDao;
    private PromotionDao pronotionDao;
    private CouponDao couponDao;
    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDao();
        pronotionDao = new PromotionDao();
        couponDao = new CouponDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Promotions promotions = pronotionDao.getPromotionValid();
        ArrayList<Books> bookrelated = bookDao.getBestSeller();
        ArrayList<Coupons> coupons = couponDao.getAllAvailableCoupon();
        request.setAttribute("bookrelated", bookrelated);
        request.setAttribute("coupons", coupons);
        request.setAttribute("promotions", promotions);
        request.getRequestDispatcher("couponView.jsp").forward(request, response);
    }


}
