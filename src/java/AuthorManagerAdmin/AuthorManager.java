/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AuthorManagerDao;

import Dao.AuthorDao;

import Model.Accounts;
import Model.Authors;
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
@WebServlet(name = "AuthorManager", urlPatterns = {"/authorManager"})
public class AuthorManager extends HttpServlet {

    private AuthorDao authorDao;

    @Override
    public void init() throws ServletException {
        super.init();
        authorDao = new AuthorDao();
    }

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }
    //private static final int PAGE_SIZE = 5; // Số lượng thể loại mỗi trang

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
        Accounts user = getAccountsInfoSession(request);
        if (user == null || user.getRoleID() == 3) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        int pageNumber = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 5;
        
        AuthorDao au = new AuthorDao();
        ArrayList<Authors> allauthor = au.getAuthors();
        
        int totalAuthor = allauthor.size();
        int totalPages  = (int) Math.ceil((double) totalAuthor / pageSize);
        
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (Exception e) {
                currentPage = 1;
            }
        }
         // Tính toán chỉ số bắt đầu và kết thúc cho trang hiện tại
        int startItem = (currentPage - 1) * pageSize;
        int endItem = Math.min(startItem + pageSize, totalAuthor);
        
        ArrayList<Authors> authors = new ArrayList<>(allauthor.subList(startItem, endItem));
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("authors", authors);
        request.getRequestDispatcher("authorManagerView.jsp").forward(request, response);
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
