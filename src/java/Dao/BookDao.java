/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Books;
import java.sql.Statement;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class BookDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    //Lây tất cả Books xem nó có bị banned hay không và có còn hàng không

    public ArrayList<Books> getAllBooks() {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books WHERE IsBanned = 0 AND IsAvailable = 1";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("getAllBooks" + e.getMessage());
        }
        return books;
    }

    // getLatestProducts co bi ban va con sach khong 
    public ArrayList<Books> getLatestProducts() {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT TOP 10 * FROM Books WHERE IsBanned = 0 AND IsAvailable = 1 ORDER BY CreatedAt DESC";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("getLatestProducts" + e.getMessage());
        }
        return books;
    }

    //Lấy ra 10 cuốn sách bán chạy nhất và không bị banned và còn hàng
    public ArrayList<Books> getBestSeller() {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT TOP 10 * FROM Books WHERE IsBanned = 0 AND IsAvailable = 1 ORDER BY SoldQuantity DESC";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("getBestSeller" + e.getMessage());
        }
        return books;
    }
    public Books getBookByID(int id) {
        String sql = "SELECT * FROM Books WHERE BookID = ? AND IsBanned = 0 AND IsAvailable = 1";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByID" + e.getMessage());
        }
        return null;
    }
    
    public Books getBookById(int id) {
        String sql = "SELECT * FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByID" + e.getMessage());
        }
        return null;
    }
    
    public int getStockByBookID(int bookID) {
        String sql = "SELECT Stock FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bookID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("Stock");
            }
        } catch (Exception e) {
            System.out.println("getStockByBookID" + e.getMessage());
        }
        return 0;

    }
    public int getSoldQuantitybyBookID(int bookID) {
        String sql = "SELECT SoldQuantity FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bookID);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("SoldQuantity");
            }
        } catch (Exception e) {
            System.out.println("getSoldQuantitybyBookID" + e.getMessage());
        }
        return 0;
    }
    
 
    //Chỉ lấy ra Publisher của Books và không trùng nhau (Distinct)
    public ArrayList<String> getDistinctPublisher() {
        ArrayList<String> publishers = new ArrayList<String>();
        String sql = "SELECT DISTINCT Publisher FROM Books";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                publishers.add(rs.getString("Publisher"));
            }
        } catch (Exception e) {
            System.out.println("getDistinctPublisher" + e.getMessage());
        }
        return publishers;
    }
    
    // Create (Add new Book)
    public int addBook(Books book) {
        String sql = "INSERT INTO Books(Title, Publisher, PublicationDate, ISBN, Price, Stock, SoldQuantity, Description, CoverImage, IsAvailable, IsBanned, CreatedAt, UpdatedAt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, book.getTitle());
            stm.setString(2, book.getPublisher());
            stm.setString(3, book.getPublicationDate());
            stm.setString(4, book.getISBN());
            stm.setString(5, book.getPrice());
            stm.setInt(6, book.getStock());
            stm.setInt(7, book.getSoldQuantity());
            stm.setString(8, book.getDescription());
            stm.setString(9, book.getCoverImage());
            stm.setBoolean(10, book.getIsAvailable());
            stm.setBoolean(11, book.getIsBanned());
            stm.setTimestamp(12, java.sql.Timestamp.valueOf(book.getCreatedAt()));
            stm.setTimestamp(13, java.sql.Timestamp.valueOf(book.getUpdatedAt()));

            int result = stm.executeUpdate();
            if (result > 0) {
                ResultSet generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                }
            }
        } catch (Exception e) {
            System.out.println("addBook: " + e.getMessage());
        }

        return -1; // Return -1 if insertion failed or no ID was generated
    }

    public List<Books> getBooksByPage(int page, int pageSize, String nameFilter) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT * FROM Books";

        // Check if nameFilter is provided and adjust the query
        if (nameFilter != null && !nameFilter.isEmpty()) {
            query += " WHERE Title LIKE ?";
        }

        query += " ORDER BY BookID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            stm = connection.prepareStatement(query);

            int parameterIndex = 1;
            if (nameFilter != null && !nameFilter.isEmpty()) {
                stm.setString(parameterIndex++, "%" + nameFilter + "%");
            }

            stm.setInt(parameterIndex++, (page - 1) * pageSize);
            stm.setInt(parameterIndex, pageSize);

            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }

    public List<Books> getBooksByPage(String nameFilter) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT * FROM Books";

        // Check if nameFilter is provided and adjust the query
        if (nameFilter != null && !nameFilter.isEmpty()) {
            query += " WHERE Title LIKE ?";
        }

        query += " ORDER BY BookID";

        try {
            stm = connection.prepareStatement(query);

            int parameterIndex = 1;
            if (nameFilter != null && !nameFilter.isEmpty()) {
                stm.setString(parameterIndex++, "%" + nameFilter + "%");
            }

            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }

    //Tìm kiếm sách theo tên
    public ArrayList<Books> searchBookByName(String name) {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books WHERE Title LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("searchBookByName" + e.getMessage());
        }
        return books;
    }

    // Update (Update Book)
    public boolean updateBook(Books book) {
        String sql = "UPDATE Books SET Title = ?, Publisher = ?, PublicationDate = ?, ISBN = ?, Price = ?, Stock = ?, SoldQuantity = ?, Description = ?, CoverImage = ?, IsAvailable = ?, IsBanned = ?, UpdatedAt = ? WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, book.getTitle());
            stm.setString(2, book.getPublisher());
            stm.setString(3, book.getPublicationDate());
            stm.setString(4, book.getISBN());
            stm.setString(5, book.getPrice());
            stm.setInt(6, book.getStock());
            stm.setInt(7, book.getSoldQuantity());
            stm.setString(8, book.getDescription());
            stm.setString(9, book.getCoverImage());
            stm.setBoolean(10, book.getIsAvailable());
            stm.setBoolean(11, book.getIsBanned());
            stm.setTimestamp(12, java.sql.Timestamp.valueOf(book.getUpdatedAt()));
            stm.setInt(13, book.getBookID());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("updateBook" + e.getMessage());
        }
        return false;
    }

    // Delete (Delete Book)
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("deleteBook" + e.getMessage());
        }
        return false;
    }

    // Read (Get Book by ISBN)
    public Books getBookByISBN(String isbn) {
        String sql = "SELECT * FROM Books WHERE ISBN = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, isbn);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByISBN" + e.getMessage());
        }
        return null;
    }

    public List<Books> getBooksByPage(int page, int pageSize) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT * FROM Books ORDER BY BookID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {

            stm = connection.prepareStatement(query);
            stm.setInt(1, (page - 1) * pageSize);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }
    
    public void UpdateOrderQuantity(int orderId) {
    String orderDetailsSql = "SELECT BookID, Quantity FROM OrderDetails WHERE OrderID = ?";
    try {
        if (connection != null) {
            // Fetch order details
            stm = connection.prepareStatement(orderDetailsSql);
            stm.setInt(1, orderId);
            rs = stm.executeQuery();

            while (rs.next()) {
                int bookID = rs.getInt("BookID");
                int quantity = rs.getInt("Quantity");

                // Get the current book stock and sold quantity
                BookDao bookDao = new BookDao();
                Books book = bookDao.getBookById(bookID);
                if (book != null) {
                    int newQuantityInStock = book.getStock() - quantity;
                    int newSoldQuantity = book.getSoldQuantity() + quantity;

                    // Update the book information
                    boolean updateSuccess = bookDao.updateQuantityInStock(bookID, newQuantityInStock, newSoldQuantity);
                    if (!updateSuccess) {
                        System.out.println("Failed to update book stock and sold quantity.");
                    }
                }
            }
        }
    } catch (Exception e) {
        System.out.println("autoUpdateOrderStatus" + e.getMessage());
    }
}

    
    public boolean updateQuantityInStock(int bookId, int newQuantityInStock, int newSoldQuantity) {
        String updateQuery = "UPDATE Books SET Stock = ?, SoldQuantity = ? WHERE BookID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setInt(1, newQuantityInStock);
            stmt.setInt(2, newSoldQuantity);
            stmt.setInt(3, bookId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void checkAndUpdateBookAvailability(int bookID) {
        String query = "SELECT Stock FROM Books WHERE BookID = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, bookID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int stock = rs.getInt("Stock");
                    boolean isAvailable = stock > 0;

                    // Update IsAvailable based on stock
                    String updateQuery = "UPDATE Books SET IsAvailable = ? WHERE BookID = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setBoolean(1, isAvailable);
                        updateStmt.setInt(2, bookID);
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
