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
        }finally {
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
    //lấy danh sách Category theo BookID
    public ArrayList<Categories> getCategoriesByBookID(int id) {
        ArrayList<Categories> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories WHERE CategoryID IN (SELECT CategoryID FROM BookCategories WHERE BookID = ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories category = new Categories();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                category.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println("getCategoriesByBookID: " + e.getMessage());
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
public void addCategory(String categoryName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        String sql = "INSERT INTO Categories(CategoryName, CreatedAt, UpdatedAt) VALUES(?, ?, ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, categoryName);
            stm.setTimestamp(2, java.sql.Timestamp.valueOf(createdAt));
            stm.setTimestamp(3, java.sql.Timestamp.valueOf(updatedAt));
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addCategory: " + e.getMessage());
        } 
    }

    public void updateCategory(String categoryName, LocalDateTime updatedAt, int id) {
        String sql = "UPDATE Categories SET CategoryName = ?, UpdatedAt = ? WHERE CategoryID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, categoryName);
            stm.setTimestamp(2, java.sql.Timestamp.valueOf(updatedAt));
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateCategory: " + e.getMessage());
        } 
    }

    public void deleteCategory(int id) {
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteCategory: " + e.getMessage());
        } 
    }

    //Lấy Category theo tên
      public Categories getCategoryByName(String name) {
        Categories category = null;
        String sql = "SELECT * FROM Categories WHERE CategoryName = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            rs = stm.executeQuery();
            while (rs.next()) {
                category = new Categories();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                category.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("getCategoryByName: " + e.getMessage());
        } 
        return category;
    }
      
      // T?m ki?m th? lo?i theo tên
    public ArrayList<Categories> searchCategoryByName(String name) {
        ArrayList<Categories> categories = new ArrayList<Categories>();
        String sql = "SELECT * FROM Categories WHERE CategoryName LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories category = new Categories();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                category.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByName" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }
    public static void main(String[] args) {
        CategoryDao cd = new CategoryDao();
     
        if ( cd.getCategoryByName("Văn học") == null) {
            System.out.println("Connected");
        } else {
            System.out.println("Not connect");
        }
    }
    
    
}
