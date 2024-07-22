/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package OrderController;

import Dao.AccountDao;
import Dao.BookDao;
import Dao.OrderDao;
import Dao.OrderDetailDao;
import Dao.ShippingDao;
import Model.Accounts;
import Model.Books;
import Model.Order;
import Model.OrderDetail;
import Model.ShippingDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="OrderManagerUser", urlPatterns={"/orderUser"})
public class OrderManagerUser extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet OrderManagerUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderManagerUser at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
            response.sendRedirect("Notfound.jsp");
        } else {
            ArrayList<Accounts> listAccount = new ArrayList<>();
            ArrayList<ShippingDetails> listShip = new ArrayList();
            ArrayList<Books> listBook = new ArrayList<>();
            ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
            BookDao bookdao = new BookDao();
            ShippingDao shipdao = new ShippingDao();
            AccountDao accountdao = new AccountDao();
            OrderDetailDao orderdetaildao = new OrderDetailDao();
            listOrderDetail = orderdetaildao.getAllOrderDetail();
            listShip = shipdao.getAllShip();
            listBook = bookdao.getAllBooks();
            listAccount = (ArrayList<Accounts>) accountdao.getAll();
            
              OrderDao orderDao = new OrderDao();
              ArrayList<Order> listOrderAll= orderDao.getOrderAll();
            //đơn hàng chờ xác nhận
            ArrayList<Order> listOrderRequest= orderDao.getOrderRequestUser(account.getUserID());
            //danh sách đơn hàng đang giao hàng
            ArrayList<Order> listOrderShipping= orderDao.getOrderShipping(account.getUserID());
            //đơn hàng giao hàng thành công.
            int pageNumber = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            int pageSize = 5;
          
            ArrayList<Order> listorderSucces = orderDao.getOrderUser(pageNumber, pageSize,account.getUserID());

            int totalPages = orderDao.getTotalPagesUser(pageSize, account.getUserID());
            
            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("listorder", listorderSucces);
            request.setAttribute("listOrderAll", listOrderAll);
           
            request.setAttribute("listorderShipping", listOrderShipping);
            request.setAttribute("listorderRequest", listOrderRequest);
            request.setAttribute("listbook", listBook);
            request.setAttribute("listorderdetail", listOrderDetail);
            request.setAttribute("listship", listShip);
            request.setAttribute("listaccount", listAccount);
            request.getRequestDispatcher("orderManagerUser.jsp").forward(request, response);
    }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String reson= request.getParameter("cancelReasonSelect");
         String id= request.getParameter("orderid");
         String status= "Hủy đơn hàng";
         int orderid= Integer.parseInt(id);
         OrderDao orderDao= new OrderDao();
         Boolean check= orderDao.updateOrderReason(orderid, reson,status);
         if(check){
             request.setAttribute("mess", "Hủy đơn hàng thành công");
         }else{
             request.setAttribute("mess", "Hủy đơn hàng không thành công");
         }
         doGet(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
