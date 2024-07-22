/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class CategoryDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
// Lấy tất cả Category

    public ArrayList<Categories> getCategories() {
        ArrayList<Categories> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Categories";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories category = new Categories();
                category.setCategoryID(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                category.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                category.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println("checkAccount" + e.getMessage());
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
        return categories;
    }
    //Lấy tên Category theo CategoryID
    public Categories getCategoryByID(int id) {
        Categories category = null;
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                category = new Categories();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                category.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("getCategoryByID: " + e.getMessage());
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
        return category;
    }
}
