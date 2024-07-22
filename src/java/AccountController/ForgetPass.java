/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ForgetPass", urlPatterns = {"/forgetpass"})
public class ForgetPass extends HttpServlet {

    private static final String USERNAME = "bookhavenshop03@gmail.com";
    private static final String PASSWORD = "dryy mszj cwmr emok";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

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
            out.println("<title>Servlet ForgetPass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgetPass at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String otp = generateOtp();

        if (sendOtpEmail(email, otp)) {
            request.getSession().setAttribute("otp", otp);
            request.getSession().setAttribute("email", email);
            response.sendRedirect("ChangePass.jsp");
        } else {
            request.setAttribute("mess", "Failed to send OTP email.");
            response.sendRedirect("ForgetPass.jsp");
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
        String otp = request.getParameter("otp");
        String email= request.getParameter("email");
        String checkemail = (String) request.getSession().getAttribute("email");
        String checkotp = (String) request.getSession().getAttribute("otp");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        if(email.equals(checkemail)){
             if (isValidPassword(pass)) {
            if (pass.equals(repass)) {
                if (otp.equals(checkotp)) {
                    AccountDao accountdao = new AccountDao();
                    if (accountdao.updatePasswordByEmail(email, pass)) {
                        request.getSession().removeAttribute("email");
                        request.setAttribute("mess", "Khôi phục mật khẩu thành công!");
                        request.getRequestDispatcher("signinView.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mess", "Khôi phục mật khẩu không thành công! Mời thử lại.");
                        request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mess", "Sai OTP rồi! Mời nhập lại!");
                    request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Mật khẩu và xác nhận mật khẩu phải trùng khớp.");
                request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Mật khẩu phải dài ít nhất 8 ký tự và chứa ít nhất một chữ in hoa.");
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
        }
        }else{
            request.setAttribute("mess", "Email không trùng khớp! Mời thử lại.");
            request.getRequestDispatcher("ForgetPass.jsp").forward(request, response);
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "VerifyOtpServlet handles OTP verification for user authentication.";
    }

    private boolean isValidPassword(String password) {
        // Validate password: at least 8 characters with at least one uppercase letter
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }

    private String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    private boolean sendOtpEmail(String toEmail, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP");
            message.setText("Your OTP is: " + otp);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
