/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Books;
import Model.Categories;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DMX THANH SON
 */
public class ProductFilterDao extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public List<Books> filterBooks(String selectedPublisher, String priceRange, String order, String categoryID) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT b.* FROM books b JOIN BookCategories bc ON b.BookID = bc.BookID WHERE 1=1";

        // Thêm điều kiện lọc theo thể loại
        if (categoryID != null && !categoryID.isEmpty()) {
            query += " AND bc.CategoryID = ?";
        }

        // Thêm điều kiện lọc theo nhà xuất bản
        if (selectedPublisher != null && !selectedPublisher.isEmpty()) {
            query += " AND b.Publisher = ?";
        }

        // Thêm điều kiện lọc theo khoảng giá
        if (priceRange != null && !priceRange.isEmpty()) {
            String[] ranges = priceRange.split("-");
            if (ranges.length == 2) {
                query += " AND b.Price BETWEEN ? AND ?";
            }
        }

        // Thêm điều kiện sắp xếp
        if (order != null && !order.isEmpty()) {
            switch (order) {
                case "totalBuy-DESC":
                    query += " ORDER BY b.SoldQuantity DESC";
                    break;
                case "createdAt-DESC":
                    query += " ORDER BY b.CreatedAt DESC";
                    break;
                case "price-ASC":
                    query += " ORDER BY b.Price ASC";
                    break;
                default:
                    query += " ORDER BY b." + order.replace("-", " ");
                    break;
            }
        }

        try {
            // Khởi tạo PreparedStatement
            stm = connection.prepareStatement(query);

            int paramIndex = 1;
            // Đặt giá trị cho các tham số truy vấn
            if (categoryID != null && !categoryID.isEmpty()) {
                stm.setString(paramIndex++, categoryID);
            }

            if (selectedPublisher != null && !selectedPublisher.isEmpty()) {
                stm.setString(paramIndex++, selectedPublisher);
            }

            if (priceRange != null && !priceRange.isEmpty()) {
                String[] ranges = priceRange.split("-");
                if (ranges.length == 2) {
                    stm.setDouble(paramIndex++, Double.parseDouble(ranges[0]));
                    stm.setDouble(paramIndex++, ranges[1].equals("infinity") ? Double.MAX_VALUE : Double.parseDouble(ranges[1]));
                }
            }

            // Thực hiện truy vấn
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                // Thiết lập các thuộc tính của book từ ResultSet
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                // Thêm book vào danh sách
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                // Không cần đóng connection vì DBContext sẽ quản lý việc này
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return books;
    }
    
     public List<Books> searchfilterBooks(String selectedPublisher, String priceRange, String order, String searchQuery) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT b.* FROM books b WHERE 1=1";

        // Thêm điều kiện tìm kiếm theo tên sách
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query += " AND b.Title LIKE ?";
        }

        // Thêm điều kiện lọc theo nhà xuất bản
        if (selectedPublisher != null && !selectedPublisher.isEmpty()) {
            query += " AND b.Publisher = ?";
        }

        // Thêm điều kiện lọc theo khoảng giá
        if (priceRange != null && !priceRange.isEmpty()) {
            String[] ranges = priceRange.split("-");
            if (ranges.length == 2) {
                query += " AND b.Price BETWEEN ? AND ?";
            }
        }

        // Thêm điều kiện sắp xếp
        if (order != null && !order.isEmpty()) {
            switch (order) {
                case "totalBuy-DESC":
                    query += " ORDER BY b.SoldQuantity DESC";
                    break;
                case "createdAt-DESC":
                    query += " ORDER BY b.CreatedAt DESC";
                    break;
                case "price-ASC":
                    query += " ORDER BY b.Price ASC";
                    break;
                default:
                    query += " ORDER BY b." + order.replace("-", " ");
                    break;
            }
        }

        try {
            // Khởi tạo PreparedStatement
            stm = connection.prepareStatement(query);

            int paramIndex = 1;
            // Đặt giá trị cho các tham số truy vấn
            if (searchQuery != null && !searchQuery.isEmpty()) {
                stm.setString(paramIndex++, "%" + searchQuery + "%");
            }

            if (selectedPublisher != null && !selectedPublisher.isEmpty()) {
                stm.setString(paramIndex++, selectedPublisher);
            }

            if (priceRange != null && !priceRange.isEmpty()) {
                String[] ranges = priceRange.split("-");
                if (ranges.length == 2) {
                    stm.setDouble(paramIndex++, Double.parseDouble(ranges[0]));
                    stm.setDouble(paramIndex++, ranges[1].equals("infinity") ? Double.MAX_VALUE : Double.parseDouble(ranges[1]));
                }
            }

            // Thực hiện truy vấn
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                // Thiết lập các thuộc tính của book từ ResultSet
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                // Thêm book vào danh sách
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                // Không cần đóng connection vì DBContext sẽ quản lý việc này
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return books;
    }

}
