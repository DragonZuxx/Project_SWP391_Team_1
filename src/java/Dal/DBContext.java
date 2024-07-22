/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

/**
 *
 * @author Aplal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        //@Students: You are allowed to edit user, pass, url variables to fit 
        //your system configuration
        //You can also add more methods for Database Interaction tasks. 
        //But we recommend you to do it in another class
        // For example : StudentDBContext extends DBContext , 
        //where StudentDBContext is located in dal package, 
        try {
            String user = "sa";
            String pass = "123";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=BookStoreDB_Test_3";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBContext dbContext = new DBContext();

        try {
            if (dbContext.connection != null && !dbContext.connection.isClosed()) {
                System.out.println("Connected to the database.");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException ex) {
            System.out.println("Exception while checking connection: " + ex.getMessage());
        } finally {
            try {
                if (dbContext.connection != null) {
                    dbContext.connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception while closing connection: " + ex.getMessage());
            }
        }
    }
}
