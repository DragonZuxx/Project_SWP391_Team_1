/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AccountController;

import Dao.BookDao;
import Dao.CategoryDao;
import Dao.PromotionDao;
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

/**
 *
 * @author LENOVO
 */
@WebServlet(name="HomeController", urlPatterns={"/home"})
public class HomeServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ChatBotController.getBot(request);

        BookDao bookDao = new BookDao();
        CategoryDao cateDao = new CategoryDao();
        PromotionDao promotionDao = new PromotionDao();
        request.setAttribute("promotion", promotionDao.getPromotionValid());
        ArrayList<Categories> category = cateDao.getCategories();
        ArrayList<Books> book = bookDao.getAllBooks();
        ArrayList<Books> bookgetlatestproducts = bookDao.getLatestProducts();
        ArrayList<Books> bookbestseller = bookDao.getBestSeller();
        request.setAttribute("bestseller", bookbestseller);
        request.setAttribute("arraybook", bookgetlatestproducts);
        request.setAttribute("listbook", book);
        request.setAttribute("category", category);
        request.getRequestDispatcher("homeView.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
