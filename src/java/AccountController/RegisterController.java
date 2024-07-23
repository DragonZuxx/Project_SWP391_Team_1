/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.AccountDao;
import Model.Accounts;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("signupView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        // Validate email format
        if (!isValidEmail(email)) {
            request.setAttribute("errorMessage", "Invalid email format. Please enter a valid email address.");
            request.getRequestDispatcher("signupView.jsp").forward(request, response);
            return;
        }
        
        // Validate password
        if (!isValidPassword(password)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain at least one uppercase letter.");
            request.getRequestDispatcher("signupView.jsp").forward(request, response);
            return;
        }

        Accounts user = new Accounts();
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setRoleID(3);

        AccountDao userDao = new AccountDao();
        if (userDao.createAccount(user)) {
            response.sendRedirect("register?success");
        } else {
            request.setAttribute("errorMessage", "Failed to register user. Please try again.");
            request.getRequestDispatcher("signupView.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(String password) {
        // Validate password: at least 8 characters with at least one uppercase letter
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }
    
    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    
}