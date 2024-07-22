/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoryController;

import Dao.CategoryDao;
import Model.Accounts;
import Model.Categories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author DMX THANH SON
 */
@WebServlet(name = "categoryManagerView", urlPatterns = {"/categoryManager"})
public class categoryManagerView extends HttpServlet {

    private CategoryDao categoryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        categoryDAO = new CategoryDao();
    }

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }
    //private static final int PAGE_SIZE = 5; // Số lượng thể loại mỗi trang

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Accounts user = getAccountsInfoSession(request);
        if (user == null || user.getRoleID() == 3) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int pageNumber = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 5;

        CategoryDao da = new CategoryDao();
        ArrayList<Categories> allCategories = da.getCategories(); // Lấy tất cả các thể loại từ cơ sở dữ liệu

        int totalCategories = allCategories.size(); // Tổng số thể loại
        int totalPages = (int) Math.ceil((double) totalCategories / pageSize); // Tổng số trang

        // Lấy trang hiện tại từ request parameter, mặc định là trang 1
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        // Tính toán chỉ số bắt đầu và kết thúc cho trang hiện tại
        int startItem = (currentPage - 1) * pageSize;
        int endItem = Math.min(startItem + pageSize, totalCategories);

        // Lấy danh sách thể loại cho trang hiện tại
        ArrayList<Categories> categories = new ArrayList<>(allCategories.subList(startItem, endItem));
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("categoryManagerView.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
