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
import java.util.List;

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
    public ArrayList<OrderDetail> getAllOrderDetail(){
        ArrayList<OrderDetail> list= new ArrayList<>();
        String sql= "select * from OrderDetails";
        try{
            stm= connection.prepareStatement(sql);
            rs= stm.executeQuery();
            while(rs.next()){
                OrderDetail order= new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
                list.add(order);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
     public boolean DeleteOrderDetail(int orderid){
         String sql="Delete from OrderDetails where OrderID= ?";
         try{
             stm= connection.prepareStatement(sql);
             stm.setInt(1, orderid);
             int result= stm.executeUpdate();
             return result > 0;
         }catch(Exception e){
             System.out.println(e);
         }
         return false;
     }
     public List<Integer> getBookIDsForOrder(int orderid) {
        List<Integer> bookIDs = new ArrayList<>();
        String query = "SELECT BookID FROM OrderDetails WHERE OrderID = ?";
        try (
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, orderid);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookIDs.add(rs.getInt("BookID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookIDs;
    }
}
