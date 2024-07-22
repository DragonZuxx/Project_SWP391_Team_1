/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ReviewManagerAdmin;

import Dao.ReviewDao;
import Model.Accounts;
import Model.Reviews;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DMX THANH SON
 */
@WebServlet(name = "ReviewAdmin", urlPatterns = {"/reviewManager"})
public class ReviewAdmin extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

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
            out.println("<title>Servlet ReviewAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReviewAdmin at " + request.getContextPath() + "</h1>");
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
    Accounts user = getAccountsInfoSession(request);
    if (user == null || user.getRoleID() == 3) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }

    int pageSize = 5;
    ReviewDao reviewDao = new ReviewDao();
    int totalReview = reviewDao.countTotalReviews();
    int totalPages = (int) Math.ceil((double) totalReview / pageSize);
    int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

    int offset = (currentPage - 1) * pageSize;
    List<Map.Entry<Reviews, Map<String, String>>> reviewList = reviewDao.getLatestReviews(offset, pageSize);

    request.setAttribute("currentPage", currentPage);
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("latereview", reviewList);
    request.getRequestDispatcher("ReviewAdminManager.jsp").forward(request, response);
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
        processRequest(request, response);
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
