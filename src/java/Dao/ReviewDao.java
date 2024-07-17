/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Dal.DBContext;
import Model.Reviews;

/**
 *
 * @author Aplal
 */
public class ReviewDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
// Lấy ra danh sách review của sách theo BookID

    public ArrayList<Reviews> getReviewByBookId(int id) {
        ArrayList<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Reviews WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Reviews review = new Reviews();
                review.setUserID(rs.getInt("UserID"));
                review.setBookID(rs.getInt("BookID"));
                review.setComment(rs.getString("Comment"));
                review.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                review.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                reviews.add(review);
            }
        } catch (Exception e) {
            System.out.println("getReviewByBookId: " + e.getMessage());
        }
        return reviews;
    }
    //Đếm số lượng review của sách theo BookID
    public int countReviewByBookId(int id) {
        String sql = "SELECT COUNT(*) FROM Reviews WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countReviewByBookId: " + e.getMessage());
        }
        return 0;
    }
    //Lấy ra danh sách UserID đã review sách theo BookID
    public ArrayList<Integer> getUserIdByBookId(int id) {
        ArrayList<Integer> userIds = new ArrayList<>();
        String sql = "SELECT UserID FROM Reviews WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                userIds.add(rs.getInt("UserID"));
            }
        } catch (Exception e) {
            System.out.println("getUserIdByBookId: " + e.getMessage());
        }
        return userIds;
    }
    public boolean deleteReview(int userId, int bookId, String content) {
        String sql = "DELETE FROM Reviews WHERE UserID = ? AND BookID = ? AND Comment = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            stm.setString(3, content);
            int result = stm.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("deleteReview: " + e.getMessage());
        }
        return false;
    }
    public boolean addReview(Reviews review) {
        String sql = "INSERT INTO Reviews (UserID, BookID, Comment, CreatedAt) VALUES(?, ?, ? ,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, review.getUserID());
            stm.setInt(2, review.getBookID());
            stm.setString(3, review.getComment());
            stm.setTimestamp(4,java.sql.Timestamp.valueOf(review.getCreatedAt()));
            int result = stm.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("addReview: " + e.getMessage());
        }
        return false;
    }
}