
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package CartController;

import Dao.CartDao;
import Dao.CartItemDao;
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
 * @author Aplal
 */
@WebServlet(name="CountCart", urlPatterns={"/CountCart"})
public class CountCart extends HttpServlet {
   
private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }

    private CartItemDao cartitemDao;
    private CartDao cartDao;

    @Override
    public void init() throws ServletException {
        super.init();

        cartitemDao = new CartItemDao();
        cartDao = new CartDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Accounts user = getAccountsInfoSession(request);
        if (user == null) {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        } else {
            int userid = user.getUserID();
            int cartid = cartDao.getCartIdByUserID(userid);
            int count = cartitemDao.getQuantityByCartID(cartid);
            request.getSession().setAttribute("count", count);
        }
    } 

}
