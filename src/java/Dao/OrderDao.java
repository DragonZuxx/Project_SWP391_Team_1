/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Timestamp;



/**
 *
 * @author LENOVO //
 */
public class OrderDao extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public boolean addNewOrder(Order order) {
        String sql = "INSERT INTO Orders(UserID,FullName,Address,Phone,TotalAmount,Status) VALUES(?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, order.getUserid());
            stm.setString(2, order.getFullname());
            stm.setString(3, order.getAddress());
            stm.setString(4, order.getPhone());
            stm.setString(5, order.getAmount());
            stm.setString(6, order.getStatus());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int getOrderIdbyUserID(int userid) {
        String sql = "SELECT OrderID FROM Orders WHERE UserID = ? ORDER BY CreatedAt DESC";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userid);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }


  public void autoUpdateOrderStatus(int orderId) {
        String sql = "UPDATE Orders SET Status = N'Đã nhận được hàng' WHERE OrderID = ? AND Status = N'Đang giao hàng' AND UpdatedAt <= ?";
        LocalDateTime tenDaysAgo = LocalDateTime.now().minusSeconds(10); // Cập nhật sau 10 ngày
        Timestamp tenDaysAgoTimestamp = Timestamp.valueOf(tenDaysAgo);

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, orderId);
            stm.setTimestamp(2, tenDaysAgoTimestamp);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật trạng thái đơn hàng tự động thành công.");
            } else {
                System.out.println("Không có đơn hàng nào cần cập nhật.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái đơn hàng tự động: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
