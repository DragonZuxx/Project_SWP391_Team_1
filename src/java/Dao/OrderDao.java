/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LENOVO //
 */
public class OrderDao extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public Order getOrder(int id) {

        String sql = "select * from Orders where OrderID= ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                return order;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Order> getOrderAll() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderSuccessfull() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders where Status = 'Completed' order by CreatedAt DESC";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderSuccessfull(int pageNumber, int pageSize) {
        ArrayList<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * \n"
                + "FROM Orders \n"
                + "WHERE Status = 'Completed' \n"
                + "ORDER BY CreatedAt DESC \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderUser(int pageNumber, int pageSize, int id) {
        ArrayList<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM Orders WHERE Status = 'Completed' and UserID = ? ORDER BY CreatedAt OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, offset);
            stm.setInt(3, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalPagesUser(int pageSize, int id) {
        String sql = "select count(*) from Orders where Status = 'Completed' and UserID= ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                int totalOrders = rs.getInt(1);
                return (int) Math.ceil((double) totalOrders / pageSize);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 1; // default to 1 if there is an error
    }

    public int getTotalPages(int pageSize) {
        String sql = "select count(*) from Orders where Status = 'Completed'";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int totalOrders = rs.getInt(1);
                return (int) Math.ceil((double) totalOrders / pageSize);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 1; // default to 1 if there is an error
    }

    public ArrayList<Order> getOrderRequest() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders where Status= N'Chờ Xác nhận'";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderRequestPagination(int pageNumber, int pageSize) {
        ArrayList<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM Orders WHERE Status = N'Chờ Xác nhận' ORDER BY CreatedAt OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalRequestPages(int pageSize) {
        String sql = "SELECT COUNT(*) FROM Orders WHERE Status = N'Chờ Xác nhận'";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int totalOrders = rs.getInt(1);
                return (int) Math.ceil((double) totalOrders / pageSize);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 1; // default to 1 if there is an error
    }

    public ArrayList<Order> getOrderShipping() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders where Status= N'Đang giao hàng";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderShippingManager(int pageNumber, int pageSize) {
        ArrayList<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM Orders WHERE Status = N'Đang giao hàng'  ORDER BY CreatedAt OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalShippingPage(int pageSize) {
        String sql = "SELECT COUNT(*) FROM Orders WHERE Status = N'Đang giao hàng'";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int totalOrders = rs.getInt(1);
                return (int) Math.ceil((double) totalOrders / pageSize);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 1; // default to 1 if there is an error
    }

    public boolean UpdateOrder(int orderid, String fullname, String address, String phone, String status) {
        String sql = " Update Orders set  [FullName] = ?,[Address] =? ,[Phone] =?,[Status]= ? where  OrderID=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, fullname);
            stm.setString(2, address);
            stm.setString(3, phone);
            stm.setString(4, status);
            stm.setInt(5, orderid);
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Order> getOrderRequestUser(int id) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders where Status = N'Chờ Xác nhận' and [UserID] = ? order by CreatedAt DESC";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderShipping(int id) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders where Status = N'Đang giao hàng' and [UserID]=?  order by CreatedAt DESC";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Boolean updateOrderReason(int orderId, String newReason, String status) {
        String sql = "UPDATE Orders SET Reason = ?, Status = ? WHERE OrderID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, newReason);
            stm.setString(2, status);
            stm.setInt(3,orderId);
             int result = stm.executeUpdate();
            return result > 0;
            

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        OrderDao orderdao = new OrderDao();
        ArrayList<Order> list = new ArrayList<>();
        list = orderdao.getOrderSuccessfull();
        System.out.println(list.get(1).getAmount());
    }
}
