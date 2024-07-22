/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoryController;

import Dao.CategoryDao;
import Model.Categories;
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
@WebServlet(name = "updateCategory", urlPatterns = {"/updatecategory"})
public class updateCategory extends HttpServlet {

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
            out.println("<title>Servlet updateCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateCategory at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");

        int id;
        try {
            id = Integer.parseInt(id_raw);
            CategoryDao dal = new CategoryDao();
            Categories category = dal.getCategoryByID(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher("updateCategoryView.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
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
        String idcate = request.getParameter("id");
        String name = request.getParameter("categoryname");
        String errorMessenger = "";
        String successMessage = "";

        CategoryDao catedao = new CategoryDao();

        try {
            int id = Integer.parseInt(idcate); // Chuyển đổi id trước
            Categories existingCategory = catedao.getCategoryByName(name);

            if (existingCategory != null && existingCategory.getCategoryID() != id) {
                errorMessenger = "Tên thể loại đã tồn tại. Vui lòng nhập thể loại khác";
                request.setAttribute("errorMessenger", errorMessenger);
                // Lấy lại thông tin thể loại hiện tại từ cơ sở dữ liệu
                Categories category = catedao.getCategoryByID(id);
                request.setAttribute("category", category);
                request.getRequestDispatcher("updateCategoryView.jsp").forward(request, response);
            } else {
                catedao.updateCategory(name, LocalDateTime.now(), id);
                successMessage = "Cập nhật thể loại thành công!";
                request.setAttribute("successMessage", successMessage);
                // Lấy lại thông tin thể loại sau khi cập nhật
                Categories category = catedao.getCategoryByID(id);
                request.setAttribute("category", category);
                request.getRequestDispatcher("updateCategoryView.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorMessenger = "ID không hợp lệ.";
            request.setAttribute("errorMessenger", errorMessenger);
            // Lấy lại thông tin thể loại hiện tại từ cơ sở dữ liệu
            Categories category = catedao.getCategoryByID(Integer.parseInt(idcate));
            request.setAttribute("category", category);
            request.getRequestDispatcher("updateCategoryView.jsp").forward(request, response);
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
