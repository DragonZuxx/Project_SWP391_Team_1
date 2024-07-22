<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Đơn hàng</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <jsp:include page="_header1.jsp"/>
        <c:set var="account" value="${sessionScope.account}" />

        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page">Đơn hàng</h2>
            </div>
        </section>

        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <jsp:include page="_navUser.jsp">
                        <jsp:param name="active" value="ORDER"/>
                    </jsp:include>

                    <main class="col-md-9">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="Pending-tab" data-toggle="tab" href="#Pending" role="tab" aria-controls="Pending" aria-selected="true">Đang Xác Nhận</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="Ship-tab" data-toggle="tab" href="#Ship" role="tab" aria-controls="Ship" aria-selected="false">Đang giao hàng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="Successful-tab" data-toggle="tab" href="#Successful" role="tab" aria-controls="Successful" aria-selected="false">Hoàn thành</a>
                            </li>
                        </ul>

                        <div class="tab-content" id="myTabContent">
                            <%-- hiển thị danh sách đơn hàng đang chờ xác thực  --%>

                           <h1 style="color: #0d6efd;margin-top: 20px ;font-size: 24px; font-family: Arial, sans-serif; text-align: center;">${requestScope.mess}</h1>
                            <div class="tab-pane fade show active order-section" id="Pending" role="tabpanel" aria-labelledby="Pending-tab">
                                <div class="table-responsive-xxl mt-4">
                                    <table class="table table-bordered table-striped table-hover align-middle">
                                        <thead>
                                            <tr>
                                                <th scope="col" style="min-width: 125px;">Mã đơn hàng</th>
                                                <th scope="col" style="min-width: 100px;">Ngày mua</th>
                                                <th scope="col" style="min-width: 300px;">Người mua</th>
                                                <th scope="col" style="min-width: 100px;">Tổng tiền</th>
                                                <th scope="col" style="min-width: 175px;">Trạng thái đơn hàng</th>
                                                <th scope="col">Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${requestScope.listorderRequest}">
                                                <tr>
                                                    <th scope="row">${order.id}</th>
                                                    <td>${order.createdat}</td>
                                                    <td>${order.userid}</td>
                                                    <td>₫${order.amount}</td>
                                                    <td>
                                                        <span class="badge bg-danger">Chờ xác nhận</span>
                                                    </td>
                                                    <td class="text-center text-nowrap">
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#orderInforModell${order.id}">Xem</button>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cancelOrderModal${order.id}">
                                                            Hủy đơn hàng
                                                        </button>



                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <%-- Hiển thị danh sách order đang giao hàng --%>
                            <div class="tab-pane fade order-section" id="Ship" role="tabpanel" aria-labelledby="Ship-tab">
                                <div class="table-responsive-xxl mt-4">
                                    <table class="table table-bordered table-striped table-hover align-middle">
                                        <thead>
                                            <tr>
                                                <th scope="col" style="min-width: 125px;">Mã đơn hàng</th>
                                                <th scope="col" style="min-width: 100px;">Ngày mua</th>
                                                <th scope="col" style="min-width: 300px;">Người mua</th>
                                                <th scope="col" style="min-width: 100px;">Tổng tiền</th>
                                                <th scope="col" style="min-width: 175px;">Trạng thái đơn hàng</th>
                                                <th scope="col">Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${requestScope.listorderShipping}">
                                                <tr>
                                                    <th scope="row">${order.id}</th>
                                                    <td>${order.createdat}</td>
                                                    <td>${order.userid}</td>
                                                    <td>₫${order.amount}</td>
                                                    <td>
                                                        <span class="badge bg-warning text-dark">Đang giao hàng</span>
                                                    </td>
                                                    <td class="text-center text-nowrap">
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#orderInforModell${order.id}">Xem</button>
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#">Đã nhận được hàng</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <%-- Hiển thị danh sách giao hàng thành công --%>
                            <div class="tab-pane fade order-section" id="Successful" role="tabpanel" aria-labelledby="Successful-tab">
                                <div class="table-responsive-xxl mt-4">
                                    <table class="table table-bordered table-striped table-hover align-middle">
                                        <thead>
                                            <tr>
                                                <th scope="col" style="min-width: 125px;">Mã đơn hàng</th>
                                                <th scope="col" style="min-width: 100px;">Ngày mua</th>
                                                <th scope="col" style="min-width: 300px;">Người mua</th>
                                                <th scope="col" style="min-width: 100px;">Tổng tiền</th>
                                                <th scope="col" style="min-width: 175px;">Trạng thái đơn hàng</th>
                                                <th scope="col">Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${requestScope.listorder}">
                                                <tr>
                                                    <th scope="row">${order.id}</th>
                                                    <td>${order.createdat}</td>
                                                    <td>${order.userid}</td>
                                                    <td>₫${order.amount}</td>
                                                    <td>
                                                        <span class="badge bg-success">Giao hàng thành công</span>
                                                    </td>
                                                    <td class="text-center text-nowrap">
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#orderInforModell${order.id}">Xem</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <c:if test="${totalPages != 0}">
                                    <nav class="mt-3 mb-5">
                                        <ul class="pagination justify-content-center">
                                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                                <a class="page-link" href="?page=${currentPage - 1}">Trang trước</a>
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
                                                            <a class="page-link" href="?page=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                                <a class="page-link" href="?page=${currentPage + 1}">Trang sau</a>
                                            </li>
                                            </
                                        </ul>
                                    </nav>
                                </c:if>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </section>
        <%-- Hiển thị chi tiết đơn hàng đang chờ xác thực --%>
        <c:forEach items="${requestScope.listOrderAll}" var="order">
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
                                    <h6 class="text-muted" style="color: black">Địa chỉ người nhận</h6>
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
                                                        Phí vận chuyển:  ₫${ship.shippingCost}
                                                        <br>
                                                    </p>
                                                    Tổng tiền phải thanh toán:₫${order.amount}
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
                                                        <td><input type="text" class="form-control" name="unitPrice" value="${orderdetail.unitPrice}" readonly></td>
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

            <!-- Cancel Order Modal -->
            <div class="modal fade" id="cancelOrderModal${order.id}" tabindex="-1" role="dialog" aria-labelledby="cancelOrderModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="cancelOrderModalLabel">Hủy Đơn Hàng #${order.id}</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="cancelOrderForm${order.id}" action="orderUser" method="post">
                                <div class="form-group">
                                    <label for="orderId${order.id}">Mã đơn hàng:</label>
                                    <input type="text" class="form-control" id="orderId${order.id}" name="orderid" value="${order.id}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="cancelReasonSelect${order.id}">Lí do hủy đơn hàng:</label>
                                    <select class="form-control" id="cancelReasonSelect${order.id}" name="cancelReasonSelect">
                                        <option value="Thay đổi địa điểm giao hàng">Thay đổi địa chỉ giao hàng</option>
                                        <option value="Không mua nữa">Không muốn mua nữa</option>
                                        <option value="Sản phẩm không phù hợp">Sản phẩm không phù hợp</option>
                                        <option value="Tôi đặt nhầm">Tôi đặt nhầm</option>
                                    </select>
                                </div>
                                <div class="form-group" id="otherReasonInput${order.id}" style="display: none;">
                                    <label for="otherReason${order.id}">Lý do khác:</label>
                                    <input type="text" class="form-control" id="otherReason${order.id}" name="otherReason" rows="3">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary">Xác nhận Hủy</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>






        </c:forEach>

        <jsp:include page="_footer.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- DataTable JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

    </body>
</html>
