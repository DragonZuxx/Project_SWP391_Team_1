/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Books;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    public boolean updateQuantityInStock(int bookID, int newQuantityInStock, int quantity) {
        String sql = "UPDATE Books SET Stock = ?, SoldQuantity = ? WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, newQuantityInStock);
            stm.setInt(2, quantity);
            stm.setInt(3, bookID);
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("updateQuantityInStock" + e.getMessage());
        }
        return false;
    }
}
