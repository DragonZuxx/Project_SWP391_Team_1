<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Giỏ hàng</title>
        <link rel="stylesheet" href="css/carrt.css">
    </head>
    <body>
        <jsp:include page="_header.jsp"/>
        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page" style="color: #000">Giỏ hàng</h2>
            </div>
        </section>
        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <c:if test="${not empty sessionScope.successDeleteBookInCart}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            ${sessionScope.successDeleteBookInCart}
                        </div>
                    </c:if>

                    <c:if test="${not empty sessionScope.errorDeleteBookInCart}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${sessionScope.errorDeleteBookInCart}
                        </div>
                    </c:if>
                    <c:remove var="successDeleteBookInCart" scope="session" />
                    <c:remove var="errorDeleteBookInCart" scope="session" />
                    <c:choose>
                        <c:when test="${empty sessionScope.account}">
                            <p>
                                Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng chức năng giỏ hàng.
                            </p>
                        </c:when>
                        <c:otherwise>
                            <main class="col-lg-9 mb-lg-0 mb-3">
                                <div class="card">
                                    <div class="table-responsive">
                                        <form action="${pageContext.request.contextPath}/cartItem" method="post">
                                            <table class="table table-borderless table-shopping-cart">
                                                <thead class="text-muted">
                                                    <tr class="small text-uppercase">
                                                        <th scope="col">Chọn</th>
                                                        <th scope="col">Tên sản phẩm</th>
                                                        <th scope="col">Sản phẩm</th>
                                                        <th scope="col" width="120">Số lượng</th>
                                                        <th scope="col" width="150">Giá</th>
                                                        <th scope="col" class="text-end" width="130">Thao tác</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${empty cartItems}">
                                                        <tr>
                                                            <td colspan="6" class="text-center">Giỏ hàng của bạn trống.</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:forEach var="item" items="${cartItems}">
                                                        <c:forEach var="book" items="${books}">
                                                            <c:if test="${book.bookID == item.bookID}">
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" class="select-book" name="selectedBooks" value="${book.bookID}"
                                                                               data-price="${empty requestScope.promotion ? book.price : book.price * (100 - requestScope.promotion.discountPercentage) / 100}"
                                                                               data-quantity="${item.quantity}">
                                                                    </td>
                                                                    <td>
                                                                        <a href="${pageContext.request.contextPath}/detailbook?id=${book.bookID}">${book.title}</a>
                                                                    </td>
                                                                    <td>
                                                                        <figure class="itemside align-items-center">
                                                                            <div class="aside">
                                                                                <img src="${book.coverImage}" class="img-sm">
                                                                            </div>
                                                                        </figure>
                                                                    </td>
                                                                    <td>
                                                                        <input type="number" class="form-control quantity-input" value="${item.quantity}" min="1" max="${book.stock}">
                                                                    </td>
                                                                    <td>
                                                                        <div class="price-wrap">
                                                                            <c:choose>
                                                                                <c:when test="${empty requestScope.promotion}">
                                                                                    <!-- If no promotion -->
                                                                                    <span class="price mt-1 fw-bold">
                                                                                        <fmt:formatNumber pattern="#,##0" value="${book.price}"/>₫
                                                                                    </span>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <!-- If there is a promotion -->
                                                                                    <span class="price mt-1 fw-bold">
                                                                                        <fmt:formatNumber pattern="#,##0" value="${book.price * (100 - requestScope.promotion.discountPercentage) / 100}"/>₫
                                                                                    </span>
                                                                                    <span class="ms-2 text-muted text-decoration-line-through">
                                                                                        <fmt:formatNumber pattern="#,##0" value="${book.price}"/>₫
                                                                                    </span>
                                                                                    <span class="ms-2 badge bg-info">
                                                                                        -<fmt:formatNumber pattern="#,##0" value="${requestScope.promotion.discountPercentage}"/>%
                                                                                    </span>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </td>
                                                                    <td class="text-end">
                                                                        <form action="${pageContext.request.contextPath}/deleteOutCart" method="post" style="display:inline;">
                                                                            <input type="hidden" name="cartID" value="${item.cartID}">
                                                                            <input type="hidden" name="bookID" value="${book.bookID}">
                                                                            <button type="submit" class="btn btn-light btn-round">Xóa</button>
                                                                        </form>
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="card-body border-top">
                                                <button type="submit" class="btn btn-primary float-end" id="checkoutBtn">Đặt hàng</button>
                                                <a href="${pageContext.request.contextPath}/home" class="btn btn-light">Tiếp tục mua sắm</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </main>
                            <aside class="col-lg-3">
                                <div class="card">
                                    <div class="card-body">
                                        <dl class="row mb-0">
                                            <dt class="col-xxl-6 col-lg-12 col-6">Tổng tiền tạm tính:</dt>
                                            <dd class="col-xxl-6 col-lg-12 col-6 text-end mb-3">
                                                <span id="temp-price">
                                                    <fmt:formatNumber value="${tempPrice}" type="currency"/>
                                                </span>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </aside>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const checkboxes = document.querySelectorAll('.select-book');
            const quantityInputs = document.querySelectorAll('.quantity-input');
            const tempPriceElement = document.getElementById('temp-price');
            const deliveryMethodRadios = document.querySelectorAll('.delivery-method');
            const deliveryPriceElement = document.getElementById('delivery-price');
            let deliveryPrice = 0;

            checkboxes.forEach(checkbox => {
                checkbox.addEventListener('change', updateTotalSelectedPrice);
            });
            quantityInputs.forEach(input => {
                input.addEventListener('input', updateTotalSelectedPrice);
            });
            deliveryMethodRadios.forEach(radio => {
                radio.addEventListener('change', function () {
                    deliveryPrice = parseInt(this.value, 10);
                    updateTotalSelectedPrice();
                });
            });

            function updateTotalSelectedPrice() {
                let totalSelectedPrice = 0;
                checkboxes.forEach(checkbox => {
                    if (checkbox.checked) {
                        const price = parseFloat(checkbox.dataset.price);
                        const quantity = parseInt(checkbox.closest('tr').querySelector('.quantity-input').value, 10);
                        totalSelectedPrice += price * quantity;
                    }
                });
                const totalPrice = totalSelectedPrice + deliveryPrice;
                tempPriceElement.textContent = totalSelectedPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                deliveryPriceElement.textContent = deliveryPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            }
        });
    </script>
</html>
