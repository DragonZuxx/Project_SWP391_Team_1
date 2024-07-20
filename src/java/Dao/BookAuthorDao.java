/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.BookAuthors;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class BookAuthorDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //Lấy BookAuthors theo BookID
    public BookAuthors getBookAuthorById(int id) {
        BookAuthors bookauthor = null;
        String sql = "SELECT * FROM BookAuthors WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                bookauthor = new BookAuthors();
                bookauthor.setBookID(rs.getInt("BookID"));
                bookauthor.setAuthorID(rs.getInt("AuthorID"));
                bookauthor.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                bookauthor.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("getBookAuthorById: " + e.getMessage());
        } finally {
            try {
                // Đóng ResultSet, PreparedStatement và kết nối sau khi sử dụng
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e);
            }
        }
        return bookauthor;
    }

    //Lấy danh sách BookAuthors theo BookID
    public ArrayList<BookAuthors> getBookAuthorsByBookID(int id) {
        ArrayList<BookAuthors> bookauthors = new ArrayList<>();
        String sql = "SELECT * FROM BookAuthors WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                BookAuthors bookauthor = new BookAuthors();
                bookauthor.setBookID(rs.getInt("BookID"));
                bookauthor.setAuthorID(rs.getInt("AuthorID"));
                bookauthor.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                bookauthor.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                bookauthors.add(bookauthor);
            }
        } catch (SQLException e) {
            System.out.println("getBookAuthorsByBookID: " + e.getMessage());
        } finally {
            try {
                // Đóng ResultSet, PreparedStatement và kết nối sau khi sử dụng
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e);
            }
        }
        return bookauthors;
    }

    //Test các method
    public static void main(String[] args) {
        BookAuthorDao ba = new BookAuthorDao();
        System.out.println(ba.getBookAuthorById(2));

    }

    public void deleteBookAuthorsByBookID(int bookID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addBookAuthor(int bookID, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
