/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Authors;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class AuthorDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
//Lấy danh sách Authors theo BookID

    public ArrayList<Authors> getAuthorsByBookId(int id) {
        ArrayList<Authors> authors = new ArrayList<Authors>();
        String sql = "SELECT * FROM Authors WHERE AuthorID IN (SELECT AuthorID FROM BookAuthors WHERE BookID = ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Authors author = new Authors();
                author.setAuthorID(rs.getInt("AuthorID"));
                author.setName(rs.getString("Name"));

                author.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println("getAuthorsByBookId: " + e.getMessage());
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
        return authors;
    }
    //Lấy Authors theo AuthorID
    public Authors getAuthorById(int id) {
        Authors author = null;
        String sql = "SELECT * FROM Authors WHERE AuthorID =?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                author = new Authors();
                author.setAuthorID(rs.getInt("AuthorID"));
                author.setName(rs.getString("Name"));

                author.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime()); // Đây là thời gian cập nhật, không phải thời gian tạo
            }
        } catch (Exception e) {
            System.out.println("getAuthorById: " + e.getMessage());
        }
        return author;
    }

    public ArrayList<Authors> getAuthors() {
        ArrayList<Authors> authors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Authors";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Authors author = new Authors();
                author.setAuthorID(rs.getInt("AuthorID"));
                author.setName(rs.getString("Name"));

                author.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println("getAuthors: " + e.getMessage());

        }
        return authors;
    }

    public void updateAuthor(String name, String biography, LocalDateTime updatedAt, int id) {
        String sql = "UPDATE Authors SET Name = ?,  UpdatedAt = ? WHERE AuthorID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, name);

            stm.setTimestamp(2, java.sql.Timestamp.valueOf(updatedAt));
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateAuthor: " + e.getMessage());
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }

    public Authors getAuthorByName(String name) {
        Authors author = null;
        String sql = "SELECT * FROM Authors WHERE Name = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            rs = stm.executeQuery();
            while (rs.next()) {
                author = new Authors();
                author.setAuthorID(rs.getInt("AuthorID"));
                author.setName(rs.getString("Name"));

                author.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("getAuthorByName: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return author;
    }

   public void addAuthor(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
    String sql = "INSERT INTO Authors(Name, CreatedAt, UpdatedAt) VALUES(?, ?, ?)";
    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, name);
        stm.setTimestamp(2, java.sql.Timestamp.valueOf(createdAt));
        stm.setTimestamp(3, java.sql.Timestamp.valueOf(updatedAt));
        stm.executeUpdate();
    } catch (SQLException e) {
        System.out.println("addAuthor: " + e.getMessage());
    }
}

   

    public ArrayList<Authors> searchAuthorByName(String name) {
        ArrayList<Authors> authors = new ArrayList<Authors>();
        String sql = "SELECT * FROM Authors WHERE Name LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Authors author = new Authors();
                author.setAuthorID(rs.getInt("AuthorID"));
                author.setName(rs.getString("Name"));

                author.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println("searchAuthorByName" + e.getMessage());
        }
        return authors;
    }
}
