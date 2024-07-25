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
import java.util.Timer;
import java.util.TimerTask;

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
        String sql = "select * from Orders where Status = N'Đã nhận được hàng' order by CreatedAt DESC";
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
                + "WHERE Status = N'Đã nhận được hàng'\n"
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
        String sql = "SELECT * FROM Orders WHERE Status = N'Đã nhận được hàng' and UserID = ? ORDER BY CreatedAt OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
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
        String sql = "select count(*) from Orders where Status = N'Đã nhận được hàng' and UserID= ?";
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
        String sql = "select count(*) from Orders where Status = N'Đã nhận được hàng'";
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
        String sql = "select * from Orders where Status= N'Đang giao hàng'";
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

    public boolean UpdateOrder(int orderid, String fullname, String address, String phone, String status, LocalDateTime update) {
        String sql = "UPDATE Orders SET FullName = ?, Address = ?, Phone = ?, Status = ?, UpdatedAt = ? WHERE OrderID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, fullname);
            stm.setString(2, address);
            stm.setString(3, phone);
            stm.setString(4, status);
            stm.setTimestamp(5, java.sql.Timestamp.valueOf(update));
            stm.setInt(6, orderid);
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

    public Boolean updateOrderReason(int orderId, String newReason, String status, LocalDateTime update) {
        String sql = "UPDATE Orders SET Reason = ?, Status = ?, [UpdatedAt] = ? WHERE OrderID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, newReason);
            stm.setString(2, status);
            stm.setTimestamp(3, java.sql.Timestamp.valueOf(update));
            stm.setInt(4, orderId);

            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateOrderSucces(int orderId, String status, LocalDateTime update) {
        String sql = "UPDATE Orders SET Status = ?, [UpdatedAt] = ? WHERE OrderID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, status);
            stm.setTimestamp(2, java.sql.Timestamp.valueOf(update));
            stm.setInt(3, orderId);

            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //OrderCancelManager
    public ArrayList<Order> getOrderCancelled(int pageNumber, int pageSize) {
        ArrayList<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM Orders WHERE Status = N'Hủy đơn hàng' ORDER BY CreatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
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
                        rs.getString(10)
                );
                list.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Order> getOrderCancel() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from Orders where Status= N'Hủy đơn hàng'";
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

    public int getTotalCancelledPages(int pageSize) {
        String sql = "SELECT COUNT(*) FROM Orders WHERE Status = N'Hủy đơn hàng'";
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

public void autoUpdateOrderStatus(int orderId) {
    String sql = "SELECT Status, UpdatedAt FROM Orders WHERE OrderID = ?";
    try {
        if (connection != null) {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String status = rs.getString("Status");
                Timestamp updatedAt = rs.getTimestamp("UpdatedAt");

                // Kiểm tra nếu trạng thái đơn hàng là "Đang giao hàng"
                if (status.equals("Đang giao hàng")) {
                    // Cập nhật trạng thái thành "Đã nhận được hàng" sau 10 ngày
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (updateOrderStatus(orderId, "Đã nhận được hàng")) {
                                System.out.println("Đơn hàng " + orderId + " đã được cập nhật thành 'Đã nhận được hàng'.");
                            } else {
                                System.out.println("Không thể cập nhật trạng thái đơn hàng " + orderId + ".");
                            }
                        }
                    }, 10L * 24 * 60 * 60 * 1000); 
                }
            }
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
    }
}


private boolean updateOrderStatus(int orderId, String status) {
    String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, status);
        stm.setInt(2, orderId);
        int rowsAffected = stm.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Lỗi khi cập nhật trạng thái đơn hàng: " + e.getMessage());
        return false;
    }
}

    public static void main(String[] args) {
        OrderDao orderdao = new OrderDao();
        ArrayList<Order> listOrder3 = new ArrayList<>();
        listOrder3 = orderdao.getOrderShipping();
        int countShip = listOrder3.size();
        System.out.println(countShip);
    }

}
