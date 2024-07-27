/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.PaymentDetail;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 *
 * @author Aplal
 */
public class PaymentDetailDao  extends  DBContext{
    PreparedStatement stm;
    ResultSet rs;
public boolean addPaymentDetail(PaymentDetail paymentDetail) {
        try {
            String sql = "INSERT INTO PaymentDetails(OrderID, PaymentMethod, PaymentDate, PaymentStatus ,AmountPaid) VALUES(?,?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, paymentDetail.getOrderID());
            stm.setString(2, paymentDetail.getPaymentMethod());
            stm.setDate(3, (Date) paymentDetail.getPaymentDate());
            stm.setString(4, paymentDetail.getPaymentStatus());
            stm.setFloat(5, (float) paymentDetail.getAmountPaid());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
public boolean updatePaymentDetail(int orderID, String newStatus, LocalDateTime update) {
    try {
        String sql = "UPDATE PaymentDetails SET PaymentStatus = ?,  UpdatedAt = ? WHERE OrderID = ?";
        stm = connection.prepareStatement(sql);
        stm.setString(1, newStatus);
        stm.setTimestamp(2, java.sql.Timestamp.valueOf(update));
        stm.setInt(3, orderID);
        int result = stm.executeUpdate();
        return result > 0;
    } catch (Exception e) {
        System.out.println(e);
    }
    return false;
}

}
