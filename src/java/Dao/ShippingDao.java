/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.ShippingDetails;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ShippingDao extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public boolean addShippingDetail(ShippingDetails shippingDetails) {
        try {
            String sql = "INSERT INTO ShippingDetails(OrderID, ShippingMethod, ShippingCost, EstimatedDeliveryDate) VALUES(?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, shippingDetails.getOrderId());
            stm.setString(2, shippingDetails.getShippingMethod());
            stm.setString(3, shippingDetails.getShippingCost());
            stm.setDate(4, (Date) shippingDetails.getEstimatedDeliveryDate());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
