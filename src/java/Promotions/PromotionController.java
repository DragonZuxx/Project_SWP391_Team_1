package Promotions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.io.IOException;

import Dao.PromotionDao;
import Model.Accounts;
import Model.Promotions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Aplal
 */
@WebServlet(urlPatterns = {"/promotionManager"})
public class PromotionController extends HttpServlet {

    private PromotionDao promotionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        promotionDAO = new PromotionDao();
    }

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Accounts user = getAccountsInfoSession(request);
             if (user == null || user.getRoleID() == 3) {
                 response.sendRedirect(request.getContextPath() + "/login");
                 return;
             }

        // Pagination parameters
        int pageNumber = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 5;

        // Fetch paginated list of promotions
        List<Promotions> promotionList = promotionDAO.getPromotionByPage(pageNumber, pageSize);

        // Get total number of promotions
        int totalPromotions = promotionDAO.getAllPromotion().size();

        int totalPages = (int) Math.ceil((double) totalPromotions / pageSize);
        request.setAttribute("promotion", promotionList);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("promotionManagerView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Accounts user = getAccountsInfoSession(request);
             if (user == null || user.getRoleID() == 3) {
                 response.sendRedirect(request.getContextPath() + "/login");
                 return;
             }
        String action = request.getParameter("action");
        if ("addPromotion".equals(action)) {
            addPromotion(request, response);
        } else if ("deletePromotion".equals(action)) {
            deletePromotion(request, response);
        } else if ("updatePromotion".equals(action)) {
            updatePromotion(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void addPromotion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String discountPercentageStr = request.getParameter("discountPercentage");

        // Validate promotion title
        if (title == null || title.isEmpty()) {
            request.getSession().setAttribute("error", "Title is required");
            response.sendRedirect("promotionManager");
            return;
        }

        // Check for duplicate title
        List<Promotions> promotionList = promotionDAO.getAllPromotion();
        for (Promotions p : promotionList) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                request.getSession().setAttribute("error", "Tên chương trình khuyến mãi đã tồn tại!");
                response.sendRedirect("promotionManager");
                return;
            }
        }

        // Validate promotion description
        if (description == null || description.isEmpty()) {
            request.getSession().setAttribute("error", "Chưa điền mô tả chương trình khuyến mãi!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate start date
        if (startDateStr == null || startDateStr.isEmpty()) {
            request.getSession().setAttribute("error", "Chưa điền ngày bắt đầu chương trình!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate end date
        if (endDateStr == null || endDateStr.isEmpty()) {
            request.getSession().setAttribute("error", "Chưa điền ngày kết thúc chương trình!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate end date is after start date
        try {
            java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
            java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);
            if (startDate.after(endDate)) {
                request.getSession().setAttribute("error", "Ngày bắt đầu phải trước ngày kết thúc!");
                response.sendRedirect("promotionManager");
                return;
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "Invalid date format");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate Discount Percentage
        BigDecimal discountPercentage;
        try {
            discountPercentage = new BigDecimal(discountPercentageStr);
            if (discountPercentage.compareTo(BigDecimal.ZERO) < 0 || discountPercentage.compareTo(new BigDecimal("100")) > 0) {
                request.getSession().setAttribute("error", "Tỷ lệ chiết khấu phải trong khoảng từ 0-100");
                response.sendRedirect("promotionManager");
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid discount percentage format");
            response.sendRedirect("promotionManager");
            return;
        }

        // Create promotion object
        Promotions p = new Promotions();
        p.setTitle(title);
        p.setDescription(description);
        p.setStartDate(java.sql.Date.valueOf(startDateStr));
        p.setEndDate(java.sql.Date.valueOf(endDateStr));
        p.setDiscountPercentage(discountPercentage);
        p.setIsActive(true);

        // Add promotion to database
        boolean isSuccess = promotionDAO.addPromotion(p);
        if (isSuccess) {
            request.getSession().setAttribute("success", "Thêm chương trình khuyến mãi thành công");
        } else {
            request.getSession().setAttribute("error", "Database error occurred");
        }
        response.sendRedirect("promotionManager");
    }

    private void deletePromotion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //get promotion id from request
        int promotionID = Integer.parseInt(request.getParameter("promotionID"));
        //delete promotion by id
        boolean isSuccess = promotionDAO.deletePromotion(promotionID);
        if (isSuccess) {
            request.getSession().setAttribute("success", "Xóa chương trình khuyến mãi thành công");
        } else {
            request.getSession().setAttribute("error", "Database error occurred");
        }
        response.sendRedirect("promotionManager");

    }

    private void updatePromotion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Lấy dữ liệu từ request
        int promotionID = Integer.parseInt(request.getParameter("promotionID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String discountPercentageStr = request.getParameter("discountPercentage");
        boolean isActive = "on".equals(request.getParameter("isActive"));

        // Validate promotion title
        if (title == null || title.isEmpty()) {
            request.getSession().setAttribute("error", "Tên chương trình khuyến mãi không được để trống!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate promotion description
        if (description == null || description.isEmpty()) {
            request.getSession().setAttribute("error", "Mô tả không được để trống!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate start date
        if (startDateStr == null || startDateStr.isEmpty()) {
           request.getSession().setAttribute("error", "Ngày bắt đầu không được để trống!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate end date
        if (endDateStr == null || endDateStr.isEmpty()) {
            request.getSession().setAttribute("error", "Ngày kết thúc không được để trống!");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate end date is after start date
        try {
            java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
            java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);
            if (startDate.after(endDate)) {
                request.getSession().setAttribute("error", "Ngày bắt đầu phải trước ngày kết thúc!");
                response.sendRedirect("promotionManager");
                return;
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "Invalid date format");
            response.sendRedirect("promotionManager");
            return;
        }

        // Validate Discount Percentage
        BigDecimal discountPercentage;
        try {
            discountPercentage = new BigDecimal(discountPercentageStr);
            if (discountPercentage.compareTo(BigDecimal.ZERO) < 0 || discountPercentage.compareTo(new BigDecimal("100")) > 0) {
                request.getSession().setAttribute("error", "Tỷ lệ chiết khấu phải trong khoảng từ 0-100");
                response.sendRedirect("promotionManager");
                return;
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid discount percentage format");
            response.sendRedirect("promotionManager");
            return;
        }

        // Cập nhật thông tin khuyến mãi
        Promotions p = new Promotions();
        p.setPromotionID(promotionID);
        p.setTitle(title);
        p.setDescription(description);
        p.setStartDate(java.sql.Date.valueOf(startDateStr));
        p.setEndDate(java.sql.Date.valueOf(endDateStr));
        p.setDiscountPercentage(discountPercentage);
        p.setIsActive(isActive);
        p.setUpdatedAt(LocalDateTime.now());

        boolean isSuccess = promotionDAO.updatePromotion(p);
        if (isSuccess) {
            request.getSession().setAttribute("success", "Chương trình khuyến mãi được cập nhật thành công!"); 
        } else {
            request.getSession().setAttribute("error", "Database error occurred");
        }
        response.sendRedirect("promotionManager");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
