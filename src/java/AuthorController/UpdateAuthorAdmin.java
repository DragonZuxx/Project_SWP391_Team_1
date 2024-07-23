/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AuthorController;

import Dao.AuthorDao;
import Model.Authors;
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
@WebServlet(name = "UpdateAuthorAdmin", urlPatterns = {"/updateauthor"})
public class UpdateAuthorAdmin extends HttpServlet {

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
            out.println("<title>Servlet UpdateAuthorAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAuthorAdmin at " + request.getContextPath() + "</h1>");
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
            AuthorDao dal = new AuthorDao();
            Authors author = dal.getAuthorById(id);
            request.setAttribute("author", author);
            request.getRequestDispatcher("updateAuthorAdmin.jsp").forward(request, response);
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

        String idauthor = request.getParameter("id");
        String name = request.getParameter("authorname");
        String biography = request.getParameter("authorbiography");
        String errorMessenger = "";
        String successMessage = "";

        AuthorDao authordao = new AuthorDao();

        try {
            int id = Integer.parseInt(idauthor);
            Authors existingAuthor = authordao.getAuthorByName(name);

            if (existingAuthor != null && existingAuthor.getAuthorID() != id) {
                errorMessenger = "Tên tác giả đã tồn tại. Vui lòng nhập tên khác!";
                request.setAttribute("errorMessenger", errorMessenger);

                Authors author = authordao.getAuthorById(id);
                request.setAttribute("author", author);
                request.getRequestDispatcher("updateAuthorAdmin.jsp").forward(request, response);
            } else {
                authordao.updateAuthor(name, biography, LocalDateTime.now(), id);
                successMessage = "Cập nhật tác giả thành công!";
                request.setAttribute("successMessage", successMessage);

                Authors author = authordao.getAuthorById(id);
                request.setAttribute("author", author);
                request.getRequestDispatcher("updateAuthorAdmin.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorMessenger = "ID không hợp lệ";
            request.setAttribute("errorMessenger", errorMessenger);
            Authors author = authordao.getAuthorById(Integer.parseInt(idauthor));
            request.setAttribute("author", author);
            request.getRequestDispatcher("updateAuthorAdmin.jsp").forward(request, response);
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
