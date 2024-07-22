
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dal.DBContext;
import Model.Accounts;

/**
 *
 * @author Aplal
 */
public class AccountDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    // Check Email
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

    public Accounts FindUserById(int id) {
        String sql = "select * from Users where UserID= ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
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

    // Tạo account
    public boolean createUser(String name, String pass, String email, String fullname, String address, String phone,
            int role) {
        String query = "INSERT INTO Users (Username, Password, Email, FullName, Address, Phone, RoleID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, pass);
            stm.setString(3, email);
            stm.setString(4, fullname);
            stm.setString(5, address);
            stm.setString(6, phone);
            stm.setInt(7, role);

            int result = stm.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            System.out.println("createUser" + e.getMessage());
        }
        return false;
    }

    // Úpdate password
    public boolean updatePasswordByEmail(String email, String newPassword) {
        String query = "UPDATE Users SET Password = ? WHERE Email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, email);

            int result = statement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            System.out.println("updatePasswordByEmail: " + e.getMessage());
        }
        return false;
    }

    // Update password by pass and email
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

    public void addAccount(String userName, String pass, String email, String fullname, String address, String Phone,
            int role) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[Email]\n"
                + "           ,[FullName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[RoleID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, pass);
            stm.setString(3, email);
            stm.setString(4, fullname);
            stm.setString(5, address);
            stm.setString(6, Phone);
            stm.setInt(7, role);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Accounts checkAccountExistByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
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

    public void deleteAccount(int id) {
        String sql = "DELETE FROM [dbo].[Users]\n"
                + "      WHERE UserID = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
        }
    }
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

}
