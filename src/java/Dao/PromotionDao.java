package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Promotions;
import Dal.DBContext;
import java.util.ArrayList;
import java.sql.Date;

public class PromotionDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    //Lấy mã giảm giá vẫn còn hiệu lực và có ngày bắt đầu nhỏ hơn hoặc bằng ngày hiện tại

    public Promotions getPromotionValid() {
        try {
            String sql = "SELECT * FROM Promotions WHERE StartDate <= ? AND EndDate > ?";
            stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            stm.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            rs = stm.executeQuery();
            while (rs.next()) {
                Promotions promotion = new Promotions();
                promotion.setPromotionID(rs.getInt("PromotionID"));
                promotion.setTitle(rs.getString("Title"));
                promotion.setDescription(rs.getString("Description"));
                promotion.setStartDate(rs.getDate("StartDate"));
                promotion.setEndDate(rs.getDate("EndDate"));
                promotion.setDiscountPercentage(rs.getBigDecimal("DiscountPercentage"));
                promotion.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                promotion.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return promotion;
            }
        } catch (Exception e) {
            System.out.println("getPromotionValid: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Promotions> getAllPromotion() {
        ArrayList<Promotions> list = new ArrayList<>();
        String sql = "SELECT * FROM Promotions";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Promotions p = new Promotions();
                p.setPromotionID(rs.getInt("PromotionID"));
                p.setTitle(rs.getString("Title"));
                p.setDescription(rs.getString("Description"));
                p.setStartDate(rs.getDate("StartDate"));
                p.setEndDate(rs.getDate("EndDate"));
                p.setDiscountPercentage(rs.getBigDecimal("DiscountPercentage"));
                p.setIsActive(rs.getBoolean("isActive"));
                p.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                p.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Promotions> getPromotionByPage(int pageNumber, int pageSize) {
        ArrayList<Promotions> list = new ArrayList<>();
        String sql = "SELECT * FROM Promotions ORDER BY PromotionID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, (pageNumber - 1) * pageSize);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Promotions p = new Promotions();
                p.setPromotionID(rs.getInt("PromotionID"));
                p.setTitle(rs.getString("Title"));
                p.setDescription(rs.getString("Description"));
                p.setStartDate(rs.getDate("StartDate"));
                p.setEndDate(rs.getDate("EndDate"));
                p.setDiscountPercentage(rs.getBigDecimal("DiscountPercentage"));
                p.setIsActive(rs.getBoolean("isActive"));
                p.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                p.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean addPromotion(Promotions p) {
        String sql = "INSERT INTO Promotions(Title, Description, StartDate, EndDate, DiscountPercentage, isActive) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getTitle());
            stm.setString(2, p.getDescription());
            stm.setDate(3, new java.sql.Date(p.getStartDate().getTime()));
            stm.setDate(4, new java.sql.Date(p.getEndDate().getTime()));
            stm.setBigDecimal(5, p.getDiscountPercentage());
            stm.setBoolean(6, p.isIsActive());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu ít nhất một dòng được thêm vào
        } catch (Exception e) {
            e.printStackTrace(); // Sử dụng e.printStackTrace() để ghi lại lỗi
            return false; // Trả về false nếu có lỗi
        }
    }

    //delete promotion by id
    public boolean deletePromotion(int promotionID) {
        String sql = "DELETE FROM Promotions WHERE PromotionID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, promotionID);
            stm.executeUpdate();
            int rowsAffected = stm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    //update promotion by id
    public boolean updatePromotion(Promotions p) {
        String sql = "UPDATE Promotions SET Title = ?, Description = ?, StartDate = ?, EndDate = ?, DiscountPercentage = ?, isActive = ? WHERE PromotionID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, p.getTitle());
            stm.setString(2, p.getDescription());
            stm.setDate(3, new java.sql.Date(p.getStartDate().getTime()));
            stm.setDate(4, new java.sql.Date(p.getEndDate().getTime()));
            stm.setBigDecimal(5, p.getDiscountPercentage());
            stm.setBoolean(6, p.isIsActive());
            stm.setInt(7, p.getPromotionID());
            stm.executeUpdate();
            
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu ít nhất một dòng được thêm vào
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }


}
