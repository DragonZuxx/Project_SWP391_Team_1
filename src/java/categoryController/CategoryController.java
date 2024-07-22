/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoryController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Dao.BookCategorieDao;
import Dao.BookDao;
import Dao.CategoryDao;
import Dao.PromotionDao;
import Model.BookCategories;
import Model.Books;
import Model.Categories;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Aplal
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

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
        PromotionDao promotionDao = new PromotionDao();
        request.setAttribute("promotion", promotionDao.getPromotionValid());
        request.setAttribute("category", category);
        request.setAttribute("bookcategories", bookcategories);
        request.setAttribute("books", books);
        request.setAttribute("publishers", publishers);
        request.setAttribute("count_cate", count);
        request.getRequestDispatcher("categoryView.jsp").forward(request, response);
    }

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
