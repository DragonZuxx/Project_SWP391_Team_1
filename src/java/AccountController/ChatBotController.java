/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.BookDao;
import Model.Books;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ChatBotController", urlPatterns = {"/chat-bot"})
public class ChatBotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String data = "";
        List<Books> bookList = new BookDao().getAllBooks();
        
        for (Books book : bookList) {
            data += book.toString() + ";";
        }
        
        request.setAttribute("data", data);
        request.getRequestDispatcher("chatBot.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    public static void getBot(HttpServletRequest request) {
        String data = "";
        List<Books> bookList = new BookDao().getAllBooks();
        
        for (Books book : bookList) {
            data += book.toString() + ";";
        }
        
        request.setAttribute("data", data);
    }


}
