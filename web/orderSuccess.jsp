<%-- 
    Document   : orderSuccess
    Created on : 14 Jul 2024, 23:21:40
    Author     : Aplal
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Xác nhận đặt hàng thành công</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">
    </head>
    <body>
        <jsp:include page="_header.jsp"/>
        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page" style="color: #000">Xác nhận đặt hàng thành công</h2>
            </div>
        </section>
        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <main class="col-lg-9 mb-lg-0 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h4>Cảm ơn bạn đã đặt hàng!</h4>
                                <p>Đơn hàng của bạn đã được xác nhận và đang được xử lý.</p>
                                <p>Thông tin đơn hàng:</p>
                                <ul>
                                    <li><strong>Họ và tên:</strong> ${param.fullName}</li>
                                    <li><strong>Địa chỉ:</strong> ${param.address}</li>
                                    <li><strong>Số điện thoại:</strong> ${param.phone}</li>
                                    <li><strong>Phương thức giao hàng:</strong> ${param['delivery-method-name']}</li>
                                    <li><strong>Thời gian giao hàng dự kiến:</strong> ${param['delivery-time']}</li>
                                    <li><strong>Phương thức thanh toán:</strong> ${param['payment-method']}</li>
                                    <li><strong>Tổng tiền:</strong> <fmt:formatNumber value="${param.totalPrice}" type="currency"/></li>
                                </ul>
                                <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Tiếp tục mua sắm</a>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>
