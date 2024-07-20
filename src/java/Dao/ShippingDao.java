/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.ShippingDetails;
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

    public ArrayList<ShippingDetails> getAllShip() {
        ArrayList<ShippingDetails> list = new ArrayList();
        String sql = "select * from ShippingDetails";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                ShippingDetails shipping = new ShippingDetails(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getTimestamp("CreatedAt").toLocalDateTime(),
                        rs.getTimestamp("UpdatedAt").toLocalDateTime());
                list.add(shipping);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public boolean UpdateShipping(String address, int orderid){
        String sql= "Update ShippingDetails SET ShippingAddress = ? where OrderID = ?";
        try{
            stm= connection.prepareStatement(sql);
            stm.setString(1, address);
            stm.setInt(2, orderid);
            int result = stm.executeUpdate();
            return result > 0;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public static void main(String[] args) {
        ShippingDao shipdao= new ShippingDao();
       boolean re= shipdao.UpdateShipping("Thường Tín, Hà Nội", 2);
        System.out.println(re);
    }
}
