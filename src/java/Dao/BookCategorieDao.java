/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Dal.DBContext;
import Model.BookCategories;

/**
 *
 * @author Aplal
 */
public class BookCategorieDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //Lấy danh sách BookCategories theo BookID
    public BookCategories getBookCategoriesByBookID(int id) {
        BookCategories bookcategories = null;
        String sql = "SELECT * FROM BookCategories WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                bookcategories = new BookCategories();
                bookcategories.setBookID(rs.getInt("BookID"));
                bookcategories.setCategoryID(rs.getInt("CategoryID"));
                bookcategories.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                bookcategories.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
            }
        } catch (Exception e) {
            System.out.println("getBookCategoriesByBookID: " + e.getMessage());
        }
        return bookcategories;
    }

    //Lấy ra list BookCategories theo CategoryID
    public ArrayList<BookCategories> getBookCategoriesByCategoryID(int id) {
        ArrayList<BookCategories> bookcategories = new ArrayList<>();
        String sql = "SELECT * FROM BookCategories WHERE CategoryID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                BookCategories bookcategorie = new BookCategories();
                bookcategorie.setBookID(rs.getInt("BookID"));
                bookcategorie.setCategoryID(rs.getInt("CategoryID"));
                bookcategorie.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                bookcategorie.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                bookcategories.add(bookcategorie);
            }
        } catch (Exception e) {
            System.out.println("getBookCategoriesByCategoryID: " + e.getMessage());
        }
        return bookcategories;
    }

    public boolean deleteBookCategoriesByBookID(int id) {
        String sql = "DELETE FROM BookCategories WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0; // Return true if rows were deleted
        } catch (Exception e) {
            System.out.println("deleteBookCategoriesByBookID: " + e.getMessage());
            return false;
        }
    }

    public boolean addBookCategory(int bookID, int categoryID) {
        String sql = "INSERT INTO BookCategories (BookID, CategoryID, CreatedAt, UpdatedAt) VALUES (?, ?, GETDATE(), GETDATE())";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bookID);
            stm.setInt(2, categoryID);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0; // Return true if insertion was successful
        } catch (Exception e) {
            System.out.println("addBookCategory: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<BookCategories> getAllBookCategoriesByBookID(int id) {
        ArrayList<BookCategories> bookcategories = new ArrayList<>();
        String sql = "SELECT * FROM BookCategories WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                BookCategories bookcategorie = new BookCategories();
                bookcategorie.setBookID(rs.getInt("BookID"));
                bookcategorie.setCategoryID(rs.getInt("CategoryID"));
                bookcategorie.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                bookcategorie.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                bookcategories.add(bookcategorie);
            }
        } catch (Exception e) {
            System.out.println("getBookCategoriesByCategoryID: " + e.getMessage());
        }
        return bookcategories;
    }

}
