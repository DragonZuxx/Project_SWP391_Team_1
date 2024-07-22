
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CouponController;

import Dao.CouponDao;
import Model.Accounts;
import Model.Coupons;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "CouponCheckOut", urlPatterns = {"/applyCoupon"})
public class CouponCheckOut extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {
        return (Accounts) request.getSession().getAttribute("account");
    }

    private CouponDao couponDao;

    @Override
    public void init() throws ServletException {
        super.init();
        couponDao = new CouponDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Accounts user = getAccountsInfoSession(request);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"success\": false, \"message\": \"Vui lòng đăng nhập để áp dụng mã giảm giá.\"}");
        } else {
            String couponName = request.getParameter("coupon");
            Coupons coupon = couponDao.getCouponByCode(couponName);
            if (coupon != null) {
                request.getSession().setAttribute("appliedCoupon", coupon);
                out.print("{\"success\": true, \"discountPercentage\": " + coupon.getDiscountPercentage() + ", \"message\": \"Mã giảm giá " + coupon.getCode() + " đã được áp dụng! Bạn được giảm " + coupon.getDiscountPercentage() + "%.\"}");
            } else {
                out.print("{\"success\": false, \"message\": \"Mã giảm giá không hợp lệ.\"}");
            }
        }
    }
}
