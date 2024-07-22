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
@WebServlet(name = "VerifyOtpController", urlPatterns = {"/verify"})
public class VerifyOtpController extends HttpServlet {

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
            out.println("<title>Servlet VerifyOtpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyOtpController at " + request.getContextPath() + "</h1>");
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
        Integer check = (Integer) request.getSession().getAttribute("Check");
        if (check != null && check ==1) {
            String email = (String) request.getSession().getAttribute("email");
            String otp = generateOtp();
            request.getSession().setAttribute("otp2", otp);
            if (sendOtpEmail(email, otp)) {
                response.sendRedirect("emalForm.jsp");
            } else {
                request.setAttribute("mess", "Gửi OTP thất bại.");
               request.getRequestDispatcher("signinView.jsp").forward(request, response);
            }
        }else{
            response.sendRedirect("home");
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
        int check = (int) request.getSession().getAttribute("Check");
        if (check == 1) {
        String email = (String) request.getSession().getAttribute("email");
        String enteredOtp = request.getParameter("otp");
        String expectedOtp = (String) request.getSession().getAttribute("otp2");
         AccountDao accountdao= new AccountDao();
        Accounts user= accountdao.checkEmail(email);
            if (enteredOtp.equals(expectedOtp)) {
                request.getSession().setAttribute("account", user);
                request.getSession().removeAttribute("otp2");
                request.getSession().removeAttribute("email");
                request.getSession().removeAttribute("Check");
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                String mess = "OTP không đúng. Mời thử lại.";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("emalForm.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("Notfound.jsp");
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
