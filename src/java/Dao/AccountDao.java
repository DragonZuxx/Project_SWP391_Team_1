/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Accounts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AccountDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    public Accounts getLogin(String email, String pass) {
        String sql = "select * from Users where Email= ? and Password =?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, pass);
            rs = stm.executeQuery();
            if (rs.next()) {
                Accounts a = new Accounts();
                a.setUserID(rs.getInt("UserID"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setFullName(rs.getString("FullName"));
                a.setAddress(rs.getString("Address"));
                a.setPhone(rs.getString("Phone"));
                a.setRoleID(rs.getInt("RoleID"));
                a.setIsActive(rs.getBoolean("IsActive"));
                a.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                a.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return a;
            }
        } catch (SQLException e) {
            System.out.println("getLogin" + e.getMessage());
        }
        return null;
    }
     public Accounts checkEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email= ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                Accounts a = new Accounts();
                a.setUserID(rs.getInt("UserID"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setFullName(rs.getString("FullName"));
                a.setAddress(rs.getString("Address"));
                a.setPhone(rs.getString("Phone"));
                a.setRoleID(rs.getInt("RoleID"));
                a.setIsActive(rs.getBoolean("IsActive"));
                a.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                a.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
   
}
