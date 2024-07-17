/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Accounts;
import Model.BookRevenue;
import Model.Books;
import Model.Wishlist;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ListAccountDao extends DBContext {

    public Accounts getLogin(String email, String pass) {
        String sql = "select * from Users where Email = ? and Password = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            pr.setString(2, pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp(9);
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp(10);
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts a = new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getBoolean(8),
                        localdate1,
                        localdate2);
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void signupAccount(String pass, String email, String fullname, String address, String phoneNumber, int roleId) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Password]\n"
                + "           ,[Email]\n"
                + "           ,[FullName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[RoleID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);

            pr.setString(1, pass);
            pr.setString(2, email);
            pr.setString(3, fullname);
            pr.setString(4, address);
            pr.setString(5, phoneNumber);
            pr.setInt(6, roleId);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Accounts> getAll() {
        List<Accounts> list = new ArrayList<>();
        String sql = "select * from Users";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts a = new Accounts(rs.getInt("UserID"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("isActive"),
                        localdate1,
                        localdate2);

                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addAccount(String pass, String email, String fullname, String address, String Phone, int role, LocalDateTime createAt, LocalDateTime updateAt) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Password]\n"
                + "           ,[Email]\n"
                + "           ,[FullName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[RoleID]\n"
                + "           ,[CreatedAt]\n"
                + "           ,[UpdatedAt])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, pass);
            pr.setString(2, email);
            pr.setString(3, fullname);
            pr.setString(4, address);
            pr.setString(5, Phone);
            pr.setInt(6, role);
            pr.setTimestamp(7, Timestamp.valueOf(createAt));
            pr.setTimestamp(8, Timestamp.valueOf(updateAt));
            pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Accounts checkAccountExistByEmail(String email) {
        String sql = "select * from Users where Email = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                return new Accounts(rs.getInt("UserID"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("isActive"),
                        localdate1,
                        localdate2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Accounts getAccountByID(int id) {
        String sql = "select * from Users where UserID = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                return new Accounts(rs.getInt("UserID"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("isActive"),
                        localdate1,
                        localdate2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateAccount(String pass, String email, String fullname, String address, String Phone, int role, boolean isActive, LocalDateTime updateAt, int UserID) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [Password] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[FullName] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[RoleID] = ?\n"
                + "      ,[isActive] = ?\n"
                + "      ,[UpdatedAt] = ?\n"
                + " WHERE UserID = ?";

        try {

            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, pass);
            pr.setString(2, email);
            pr.setString(3, fullname);
            pr.setString(4, address);
            pr.setString(5, Phone);
            pr.setInt(6, role);
            pr.setBoolean(7, isActive);
            pr.setTimestamp(8, Timestamp.valueOf(updateAt));
            pr.setInt(9, UserID);
            pr.executeUpdate();
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

    public List<Books> getBookByName(String txtSearch) {
        List<Books> list = new ArrayList<>();
        String sql = "select * from Books\n"
                + "where Title like ?";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, "%" + txtSearch + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp(13);
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp(14);
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                list.add(new Books(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getBoolean(11),
                        rs.getBoolean(12),
                        localdate1,
                        localdate2));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<BookRevenue> getMonthlyRevenue() {
        List<BookRevenue> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "	FORMAT(CreatedAt, 'MM') AS Month,\n"
                + "    FORMAT(CreatedAt, 'yyyy') AS Year,\n"
                + "    SUM(SoldQuantity * CAST(Price AS DECIMAL(10, 2))) AS TotalRevenue\n"
                + "FROM \n"
                + "    Books\n"
                + "GROUP BY \n"
                + "        FORMAT(CreatedAt, 'yyyy'),\n"
                + "	FORMAT(CreatedAt, 'MM')\n"
                + "ORDER BY \n"
                + "    Month, Year";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                BookRevenue book = new BookRevenue(rs.getString("Month"),
                        rs.getString("Year"),
                        rs.getDouble("TotalRevenue"));
                list.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Books> getBestSelling() {
        List<Books> list = new ArrayList<>();
        String sql = "SELECT TOP 10 \n"
                + "    BookID, \n"
                + "    Title, \n"
                + "	Publisher,\n"
                + "	Price,\n"
                + "	(Stock - SUM(SoldQuantity)) AS RemainingStock,\n"
                + "	SUM(SoldQuantity) AS TotalSold,\n"
                + "	Description,\n"
                + "	CoverImage\n"
                + "FROM \n"
                + "    Books\n"
                + "GROUP BY \n"
                + "    BookID, \n"
                + "    Title, \n"
                + "	Publisher,\n"
                + "	Price,\n"
                + "	Description,\n"
                + "	CoverImage,\n"
                + "    Stock\n"
                + "ORDER BY \n"
                + "    TotalSold DESC";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                list.add(new Books(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        null,
                        null,
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        null,
                        null,
                        null,
                        null));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }


    public List<Books> getBestSellingByPage(int pageNumber, int pageSize) {
        List<Books> list = new ArrayList<>();
        String sql = "SELECT "
                + "    BookID, \n"
                + "    Title, \n"
                + "    Publisher,\n"
                + "    Price,\n"
                + "    (Stock - SUM(SoldQuantity)) AS RemainingStock,\n"
                + "    SUM(SoldQuantity) AS TotalSold,\n"
                + "    Description,\n"
                + "    CoverImage\n"
                + "FROM \n"
                + "    Books\n"
                + "GROUP BY \n"
                + "    BookID, \n"
                + "    Title, \n"
                + "    Publisher,\n"
                + "    Price,\n"
                + "    Description,\n"
                + "    CoverImage,\n"
                + "    Stock\n"
                + "ORDER BY \n"
                + "    TotalSold DESC\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, (pageNumber - 1) * pageSize); // OFFSET
            pr.setInt(2, pageSize); // FETCH NEXT

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                list.add(new Books(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        null,
                        null,
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        null,
                        null,
                        null,
                        null));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Accounts> getAccountByPage(int page, int pageSize) {
        List<Accounts> list = new ArrayList<>();
        String query = "SELECT * FROM Users ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, (page - 1) * pageSize);
            pr.setInt(2, pageSize);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts account = new Accounts();
                account.setUserID(rs.getInt("UserID"));
                account.setPassword(rs.getString("Password"));
                account.setEmail(rs.getString("Email"));
                account.setFullName(rs.getString("FullName"));
                account.setAddress(rs.getString("Address"));
                account.setPhone(rs.getString("Phone"));
                account.setRoleID(rs.getInt("RoleID"));
                account.setIsActive(rs.getBoolean("IsActive"));
                account.setCreatedAt(localdate1);
                account.setUpdatedAt(localdate2);

                list.add(account);
            }
        } catch (SQLException e) {
            Logger.getLogger(ListAccountDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

        public List<Wishlist> getWishlistBooksByPage(int pageNumber, int pageSize) {
        List<Wishlist> list = new ArrayList<>();
        String sql = "SELECT \n" +
                     "    B.BookID,\n" +
                     "    COUNT(W.WishlistID) AS WishlistCount\n" +
                     "FROM \n" +
                     "    Wishlist W\n" +
                     "    INNER JOIN Books B ON W.BookID = B.BookID\n" +
                     "GROUP BY \n" +
                     "    B.BookID\n" +
                     "ORDER BY \n" +
                     "    WishlistCount DESC\n" +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, (pageNumber - 1) * pageSize);
            pr.setInt(2, pageSize);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Wishlist wl = new Wishlist();
                wl.setWishlistID(rs.getInt("WishlistCount"));
                wl.setBookID(rs.getInt("BookID"));
                list.add(wl);
            }
        } catch (SQLException e) {
                System.out.println(e);
        }
        return list;
    }
        
    public List<Wishlist> getAllWishlistBooks() {
        List<Wishlist> list = new ArrayList<>();
        String sql = "SELECT TOP 10 \n"
                + "    B.BookID,\n"
                + "    COUNT(W.WishlistID) AS WishlistCount\n"
                + "FROM \n"
                + "    Wishlist W\n"
                + "    INNER JOIN Books B ON W.BookID = B.BookID\n"
                + "GROUP BY \n"
                + "    B.BookID\n"
                + "ORDER BY \n"
                + "    WishlistCount DESC";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {

                Wishlist wl = new Wishlist();
                wl.setWishlistID(rs.getInt("WishlistCount"));
                wl.setBookID(rs.getInt("BookID"));
                list.add(wl);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }


    public List<Accounts> getUserByEmail(String txtSearch) {
        List<Accounts> list = new ArrayList<>();
        String sql = "select * from Users where Email like ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, "%" + txtSearch + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp(9);
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp(10);
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts acc = new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getBoolean(8),
                        localdate1,
                        localdate2);
                list.add(acc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Accounts> getUserByEmailpage(String txtSearch, int page, int pageSize) {
        List<Accounts> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Email LIKE ? ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, "%" + txtSearch + "%");
            pr.setInt(1, (page - 1) * pageSize); // OFFSET
            pr.setInt(2, pageSize); // FETCH NEXT
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp(9);
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp(10);
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts acc = new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getBoolean(8),
                        localdate1,
                        localdate2);
                list.add(acc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<BookRevenue> getMonthlyRevenueByYear(int year) {
        List<BookRevenue> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    FORMAT(CreatedAt, 'MM') AS Month,\n"
                + "    FORMAT(CreatedAt, 'yyyy') AS Year,\n"
                + "    SUM(Quantity * CAST(UnitPrice AS DECIMAL(10, 2))) AS TotalRevenue\n"
                + "FROM \n"
                + "    OrderDetails\n"
                + "WHERE \n"
                + "    FORMAT(CreatedAt, 'yyyy') = ?\n"
                + "GROUP BY \n"
                + "    FORMAT(CreatedAt, 'yyyy'),\n"
                + "    FORMAT(CreatedAt, 'MM')\n"
                + "ORDER BY \n"
                + "    Month";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, year);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                BookRevenue book = new BookRevenue(rs.getString("Month"),
                        rs.getString("Year"),
                        rs.getDouble("TotalRevenue"));
                list.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public double getTotalRevenue(int year) {
        String sql = "SELECT \n"
                + "    SUM(Quantity * CAST(UnitPrice AS DECIMAL(10, 2))) AS TotalRevenue\n"
                + "FROM \n"
                + "    OrderDetails\n"
                + "WHERE \n"
                + "    FORMAT(CreatedAt, 'yyyy') = ?";
        double totalRevenue = 0.0;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, year);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("TotalRevenue");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return totalRevenue;
    }

    public static void main(String[] args) {
        ListAccountDao dal = new ListAccountDao();
        BookRevenue bok = new BookRevenue();

        System.out.println(dal.getAllWishlistBooks());

    }
}
