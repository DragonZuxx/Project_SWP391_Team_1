/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoryController;

import Dao.BookDao;
import Dao.CategoryDao;
import Dao.ProductFilterDao;
import Dao.PromotionDao;
import Model.Books;
import Model.Categories;
import Model.Promotions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

/**
 *
 * @author DMX THANH SON
 */
@WebServlet(name = "FilterCategory", urlPatterns = {"/listdetailcategory"})
public class FilterCategory extends HttpServlet {

    private ProductFilterDao productDAO = new ProductFilterDao();
    private BookDao bd = new BookDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FilterCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách nhà xuất bản từ DAO
        ArrayList<String> publishers = bd.getDistinctPublisher();

        // Lấy các tham số từ request
        String selectedPublisher = request.getParameter("selectedPublisher");
        String priceRange = request.getParameter("priceRanges");
        String order = request.getParameter("order");
        String categoryID = request.getParameter("categoryID");

        // Kiểm tra nếu categoryID null hoặc empty, hãy lấy nó từ session hoặc nơi lưu trữ khác
        if (categoryID == null || categoryID.isEmpty()) {
            categoryID = (String) request.getSession().getAttribute("categoryID");
        } else {
            request.getSession().setAttribute("categoryID", categoryID);
        }
         CategoryDao cd = new CategoryDao();
        //Lấy thông tin Category theo id
        int cateid = Integer.parseInt(categoryID);
        Categories category = cd.getCategoryByID(cateid);

        // Lấy danh sách sách từ DAO
        List<Books> books = productDAO.filterBooks(selectedPublisher, priceRange, order, categoryID);
        PromotionDao promotionsDao = new PromotionDao();
        Promotions promotions = promotionsDao.getPromotionValid();
        // Kiểm tra và lấy thông tin khuyến mãi nếu chưa có trong request
        if (request.getAttribute("promotion") == null) {

            request.setAttribute("promotion", promotions);
        }

        
        // Đặt các thuộc tính vào request scope để truyền tới JSP
        request.setAttribute("publishers", publishers);
        request.setAttribute("books", books);
        request.setAttribute("selectedPublisher", selectedPublisher);
        request.setAttribute("priceRanges", priceRange);
        request.setAttribute("order", order);
        request.setAttribute("categoryID", categoryID);
        request.setAttribute("category", category);

        // Forward request và response tới trang JSP
        request.getRequestDispatcher("categoryView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
