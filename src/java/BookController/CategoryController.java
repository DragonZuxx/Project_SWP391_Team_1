/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package BookController;

import Dao.BookCategorieDao;
import Dao.BookDao;
import Dao.CategoryDao;
import Model.BookCategories;
import Model.Books;
import Model.Categories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="CategoryController", urlPatterns={"/category"})
public class CategoryController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet CategoryController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer categoryId = Integer.parseInt(id);

        BookDao bd = new BookDao();
        CategoryDao cd = new CategoryDao();
        BookCategorieDao bcd = new BookCategorieDao();
        
        //Lấy thông tin Category theo id
         Categories category = cd.getCategoryByID(categoryId);
        int count = bcd.getBookCategoriesByCategoryID(categoryId).size();
        //Lấy mảng BookID theo CategoryID
        ArrayList<BookCategories> bookcategories = bcd.getBookCategoriesByCategoryID(categoryId);
        List<Books> books = new ArrayList<>();
        for (BookCategories bookcategory : bookcategories) {
            Books book = new BookDao().getBookByID(bookcategory.getBookID());
            books.add(book);
        }
        //Lấy mảng Book theo bookcategories
        ArrayList<String> publishers = bd.getDistinctPublisher();
        //Set thông tin vào request
        //int totalPage = (int) Math.ceil((double) books.size() / 6);
        //request.setAttribute("totalPage", totalPage);
         request.setAttribute("category", category);
        request.setAttribute("bookcategories", bookcategories);
        request.setAttribute("books", books);
        request.setAttribute("publishers", publishers);
        request.setAttribute("count_cate", count);
        request.getRequestDispatcher("categoryView.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
