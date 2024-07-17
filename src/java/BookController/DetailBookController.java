/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package BookController;

import Dao.AccountDao;
import Dao.AuthorDao;
import Dao.BookAuthorDao;
import Dao.BookCategorieDao;
import Dao.BookDao;
import Dao.CategoryDao;
import Dao.PromotionDao;
import Dao.ReviewDao;
import Dao.WishlistDao;
import Model.Accounts;
import Model.Authors;
import Model.BookAuthors;
import Model.BookCategories;
import Model.Books;
import Model.Categories;
import Model.Promotions;
import Model.Reviews;
import Model.Wishlist;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Aplal
 */
@WebServlet(name="DetailBookController", urlPatterns={"/detailbook"})
public class DetailBookController extends HttpServlet {

    private AccountDao accountDao;
    private BookDao bookDao;
    private AuthorDao authorDao;
    private CategoryDao categoryDao;
    private BookAuthorDao bookAuthorDao;
    private BookCategorieDao bookCategorieDao;
    private ReviewDao reviewDao;
    private PromotionDao promotionDao;
    private WishlistDao wishlistDao;

    @Override
    public void init() throws ServletException {
        super.init();
        accountDao = new AccountDao();
        bookDao = new BookDao();
        authorDao = new AuthorDao();
        categoryDao = new CategoryDao();
        bookAuthorDao = new BookAuthorDao();
        bookCategorieDao = new BookCategorieDao();
        reviewDao = new ReviewDao();
        promotionDao = new PromotionDao();
        wishlistDao = new WishlistDao();
    }

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookIDStr = request.getParameter("id");
        int bookID = Integer.parseInt(bookIDStr);
        Accounts account = getAccountsInfoSession(request);

        
        Books product = bookDao.getBookByID(bookID);

       
        BookAuthors ba = bookAuthorDao.getBookAuthorById(bookID);
        
        BookCategories bc = bookCategorieDao.getBookCategoriesByBookID(bookID);
        
        int auid = ba != null ? ba.getAuthorID() : -1;
        int cateID = bc != null ? bc.getCategoryID() : -1;

        
        ArrayList<Authors> author = authorDao.getAuthorsByBookId(auid);
       
        Categories category = categoryDao.getCategoryByID(cateID);
        
        Promotions promotion = promotionDao.getPromotionValid();
        
        ArrayList<Reviews> review = reviewDao.getReviewByBookId(bookID);
        int countReview = reviewDao.countReviewByBookId(bookID);

        ArrayList<Integer> userIds = reviewDao.getUserIdByBookId(bookID);
        List<Accounts> userreviewss = new ArrayList<>();
        Set<Integer> addedUserIDs = new HashSet<>();
        for (Integer userId : userIds) {
            if (!addedUserIDs.contains(userId)) {
                Accounts userreviews = accountDao.getAccountByUserID(userId);
                userreviewss.add(userreviews);
                addedUserIDs.add(userId);
            }
        }

        ArrayList<BookCategories> bookCategories = bookCategorieDao.getBookCategoriesByCategoryID(cateID);
        List<Books> bookgetbyid = new ArrayList<>();
        for (int i = 0; i < bookCategories.size(); i++) {
            BookCategories bcate = bookCategories.get(i);
            Books bookbyid = bookDao.getBookByID(bcate.getBookID());
            bookgetbyid.add(bookbyid);
        }

        if (account != null) {
            int accID = account.getUserID();
            Wishlist wishlist = wishlistDao.getWishlistByUserIdAndBookId(accID, bookID);
            if (wishlist != null) {
                request.setAttribute("isWishlistItem", 1);
            } else {
                request.setAttribute("isWishlistItem", 0); 
            }
            request.setAttribute("wishlist", wishlist);
        }
        
        request.setAttribute("addedUserIDs", addedUserIDs);
        request.setAttribute("userreviews", userreviewss);
        request.setAttribute("bookgetbyid", bookgetbyid);
        request.setAttribute("product", product);
        request.setAttribute("author", author);
        request.setAttribute("promotion", promotion);
        request.setAttribute("review", review);
        request.setAttribute("category", category);
        request.setAttribute("countReview", countReview);
        request.getRequestDispatcher("productView.jsp").forward(request, response);
    }

}
