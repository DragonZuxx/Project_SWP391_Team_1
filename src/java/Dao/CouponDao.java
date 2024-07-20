
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Coupons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class CouponDao extends DBContext{
    PreparedStatement stm;
    ResultSet rs;


    //Lấy tất cả các coupon có thể sử dụng và còn hạn
    public ArrayList<Coupons> getAllAvailableCoupon() {
        ArrayList<Coupons> list = new ArrayList<>();
        String sql = "SELECT * FROM Coupons WHERE isExpired = 0 AND RemainingQuantity > 0 AND ExpirationDate > GETDATE()";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Coupons c = new Coupons();
                c.setCouponID(rs.getInt("CouponID"));
                c.setCode(rs.getString("Code"));
                c.setDescription(rs.getString("Description"));
                c.setDiscountPercentage(rs.getBigDecimal("DiscountPercentage"));
                c.setExpirationDate(rs.getDate("ExpirationDate"));
                c.setMinimumOrderAmount(rs.getBigDecimal("MinimumOrderAmount"));
                c.setRemainingQuantity(rs.getInt("RemainingQuantity"));
                c.setUsedQuantity(rs.getInt("UsedQuantity"));
                c.setIsExpired(rs.getBoolean("isExpired"));
                c.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                c.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    //Lấy coupon theo tên code và check xem còn hạn không và còn số lượng không
    public Coupons getCouponByCode(String code) {
        String sql = "SELECT * FROM Coupons WHERE Code = ? AND isExpired = 0 AND RemainingQuantity > 0 AND ExpirationDate > GETDATE()";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                Coupons c = new Coupons();
                c.setCouponID(rs.getInt("CouponID"));
                c.setCode(rs.getString("Code"));
                c.setDescription(rs.getString("Description"));
                c.setDiscountPercentage(rs.getBigDecimal("DiscountPercentage"));
                c.setExpirationDate(rs.getDate("ExpirationDate"));
                c.setMinimumOrderAmount(rs.getBigDecimal("MinimumOrderAmount"));
                c.setRemainingQuantity(rs.getInt("RemainingQuantity"));
                c.setUsedQuantity(rs.getInt("UsedQuantity"));
                c.setIsExpired(rs.getBoolean("isExpired"));
                c.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                c.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Coupons> getCouponByPage(int page, int pageSize) {
        ArrayList<Coupons> list = new ArrayList<>();
        String sql = "SELECT * FROM Coupons WHERE isExpired = 0 AND RemainingQuantity > 0 AND ExpirationDate > GETDATE() ORDER BY BookID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Coupons c = new Coupons();
                c.setCouponID(rs.getInt("CouponID"));
                c.setCode(rs.getString("Code"));
                c.setDescription(rs.getString("Description"));
                c.setDiscountPercentage(rs.getBigDecimal("DiscountPercentage"));
                c.setExpirationDate(rs.getDate("ExpirationDate"));
                c.setMinimumOrderAmount(rs.getBigDecimal("MinimumOrderAmount"));
                c.setRemainingQuantity(rs.getInt("RemainingQuantity"));
                c.setUsedQuantity(rs.getInt("UsedQuantity"));
                c.setIsExpired(rs.getBoolean("isExpired"));
                c.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                c.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;    
    }

    //test getCouponByPage
    public static void main(String[] args) {
        CouponDao dao = new CouponDao();
        System.out.println(dao.getAllAvailableCoupon().size());
    }

}
