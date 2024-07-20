/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dal.DBContext;
import Model.BookCategories;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class BookCategorieDao extends DBContext{

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

    //Đếm số BookID theo CategoryID
    public int countBookIDByCategoryID(int id) {
        int count = 0;
        String sql = "SELECT COUNT(BookID) FROM BookCategories WHERE CategoryID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countBookIDByCategoryID: " + e.getMessage());
        }
        return count;
    }
    //Chỉ lấy mỗi BookID theo CategoryID
    public ArrayList<Integer> getBookIDByCategoryID(int id) {
        ArrayList<Integer> bookID = new ArrayList<>();
        String sql = "SELECT BookID FROM BookCategories WHERE CategoryID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                bookID.add(rs.getInt("BookID"));
            }
        } catch (Exception e) {
            System.out.println("getBookIDByCategoryID: " + e.getMessage());
        }
        return bookID;
    }

    //Test case
    public static void main(String[] args) {
        BookCategorieDao bookCategorieDao = new BookCategorieDao();
        System.out.println(bookCategorieDao.getBookCategoriesByBookID(1));
    }

    public void deleteBookCategoriesByBookID(int bookID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addBookCategory(int bookID, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
