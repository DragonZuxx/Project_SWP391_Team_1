/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package CartController;

import Dao.BookDao;
import Dao.CartDao;
import Dao.CartItemDao;
import Dao.OrderDao;
import Dao.OrderDetailDao;
import Dao.OrderStatusDao;
import Dao.PaymentDetailDao;
import Dao.PromotionDao;
import Dao.ShippingDao;
import Model.Accounts;
import Model.Order;
import Model.OrderDetail;
import Model.OrderStatusHistory;
import Model.PaymentDetail;
import Model.ShippingDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author Aplal
 */
@WebServlet(name="OrderSucessfullController", urlPatterns={"/orderSucessfull"})
public class OrderSucessfullController extends HttpServlet {
   private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }
    private BookDao bookDao;
    private PromotionDao promotionDao;
    private CartItemDao cartitemDao;
    private CartDao cartDao;
    private OrderDao orderDao;
    private OrderDetailDao orderdetailDao;
    private ShippingDao shipingDao;
    private OrderStatusDao orderstatusDao;
    private PaymentDetailDao paymentdateilDao;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDao();
        promotionDao = new PromotionDao();
        cartitemDao = new CartItemDao();
        cartDao = new CartDao();
        orderDao = new OrderDao();
        orderdetailDao = new OrderDetailDao();
        shipingDao = new ShippingDao();
        orderstatusDao = new OrderStatusDao();
        paymentdateilDao = new PaymentDetailDao();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String[] bookIDs = request.getParameterValues("bookID");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("price");
        String deliveryMethod = request.getParameter("delivery-method");
        String paymentMethod = request.getParameter("payment-method");
        String totalPrice = request.getParameter("totalPrice");
        String deliveryMethodName = request.getParameter("delivery-method-name");
        String deliveryTime = request.getParameter("delivery-time");
        String status = "Chờ Xác nhận";
        Accounts user = getAccountsInfoSession(request);
        int userid = user.getUserID();
        String userID = String.valueOf(userid);

        if (user == null) {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
            return;
        }

        // Validation
        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Full Name is required.");
            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Address is required.");
            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
            return;
        }

        if (phone == null || !phone.matches("\\d{10,15}")) {
            request.setAttribute("errorMessage", "Valid phone number is required (10-15 digits).");
            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
            return;
        }

        // Insert into Orders table
        Order order = new Order();
        order.setUserid(userid);
        order.setFullname(fullName);
        order.setAddress(address);
        order.setPhone(phone);
        order.setAmount(totalPrice);
        order.setStatus(status);
        boolean orderResult = orderDao.addNewOrder(order);
        if (orderResult) {
            int orderId = orderDao.getOrderIdbyUserID(userid); // Assuming you have a method to get the last inserted order ID

            // Insert into OrderDetails table
            for (int i = 0; i < bookIDs.length; i++) {
                int bookID = Integer.parseInt(bookIDs[i]);
                int quantity = Integer.parseInt(quantities[i]);
                String price = String.valueOf(prices[i]);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderid(orderId);
                orderDetail.setBookid(bookID);
                orderDetail.setQuantity(quantity);
                orderDetail.setUnitPrice(price);
                boolean orderDetailResult = orderdetailDao.addNewOrderDetail(orderDetail);

                if (!orderDetailResult) {
                    request.setAttribute("errorMessage", "Failed to place order. Please try again.");
                    request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
                    return;
                }
            }

            OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
            orderStatusHistory.setOrderid(orderId);
            orderStatusHistory.setStatus(status);
            orderStatusHistory.setChangeDate(java.time.LocalDate.now().plusDays(5).toString());
            boolean orderStatusResult = orderstatusDao.addOrderStatusHistory(orderStatusHistory);
            if (!orderStatusResult) {
                request.setAttribute("errorMessage", "Failed to place order_4. Please try again.");
                request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
                return;
            }

            ShippingDetails shippingDetails = new ShippingDetails();
            shippingDetails.setOrderId(orderId);
            shippingDetails.setShippingMethod(deliveryMethodName);
            shippingDetails.setShippingCost(deliveryMethod);
            shippingDetails.setEstimatedDeliveryDate(Date.valueOf(deliveryTime));
            boolean shippingResult = shipingDao.addShippingDetail(shippingDetails);
            if (!shippingResult) {
                request.setAttribute("errorMessage", "Failed to place order_1. Please try again.");
                request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
                return;
            }

            PaymentDetail paymentDetail = new PaymentDetail();
            paymentDetail.setOrderID(orderId);
            paymentDetail.setPaymentMethod(paymentMethod);
            paymentDetail.setPaymentDate(Date.valueOf(deliveryTime));
            paymentDetail.setPaymentStatus(status);
            paymentDetail.setAmountPaid(0);
            boolean paymentResult = paymentdateilDao.addPaymentDetail(paymentDetail);
            if (!paymentResult) {
                request.setAttribute("errorMessage", "Failed to place order_2. Please try again.");
                request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
                return;
            }

            // Delete cart items
            for (int i = 0; i < bookIDs.length; i++) {
                int cartid = cartDao.getCartIdByUserID(userid);
                int bookID = Integer.parseInt(bookIDs[i]);
                int quantity = Integer.parseInt(quantities[i]);
                boolean deleteCartResult = cartitemDao.deleteCartItemsByUserID(cartid, bookID, quantity);
                if (!deleteCartResult) {
                    request.setAttribute("errorMessage", "Failed to place order_6. Please try again.");
                    request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
                    return;
                }
            }

//            // Update stock quantity
//            for (int i = 0; i < bookIDs.length; i++) {
//                int bookID = Integer.parseInt(bookIDs[i]);
//                int quantity = Integer.parseInt(quantities[i]);
//                int quantityInStock = bookDao.getStockByBookID(bookID);
//                int soldQuantity = bookDao.getSoldQuantitybyBookID(bookID);
//                int newQuantityInStock = quantityInStock - quantity;
//                int newSoldQuantity = soldQuantity + quantity;
//                boolean updateQuantityInStockResult = bookDao.updateQuantityInStock(bookID, newQuantityInStock, newSoldQuantity);
//                if (!updateQuantityInStockResult) {
//                    request.setAttribute("errorMessage", "Failed to place order_5. Please try again.");
//                    request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
//                    return;
//                }
//            }
            orderDao.autoUpdateOrderStatus(orderId);
            request.setAttribute("successMessage", "Order placed successfully.");
            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to place order_3. Please try again.");
            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
        }
    }
}
