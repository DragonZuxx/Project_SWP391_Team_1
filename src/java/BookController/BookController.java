package BookController;

import Dao.AuthorDao;
import Dao.BookAuthorDao;
import Dao.BookCategorieDao;
import Dao.BookDao;
import Dao.CategoryDao;
import Model.Books;
import Model.Accounts;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns = {"/productManager"})
public class BookController extends HttpServlet {

    private BookDao bookDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDAO = new BookDao();
    }

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Accounts user = getAccountsInfoSession(request);
//        if (user == null || user.getRoleID() == 3) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
        // Pagination parameters
        int pageNumber = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 5;
        String name = request.getParameter("name");

        // Fetch paginated list of books
        List<Books> bookList = bookDAO.getBooksByPage(pageNumber, pageSize, name);

        // Get total number of books
        int totalBooks = bookDAO.getBooksByPage(name).size();

        // Calculate total number of pages
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        // Forward the book list and pagination parameters to the JSP
        request.setAttribute("products", bookList);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("name", name);
        request.setAttribute("authorList", new AuthorDao().getAllAuthors());
        request.setAttribute("cateList", new CategoryDao().getCategories());

        request.getRequestDispatcher("productManagerView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Determine action (add or update)
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addBook(request, response);
                    break;
                case "update":
                    updateBook(request, response);
                    break;
                case "delete":
                    deleteBook(request, response);
                    break;
            }
        } else {
            // Handle missing action parameter
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve book data from request parameters
        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        String publicationDate = request.getParameter("publicationDate");
        String isbn = request.getParameter("isbn");
        String price = request.getParameter("price");
        int stock = Integer.parseInt(request.getParameter("stock"));
//        int soldQuantity = Integer.parseInt(request.getParameter("soldQuantity"));
        String description = request.getParameter("description");
        String coverImage = request.getParameter("coverImage");
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));
        boolean isBanned = Boolean.parseBoolean(request.getParameter("isBanned"));
        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        Books newBook = new Books();
        newBook.setTitle(title);
        newBook.setPublisher(publisher);
        newBook.setPublicationDate(publicationDate);
        newBook.setISBN(isbn);
        newBook.setPrice(price);
        newBook.setStock(stock);
//        newBook.setSoldQuantity(soldQuantity);
        newBook.setDescription(description);
        newBook.setCoverImage(coverImage);
        newBook.setIsAvailable(isAvailable);
        newBook.setIsBanned(isBanned);
        newBook.setCreatedAt(now);
        newBook.setUpdatedAt(now);

        int bookID = bookDAO.addBook(newBook);

        String[] authors = request.getParameterValues("authors");
        if (authors != null) {
            for (String author : authors) {
                new BookAuthorDao().addBookAuthor(bookID, Integer.parseInt(author));
            }
        }

        String[] categories = request.getParameterValues("categories");
        if (categories != null) {
            for (String cate : categories) {
                new BookCategorieDao().addBookCategory(bookID, Integer.parseInt(cate));
            }
        }

        if (bookID != -1) {
            // Redirect to book list page after successful addition
            response.sendRedirect("productManager?success");
        } else {
            response.sendRedirect("productManager?fail");
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve book data from request parameters
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        String publicationDate = request.getParameter("publicationDate");
        String isbn = request.getParameter("isbn");
        String price = request.getParameter("price");
        int stock = Integer.parseInt(request.getParameter("stock"));
//        int soldQuantity = Integer.parseInt(request.getParameter("soldQuantity"));
        String description = request.getParameter("description");
        String coverImage = request.getParameter("coverImage");
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));
        boolean isBanned = Boolean.parseBoolean(request.getParameter("isBanned"));
        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        Books book = bookDAO.getBookById(bookID);
        book.setISBN(isbn);
        book.setTitle(title);
        book.setPublisher(publisher);
        book.setPublicationDate(publicationDate);
        book.setPrice(price);
        book.setStock(stock);
//        book.setSoldQuantity(soldQuantity);
        book.setDescription(description);
        book.setCoverImage(coverImage);
        book.setIsAvailable(isAvailable);
        book.setIsBanned(isBanned);
        book.setUpdatedAt(java.time.LocalDateTime.now());

        boolean success = bookDAO.updateBook(book);

        new BookAuthorDao().deleteBookAuthorsByBookID(bookID);
        String[] authors = request.getParameterValues("authors");
        if (authors != null) {
            for (String author : authors) {
                new BookAuthorDao().addBookAuthor(bookID, Integer.parseInt(author));
            }
        }

        new BookCategorieDao().deleteBookCategoriesByBookID(bookID);
        String[] categories = request.getParameterValues("categories");
        if (categories != null) {
            for (String cate : categories) {
                new BookCategorieDao().addBookCategory(bookID, Integer.parseInt(cate));
            }
        }

        if (success) {
            // Redirect to book list page after successful update
            response.sendRedirect("productManager?success");
        } else {
            // Handle update failure
            response.sendRedirect("productManager?fail");
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookID = Integer.parseInt(request.getParameter("bookID"));

        boolean success = bookDAO.deleteBook(bookID);
        if (success) {
            // Redirect to book list page after successful deletion
            response.sendRedirect("productManager?success");
        } else {
            // Handle deletion failure
            response.sendRedirect("productManager?fail");
        }
    }
}
