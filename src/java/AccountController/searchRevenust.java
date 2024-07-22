/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.ListAccountDao;
import Model.BookRevenue;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


/**
 *
 * @author Admin
 */
@WebServlet(name = "searchRevenust", urlPatterns = {"/searchRevenust"})
public class searchRevenust extends HttpServlet {

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
            out.println("<title>Servlet searchRevenust</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchRevenust at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String year_raw = request.getParameter("year");
        int year;
        try {
            year = Integer.parseInt(year_raw);
            if (!isValidYear(year_raw)) {
                request.setAttribute("mess", "Năm không hợp lệ!");
            }

            ListAccountDao dal = new ListAccountDao();
            List<BookRevenue> list = dal.getMonthlyRevenueByYear(year);
            request.setAttribute("reven", list);
            double totalRevenue = dal.getTotalRevenue(year);
            request.setAttribute("totalRevenue", totalRevenue);

            request.setAttribute("year", year);
        } catch (NumberFormatException e) {
            request.setAttribute("mess", "Năm không hợp lệ, vui lòng nhập lại!");
        }
        request.getRequestDispatcher("displayReven.jsp").forward(request, response);
    }

    public static boolean isValidYear(String year) {
        // Định nghĩa biểu thức chính quy cho năm có 4 số
        String regex = "^[0-9]{4}$";

        // Kiểm tra năm có khớp với biểu thức chính quy không
        return year.matches(regex);
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
