/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDao extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public boolean addNewOrderDetail(OrderDetail orderDetail) {
        try {
            String sql = "INSERT INTO OrderDetails(OrderID, BookID, Quantity, UnitPrice) VALUES(?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, orderDetail.getOrderid());
            stm.setInt(2, orderDetail.getBookid());
            stm.setInt(3, orderDetail.getQuantity());
            stm.setString(4, orderDetail.getUnitPrice());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
