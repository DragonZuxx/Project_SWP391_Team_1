/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Authors;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class AuthorDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
//Lấy danh sách Authors theo BookID

    public ArrayList<Authors> getAuthorsByBookId(int id) {
        ArrayList<Authors> authors = new ArrayList<Authors>();
        String sql = "SELECT * FROM Authors WHERE AuthorID IN (SELECT AuthorID FROM BookAuthors WHERE BookID = ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Authors author = new Authors();
                author.setAuthorID(rs.getInt("AuthorID"));
                author.setName(rs.getString("Name"));

                author.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                author.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println("getAuthorsByBookId: " + e.getMessage());
        } finally {
            try {
                // Đóng ResultSet, PreparedStatement và kết nối sau khi sử dụng
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e);
            }
        }
        return authors;
    }
}
