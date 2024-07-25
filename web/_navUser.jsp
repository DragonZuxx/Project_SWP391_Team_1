<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<aside class="col-md-3 mb-4">
    <nav class="list-group shadow-sm">
        <a class="list-group-item list-group-item-action ${param.active == 'USER' ? 'active' : ''}" href="${pageContext.request.contextPath}/userView.jsp" role="button">
            <i class="fas fa-user me-2"></i>Tài khoản
        </a>
        <a class="list-group-item list-group-item-action ${param.active == 'ORDER' ? 'active' : ''}" href="${pageContext.request.contextPath}/orderUser" role="button">
            <i class="fas fa-shopping-cart me-2"></i>Đơn hàng của tôi
        </a>
        <a class="list-group-item list-group-item-action ${param.active == 'WISHLIST' ? 'active' : ''}" href="${pageContext.request.contextPath}/wishlistuser" role="button">
            <i class="fas fa-heart me-2"></i>Sản phẩm yêu thích
        </a>
        <a class="list-group-item list-group-item-action ${param.active == 'CHANGE_PASSWORD' ? 'active' : ''}" href="${pageContext.request.contextPath}/changepass" role="button">
            <i class="fas fa-key me-2"></i>Đổi mật khẩu
        </a>

    </nav>
</aside> <!-- col.// -->
