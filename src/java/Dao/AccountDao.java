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
import java.util.List;

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

    public boolean updatePasswordByPassAndEmail(String currentPassword, String newPassword, String email) {
        String query = "UPDATE Users SET Password = ? WHERE Password = ? AND Email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, currentPassword);
            statement.setString(3, email);

            int result = statement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            System.out.println("updatePasswordByPassAndEmail: " + e.getMessage());
        }
        return false;
    }

    public void updateUser(String pass, String fullname, String address, String phone,
            int userID) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET  [Password] = ?\n"
                + "      ,[FullName] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Phone] = ?\n"
                + " WHERE UserID = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, pass);
            stm.setString(2, fullname);
            stm.setString(3, address);
            stm.setString(4, phone);
            stm.setInt(5, userID);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Accounts getAccountByID(int id) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        Accounts a = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                a = new Accounts();
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
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }
    //Lấy thông tin người dùng bằng Id

    public Accounts getAccountByUserID(int id) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Accounts account = new Accounts();
                account.setUserID(rs.getInt("UserID"));
                account.setPassword(rs.getString("Password"));
                account.setEmail(rs.getString("Email"));
                account.setFullName(rs.getString("FullName"));
                account.setAddress(rs.getString("Address"));
                account.setPhone(rs.getString("Phone"));
                account.setRoleID(rs.getInt("RoleID"));
                account.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                account.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return account;
            }
        } catch (Exception e) {
            System.out.println("getAccountByUserID: " + e.getMessage());
        }
        return null;
    }

    public List<Accounts> getAll() {
        List<Accounts> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
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
                accounts.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return accounts;
    }

    public boolean createAccount(Accounts account) {
        String sql = "INSERT INTO Users (Email, Password, FullName, Address, Phone, RoleID, IsActive, CreatedAt, UpdatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullName());
            statement.setString(4, account.getAddress());
            statement.setString(5, account.getPhone());
            statement.setInt(6, account.getRoleID());
            statement.setBoolean(7, true);

            int result = statement.executeUpdate();
            return result > 0; // Returns true if at least one record was inserted
        } catch (SQLException e) {
            System.out.println("createAccount: " + e.getMessage());
        }
        return false; // Returns false if an error occurred or no record was inserted
    }

}
