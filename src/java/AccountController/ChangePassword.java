/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.AccountDao;
import Model.Accounts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/changepass"})
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        Accounts account = (Accounts) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("home");
        } else {
            response.sendRedirect("changePasswordView.jsp");
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
        Accounts account = (Accounts) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("signinView.jsp");
            return;
        } else {
            String email = account.getEmail();
            String passSession = account.getPassword();
            String currentpass = request.getParameter("currentPassword");
            String newpass = request.getParameter("newPassword");
            String newpass2 = request.getParameter("newPasswordAgain");
            if (currentpass.equals(passSession)) {
                if (isValidPassword(newpass)) {
                    if (newpass.equals(newpass2)) {
                        AccountDao accountdao = new AccountDao();
                        if (accountdao.updatePasswordByPassAndEmail(currentpass, newpass, email)) {
                            request.setAttribute("mess", "Đổi mật khẩu thành công!");
                            request.getRequestDispatcher("changePasswordView.jsp").forward(request, response);
                        } else {
                            request.setAttribute("mess", "Đồi mật khẩu không thành công! Mời thử lại.");
                            request.getRequestDispatcher("changePasswordView.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("mess", "Mật khẩu mới và mật khẩu nhập lại không trùng khớp. Mời thử lại!");
                        request.getRequestDispatcher("changePasswordView.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mess", "Mật khẩu mới không đúng định danh.Làm ơn nhập độ dài lớn hơn 8 và có 1 chữ cái hoa.");
                    request.getRequestDispatcher("changePasswordView.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Mật khẩu hiện tại không đúng. Mời thử lại!");
                request.getRequestDispatcher("changePasswordView.jsp").forward(request, response);
            }
        }
    }

    private boolean isValidPassword(String password) {
        // Validate password: at least 8 characters with at least one uppercase letter
        return password.length() >= 8 && password.matches(".*[A-Z].*");
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
