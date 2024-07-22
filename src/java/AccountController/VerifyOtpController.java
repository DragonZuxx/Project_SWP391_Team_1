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

@WebServlet(name="VerifyOtpController", urlPatterns={"/verify"})
public class VerifyOtpController extends HttpServlet {

    private static final String USERNAME = "bookhavenshop03@gmail.com";
    private static final String PASSWORD = "dryy mszj cwmr emok";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer check = (Integer) request.getSession().getAttribute("Check");
        if (check != null && check == 1) {
            String email = (String) request.getSession().getAttribute("email");
            String otp = generateOtp();
            request.getSession().setAttribute("otp2", otp);
            if (sendOtpEmail(email, otp)) {
                response.sendRedirect("emalForm.jsp");
            } else {
                request.setAttribute("mess", "Gửi OTP thất bại.");
                request.getRequestDispatcher("signinView.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 checkOtp(request, response);
    }

    @Override
    public String getServletInfo() {
        return "VerifyOtpServlet handles OTP verification for user authentication.";
    }
    
    private void checkOtp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Integer check = (Integer) request.getSession().getAttribute("Check");
        if (check != null && check == 1) {
            String email = (String) request.getSession().getAttribute("email");
            String enteredOtp = request.getParameter("otp");
            String expectedOtp = (String) request.getSession().getAttribute("otp2");
            AccountDao accountdao = new AccountDao();
            Accounts user = accountdao.checkEmail(email);
            if (enteredOtp.equals(expectedOtp)) {
                request.getSession().setAttribute("account", user);
                request.getSession().removeAttribute("otp2");
                request.getSession().removeAttribute("email");
                request.getSession().removeAttribute("Check");
                response.sendRedirect("home");
            } else {
                String mess = "OTP không đúng. Mời thử lại.";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("emalForm.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("Notfound.jsp");
        }

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
