/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.PromotionDao;
import Dao.WishlistDao;
import Model.Accounts;
import Model.Books;
import Model.Promotions;
import Model.Wishlist;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author DMX THANH SON
 */
@WebServlet(name = "WishlishUserControler", urlPatterns = {"/wishlistuser"})
public class WishlishUserControler extends HttpServlet {

    private WishlistDao wishlistDao = new WishlistDao();
    private PromotionDao promotionDao = new PromotionDao();

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
            out.println("<title>Servlet WishlishUserControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WishlishUserControler at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Accounts account = (Accounts) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = account.getUserID();
        List<Books> wishlistProducts = wishlistDao.getWishlistProducts(userId);

        if (wishlistProducts == null || wishlistProducts.isEmpty()) {
            System.out.println("Wishlist is empty for user: " + userId);
        } else {
            System.out.println("Wishlist contains items for user: " + userId);
        }
        Promotions promotions = promotionDao.getPromotionValid();

        request.setAttribute("wishlistProducts", wishlistProducts);
        request.setAttribute("promotions", promotions);
        request.getRequestDispatcher("wishlistViewUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Accounts account = (Accounts) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int userId = account.getUserID();

        if ("add".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            wishlistDao.addWishlist(userId, bookId);
        } else if ("remove".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            wishlistDao.removeWishlist(userId, bookId);
        }

        response.sendRedirect("wishlistuser");

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
