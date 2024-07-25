/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BookController;

import Dao.BookDao;
import Dao.ProductFilterDao;
import Dao.PromotionDao;
import Model.Books;
import Model.Promotions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "SearchBookByNameController", urlPatterns = {"/search"})
public class SearchBookByNameController extends HttpServlet {

    private BookDao bd = new BookDao();
    private static final int RECORDS_PER_PAGE = 10;

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
            out.println("<title>Servlet SearchBookByNameController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchBookByNameController at " + request.getContextPath() + "</h1>");
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
        String query = request.getParameter("q");
        String selectedPublisher = request.getParameter("selectedPublisher");
        String[] priceRanges = request.getParameterValues("priceRanges");
        String order = request.getParameter("order");
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Xử lý khoảng giá thành một chuỗi
        String priceRange = null;
        if (priceRanges != null && priceRanges.length > 0) {
            priceRange = String.join("-", priceRanges);
        }

        ProductFilterDao dao = new ProductFilterDao();
        List<Books> books = dao.searchfilterBooks(selectedPublisher, priceRange, order, query);
        int totalRecords = books.size();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / RECORDS_PER_PAGE);
        PromotionDao promotionDao = new PromotionDao();
        Promotions promotions = promotionDao.getPromotionValid();
        request.setAttribute("promotion", promotions);
        request.setAttribute("books", books);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("query", query);
        request.setAttribute("selectedPublisher", selectedPublisher);
        request.setAttribute("publishers", publishers); // Thiết lập danh sách nhà xuất bản
        request.setAttribute("priceRanges", priceRanges != null ? Arrays.asList(priceRanges) : new ArrayList<>());
        request.setAttribute("order", order);

        request.getRequestDispatcher("searchView.jsp").forward(request, response);
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
        doGet(request, response);
    }

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
