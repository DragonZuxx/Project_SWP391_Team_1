/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Wishlist;
import Dal.DBContext;
import Model.Books;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aplal
 */
public class WishlistDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public boolean addWishlist(Wishlist w) {
        try {
            String sql = "INSERT INTO Wishlist (UserID, BookID) VALUES(?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, w.getUserID());
            stm.setInt(2, w.getBookID());
            int result = stm.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addWishlist: " + e.getMessage());
        }
        return false;
    }
//Lấy ra danh sanh sách yêu thích của người dùng theo UserID

    public Wishlist getWishlistByUserIdAndBookId(int userId, int bookId) {
        String sql = "SELECT * FROM Wishlist WHERE UserID = ? AND BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setUserID(rs.getInt("UserID"));
                wishlist.setBookID(rs.getInt("BookID"));
                wishlist.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                wishlist.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return wishlist;
            }
        } catch (Exception e) {
            System.out.println("getWishlistByUserIdAndBookId: " + e.getMessage());
        }
        return null;
    }
    // Lấy danh sách sản phẩm yêu thích của người dùng theo id
    public List<Books> getWishlistProducts(int userId) {
        List<Books> wishlistProducts = new ArrayList<>();
        String sql = "SELECT b.BookID, b.Title, b.Price, b.CoverImage "
                + "FROM Wishlist w "
                + "JOIN Books b ON w.BookID = b.BookID "
                + "WHERE w.UserID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books product = new Books();
                product.setBookID(rs.getInt("BookID"));
                product.setTitle(rs.getString("Title"));
                product.setPrice(rs.getString("Price")); // Sử dụng BigDecimal cho giá
                product.setCoverImage(rs.getString("CoverImage")); // Lưu trữ liên kết ảnh
                wishlistProducts.add(product);
            }
        } catch (Exception e) {
            System.out.println("getWishlistProducts: " + e.getMessage());
        }
        return wishlistProducts;
    }

    // Xóa sản phẩm khỏi danh sách yêu thích
    public void removeWishlist(int userId, int bookId) {
        String sql = "DELETE FROM Wishlist WHERE UserID = ? AND BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("removeWishlist: " + e.getMessage());
        }
    }
     // Thêm sản phẩm vào danh sách yêu thích
    public Wishlist addWishlist(int userId, int bookId) {
        String sql = "INSERT INTO Wishlist VALUES(?, ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addWishlist: " + e.getMessage());
        }
        return null;
    }
}
