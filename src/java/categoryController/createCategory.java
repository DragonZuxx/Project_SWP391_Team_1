/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoryController;

import Dao.CategoryDao;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 *
 * @author DMX THANH SON
 */
@WebServlet(name = "createCategory", urlPatterns = {"/createcategory"})
public class createCategory extends HttpServlet {

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
            out.println("<title>Servlet createCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createCategory at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("createCategoryView.jsp").forward(request, response);
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
        String catename = request.getParameter("CategoryName");
        String errorMessenger = "";
        String successMessage = "";
        CategoryDao catedao = new CategoryDao();

        try {
            if (catedao.getCategoryByName(catename) != null) {
                errorMessenger = "Tên thể loại đã tồn tại. Vui lòng nhập thể loại khác";
                request.setAttribute("errorMessenger", errorMessenger);
                request.getRequestDispatcher("createCategoryView.jsp").forward(request, response);
            } else {
                catedao.addCategory(catename, LocalDateTime.now(), LocalDateTime.now());
                successMessage = "Thêm thể loại thành công!";
                request.setAttribute("successMessage", successMessage);
                request.getRequestDispatcher("createCategoryView.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Thêm thông báo lỗi vào request scope
            request.setAttribute("error", "Failed to add category: " + e.getMessage());
            // Chuyển hướng về form và hiển thị lỗi
            request.getRequestDispatcher("createCategoryView.jsp").forward(request, response);
        }

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
