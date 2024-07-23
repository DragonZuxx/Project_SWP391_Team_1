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
@WebServlet(name = "addAccount", urlPatterns = {"/add"})
public class addAccount extends HttpServlet {

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
            out.println("<title>Servlet addAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addAccount at " + request.getContextPath() + "</h1>");
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

        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String active = request.getParameter("isActive");
        int roleId;
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
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(pass)) {
            request.setAttribute("errorMessage2", "Mật khẩu phải dài ít nhất 8 ký tự và chứa ít nhất một chữ in hoa.");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        if (!isValidPhoneNumber(phone)) {
            request.setAttribute("errorMessage3", "Số điện thoại phải có 10 số");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        try {
            roleId = Integer.parseInt(role);
            ListAccountDao dal = new ListAccountDao();
            Accounts ac = dal.checkAccountExistByEmail(email);
            if (ac == null) {

                dal.addAccount(pass, email, fullName, address, phone, roleId, isActive, LocalDateTime.now(), LocalDateTime.now());
                request.getSession().setAttribute("succsess", "Thêm thành công tài khoản!");
                response.sendRedirect("userManager");
            } else {
                request.setAttribute("mess4", "Email đã tồn tại!");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
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
