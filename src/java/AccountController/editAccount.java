/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.ListAccountDao;
import Model.Accounts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
@WebServlet(name = "editAccount", urlPatterns = {"/edit"})
public class editAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editAccount at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id_raw = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(id_raw);
            ListAccountDao dal = new ListAccountDao();
            Accounts a = dal.getAccountByID(id);
            request.setAttribute("account", a);
            request.getRequestDispatcher("updateUserView.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id_raw = request.getParameter("userID");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String active = request.getParameter("isActive");
        String role_raw = request.getParameter("roleID");
        int role;
        int id;
//        boolean isActive;
        Boolean isActive = null;
        if (active != null) {
            if (active.equals("true")) {
                isActive = true;
            } else if (active.equals("false")) {
                isActive = false;
            }
        }
        if (!isValidEmail(email)) {
            request.setAttribute("errorMessage1", "Định dạng email không hợp lệ. Vui lòng nhập địa chỉ email hợp lệ.");
            request.getRequestDispatcher("updateUserView.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(pass)) {
            request.setAttribute("errorMessage2", "Mật khẩu phải dài ít nhất 8 ký tự và chứa ít nhất một chữ in hoa.");
            request.getRequestDispatcher("updateUserView.jsp").forward(request, response);
            return;
        }

        if (!isValidPhoneNumber(phone)) {
            request.setAttribute("errorMessage3", "Số điện thoại phải có 10 số");
            request.getRequestDispatcher("updateUserView.jsp").forward(request, response);
            return;
        }

        try {
            id = Integer.parseInt(id_raw);
            role = Integer.parseInt(role_raw);
//            isActive = Boolean.parseBoolean(active);
            ListAccountDao dal = new ListAccountDao();
            dal.updateAccount(pass, email, fullname, address, phone, role, isActive, LocalDateTime.now(), id);
            request.getSession().setAttribute("mess", "Sửa người dùng thành công!");
            response.sendRedirect("userManager");

        } catch (NumberFormatException e) {
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

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{10}$";
        // Kiểm tra số điện thoại chỉ chứa các chữ số và có độ dài phù hợp (ví dụ: 10 chữ số)
        return phoneNumber.matches(phoneRegex);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
