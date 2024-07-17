/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.OrderStatusHistory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class OrderStatusDao extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public boolean addOrderStatusHistory(OrderStatusHistory orderStatusHistory) {
        try {
            String sql = "INSERT INTO OrderStatusHistory(OrderID, Status, ChangedDate) VALUES(?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, orderStatusHistory.getOrderid());
            stm.setString(2, orderStatusHistory.getStatus());
            stm.setString(3, orderStatusHistory.getChangeDate());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
