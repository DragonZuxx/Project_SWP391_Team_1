package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Promotions;
import Dal.DBContext;

public  class PromotionDao extends DBContext{
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
                promotion.setPromotionId(rs.getInt("promotionId"));
                promotion.setTitle(rs.getString("Title"));
                promotion.setDescription(rs.getString("Description"));
                promotion.setStartDate(rs.getTimestamp("startDate").toLocalDateTime());
                promotion.setEndDate(rs.getTimestamp("endDate").toLocalDateTime());
                promotion.setDiscountPercentage(rs.getString("DiscountPercentage"));
                promotion.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                promotion.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return promotion;
            }
        } catch (Exception e) {
            System.out.println("getPromotionValid: " + e.getMessage());
        }
        return null;
    }

    //Test getPromotionValid
    public static void main(String[] args) {
        PromotionDao promotionDao = new PromotionDao();
        for (int i = 1; i <= 10; i++) {
            Promotions promotion = promotionDao.getPromotionValid();
            System.out.println(promotion);
        }
    }

}
