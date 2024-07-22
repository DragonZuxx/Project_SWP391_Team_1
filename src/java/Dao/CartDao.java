/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Cart;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Aplal
 */
public class CartDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public int getCartIdByUserID(int userid) {
        String sql = "SELECT * FROM Cart WHERE UserID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userid);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("CartID");
            }
        } catch (SQLException e) {
            System.out.println("getCartIdByUserID: " + e.getMessage());
        }
        return 0;
    }

    public Cart getCartByUserID(int UserID) {
        String sql = "SELECT * FROM Cart WHERE UserID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, UserID);
            rs = stm.executeQuery();
            if (rs.next()) {
                int cartID = rs.getInt("CartID");
                LocalDateTime createAt = rs.getTimestamp("CreatedAt").toLocalDateTime();
                LocalDateTime updateAt = rs.getTimestamp("UpdatedAt").toLocalDateTime();
                Cart cart = new Cart(cartID, UserID, createAt, updateAt);
                return cart;
            }
        } catch (SQLException e) {
            System.out.println("getCartByUserID: " + e.getMessage());
        }
        return null;
    }

    public boolean addNewCart(Cart cart) {
        String sql = "INSERT INTO [dbo].[Cart] ([UserID]) VALUES (?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cart.getUserID());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("addNewCart: " + e.getMessage());
        }
        return true;
    }

}
