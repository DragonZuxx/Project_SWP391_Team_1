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
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "OrderShippingManager", urlPatterns = {"/orderShipping"})
public class OrderShippingManager extends HttpServlet {

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
            out.println("<title>Servlet OrderShippingManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderShippingManager at " + request.getContextPath() + "</h1>");
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

        if (account == null || account.getRoleID() == 3) {
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

            int pageNumber = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            int pageSize = 5;
            OrderDao orderDao = new OrderDao();
            ArrayList<Order> listOrderShip = orderDao.getOrderShippingManager(pageNumber, pageSize);

            int totalPages = orderDao.getTotalShippingPage(pageSize);
            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("listorder", listOrderShip);

            request.setAttribute("listbook", listBook);
            request.setAttribute("listorderdetail", listOrderDetail);
            request.setAttribute("listship", listShip);
            request.setAttribute("listaccount", listAccount);
            request.getRequestDispatcher("orderShippingManager.jsp").forward(request, response);
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

        if (account == null || account.getRoleID() == 3) {
            response.sendRedirect("Notfound.jsp");
        } else {
            String searchTerm = request.getParameter("searchorder");
            int search = Integer.parseInt(searchTerm);
            OrderDao orderDao = new OrderDao();
            List<Order> orderShipping = orderDao.getOrderShipping();
            List<Order> filteredOrders = new ArrayList<>();

            if (!searchTerm.isEmpty() && !searchTerm.trim().isEmpty()) {
                for (Order order : orderShipping) {
                    if (order.getId() == search) {
                        filteredOrders.add(order);
                    }
                }
            } else {
                filteredOrders = orderShipping;
            }
            ArrayList<Order> listOrder2 = new ArrayList<>();
            ArrayList<Accounts> listAccount = new ArrayList<>();
            ArrayList<ShippingDetails> listShip = new ArrayList();
            ArrayList<Books> listBook = new ArrayList<>();
            ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
            BookDao bookdao = new BookDao();
            ShippingDao shipdao = new ShippingDao();
            OrderDao orderdao = new OrderDao();
            AccountDao accountdao = new AccountDao();
            OrderDetailDao orderdetaildao = new OrderDetailDao();
            listOrderDetail = orderdetaildao.getAllOrderDetail();
            listShip = shipdao.getAllShip();
            listBook = bookdao.getAllBooks();
            listAccount = (ArrayList<Accounts>) accountdao.getAll();
            listOrder2 = orderdao.getOrderRequest();
            int count = listOrder2.size();
           

              if(filteredOrders.isEmpty()){
                request.setAttribute("mess", "Không tìm thấy order nào.");
            }
            request.setAttribute("listviewOrder", orderShipping);
            request.setAttribute("countRequest", count);
            request.setAttribute("listbook", listBook);
            request.setAttribute("listorderdetail", listOrderDetail);
            request.setAttribute("listship", listShip);
            request.setAttribute("listaccount", listAccount);
            request.setAttribute("detailorder", filteredOrders);
          
            
            request.getRequestDispatcher("orderShippingManager.jsp").forward(request, response);
        }
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
