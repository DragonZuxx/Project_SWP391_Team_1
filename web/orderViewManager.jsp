<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp" />
        <title>Đơn hàng</title>
        <!-- Bootstrap CSS -->

        <!-- Custom CSS -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_headerAdmin.jsp" />

        <c:set var="account" value="${sessionScope.account}" />
        <c:if test="${empty account or account.getRoleID() == 3}">
            <% response.sendRedirect("Notfound.jsp"); %>
        </c:if>
        <section class="section-pagetop bg-light py-3">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-3">
                        <h2 class="title-page">Quản lý đơn hàng</h2>
                    </div>
                    <div class="col-md-3">
                        <form action="listorder" method="post" class="search-form">
                            <div class="input-group">
                                <input type="number" name="searchorder" class="form-control" placeholder="Tìm kiếm đơn hàng..." aria-label="Tìm kiếm đơn hàng..." aria-describedby="button-addon2">
                                <button class="btn btn-outline-primary" type="submit" id="button-addon2">
                                    <i class="bi bi-search"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-2 text-end">
                        <a href="orderShipping" class="btn btn-outline-info">
                            <i class="bi bi-basket"></i> 
                            <span class="badge bg-primary">${requestScope.countShip}</span>
                            <span class="d-block">Đang giao hàng</span>
                        </a>
                    </div>
                    <div class="col-md-2 text-end">
                        <a href="orderRequest" class="btn btn-outline-warning">
                            <i class="bi bi-basket"></i> 
                            <span class="badge bg-primary">${requestScope.countRequest}</span>
                            <span class="d-block">Chờ xử lí</span>
                        </a>
                    </div>
                    <div class="col-md-2 text-end">
                        <a href="orderCancel" class="btn btn-outline-danger">
                            <i class="bi bi-basket"></i> 
                            <span class="badge bg-primary">${requestScope.countCancelled}</span>
                            <span class="d-block">Đã hủy</span>
                        </a>
                    </div>
                </div>
            </div>
        </section>




        <h1 class="text-danger text-center" style="font-size: 24px; font-family: Arial, sans-serif;">${requestScope.mess}</h1>

        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <main class="col-md-12">
                        <div class="table-responsive-xxl">
                               <c:if test="${empty requestScope.listorder}">
                                <p style="text-align: center; color: red; font-size: 20px;">Không có đơn hàng nào.</p>
                            </c:if>
                            <c:if test="${not empty requestScope.listorder}">
                            <table class="table table-bordered table-striped table-hover align-middle">
                                <thead>
                                    <tr>
                                        <th scope="col" style="min-width: 125px;">Mã đơn hàng</th>
                                        <th scope="col" style="min-width: 125px;">Người mua</th>
                                        <th scope="col" style="min-width: 100px;">Ngày mua</th>
                                        <th scope="col" style="min-width: 100px;">Tổng tiền</th>
                                        <th scope="col" style="min-width: 175px;">Trạng thái đơn hàng</th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${requestScope.listorder}">
                                                <tr>
                                                    <th scope="row">${order.id}</th>
                                                        <c:forEach var="account" items="${requestScope.listaccount}">
                                                            <c:if test="${account.getUserID() == order.userid}">
                                                            <td>${account.getEmail()}</td>
                                                        </c:if>
                                                    </c:forEach>
                                                    <td>${order.createdat}</td>
                                                    <td><fmt:formatNumber pattern="#,##0" value="${order.amount}"/>₫</td>
                                                    <td>
                                                        <span class="badge bg-success">${order.status}</span>
                                                    </td>
                                                    <td class="text-center text-nowrap">
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#orderInforModell${order.id}">Xem</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                </tbody>
                            </table>
                            </c:if>
                        </div>
                    </main>
                </div>
                <c:if test="${empty requestScope.check}">
                <c:if test="${totalPages != 0}">
                    <nav class="mt-3 mb-5">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="?page=${currentPage - 1}">
                                    Trang trước
                                </a>
                            </li>
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage == i}">
                                        <li class="page-item active">
                                            <a class="page-link">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${i}">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="?page=${currentPage + 1}">
                                    Trang sau
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
                </c:if>
            </div>
        </section>

        <c:forEach items="${requestScope.listorder}" var="order">
            <div class="modal fade" id="orderInforModell${order.id}" tabindex="-1" role="dialog" aria-labelledby="orderLabel_${order.id}" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Order Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <header class="card-header">
                            <strong class="d-inline-block me-4">Mã đơn hàng: ${order.id}</strong>
                            Ngày mua: ${order.createdat}<br>
                            Trạng thái: ${order.status}<br>
                        </header>
                        <div class="card-body pb-0">
                            <div class="row">
                                <div class="col-lg-8">
                                    <h6 class="text-muted">Địa chỉ người nhận</h6>
                                    <c:forEach items="${requestScope.listaccount}" var="account">
                                        <c:if test="${order.userid == account.getUserID()}">
                                            <p class="lh-lg">
                                                Họ và tên: ${order.fullname} <br>
                                                Số điện thoại: ${order.phone} <br> 
                                                Địa chỉ: ${order.address}<br>
                                                <c:forEach items="${requestScope.listship}" var="ship">
                                                    <c:if test="${ship.orderId == order.id}">
                                                    <h6 class="text-muted">Hình thức thanh toán: Thanh toán khi nhận hàng</h6>
                                                    <span class="text-success">
                                                        <i class="fab fa-lg fa-cc-visa"></i>
                                                        ${ship.shippingMethod}
                                                    </span>
                                                    <p class="lh-lg">
                                                        Phí vận chuyển: <fmt:formatNumber pattern="#,##0" value="${ship.shippingCost}"/>₫<br>
                                                    </p>
                                                    Tổng tiền phải thanh toán: <fmt:formatNumber pattern="#,##0" value="${order.amount}"/>₫
                                                </c:if>
                                            </c:forEach>
                                            </p>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <hr class="m-0">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Tên Sản Phẩm</th>
                                        <th>Sản Phẩm</th>
                                        <th>Giá</th>
                                        <th>Số Lượng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="orderdetail" items="${requestScope.listorderdetail}">
                                        <c:if test="${order.id == orderdetail.orderid}">
                                            <c:forEach items="${requestScope.listbook}" var="book">
                                                <c:if test="${orderdetail.bookid == book.getBookID()}">
                                                    <tr>
                                                        <td>${book.getTitle()}</td>
                                                        <td>
                                                            <img src="${book.getCoverImage()}" alt="${book.getTitle()}" class="img-fluid" style="max-width: 100px; max-height: 100px;">
                                                        </td>
                                                        <td><input type="text" class="form-control" name="unitPrice" value="<fmt:formatNumber pattern="#,##0" value="${orderdetail.unitPrice}"/>₫" readonly></td>
                                                        <td><input type="text" class="form-control" name="quantity" value="${orderdetail.quantity}" readonly></td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="orderSearchModell${order.id}" tabindex="-1" role="dialog" aria-labelledby="orderLabel_${order.id}" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Order Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <header class="card-header">
                            <strong class="d-inline-block me-4">Mã đơn hàng: ${order.id}</strong>
                            Ngày mua: ${order.createdat}<br>
                            Trạng thái: ${order.status}<br>
                        </header>
                        <div class="card-body pb-0">
                            <div class="row">
                                <div class="col-lg-8">
                                    <h6 class="text-muted">Địa chỉ người nhận</h6>
                                    <c:forEach items="${requestScope.listaccount}" var="account">
                                        <c:if test="${order.userid == account.getUserID()}">
                                            <p class="lh-lg">
                                                Họ và tên: ${order.fullname} <br>
                                                Số điện thoại: ${order.phone} <br> 
                                                Địa chỉ: ${order.address}<br>
                                                <c:forEach items="${requestScope.listship}" var="ship">
                                                    <c:if test="${ship.orderId == order.id}">
                                                    <h6 class="text-muted">Hình thức thanh toán: Thanh toán khi nhận hàng</h6>
                                                    <span class="text-success">
                                                        <i class="fab fa-lg fa-cc-visa"></i>
                                                        ${ship.shippingMethod}
                                                    </span>
                                                    <p class="lh-lg">
                                                        Phí vận chuyển: <fmt:formatNumber pattern="#,##0" value="${ship.shippingCost}"/>₫<br>
                                                    </p>
                                                    Tổng tiền phải thanh toán: <fmt:formatNumber pattern="#,##0" value="${order.amount}"/>₫
                                                </c:if>
                                            </c:forEach>
                                            </p>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <hr class="m-0">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Tên Sản Phẩm</th>
                                        <th>Sản Phẩm</th>
                                        <th>Giá</th>
                                        <th>Số Lượng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="orderdetail" items="${requestScope.listorderdetail}">
                                        <c:if test="${order.id == orderdetail.orderid}">
                                            <c:forEach items="${requestScope.listbook}" var="book">
                                                <c:if test="${orderdetail.bookid == book.getBookID()}">
                                                    <tr>
                                                        <td>${book.getTitle()}</td>
                                                        <td>
                                                            <img src="${book.getCoverImage()}" alt="${book.getTitle()}" class="img-fluid" style="max-width: 100px; max-height: 100px;">
                                                        </td>
                                                        <td><input type="text" class="form-control" name="unitPrice" value="<fmt:formatNumber pattern="#,##0" value="${orderdetail.unitPrice}"/>₫" readonly></td>
                                                        <td><input type="text" class="form-control" name="quantity" value="${orderdetail.quantity}" readonly></td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>

        <jsp:include page="_footerAdmin.jsp" />

        <!-- Bootstrap and DataTable JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
        <!-- Custom JS -->
        <script src="js/scripts.js"></script>
    </body>
</html>
