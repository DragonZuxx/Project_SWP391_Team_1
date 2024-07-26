<%-- 
    Document   : checkout
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
        <title>Đặt hàng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">
    </head>
    <body>
        <jsp:include page="_header.jsp"/>
        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page" style="color: #000">Đặt hàng</h2>
            </div>
        </section>
        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <main class="col-lg-9 mb-lg-0 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/orderSucessfull" method="get">
                                    <h4>Thông tin giao hàng</h4>
                                    <div class="mb-3">
                                        <label for="fullName" class="form-label">Họ và tên</label>
                                        <input type="text" class="form-control" id="fullName" name="fullName"  value="${sessionScope.account.getFullName()}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="address" class="form-label">Địa chỉ</label>
                                        <input type="text" class="form-control" id="address" name="address" value="${sessionScope.account.getAddress()}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="phone" class="form-label">Số điện thoại</label>
                                        <input type="number" class="form-control" id="phone" name="phone"  value="${sessionScope.account.getPhone()}" required>
                                    </div>
                                    <h4>Thông tin đơn hàng</h4>
                                    <div class="table-responsive">
                                        <table class="table table-borderless table-shopping-cart">
                                            <thead class="text-muted">
                                                <tr class="small text-uppercase">
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col" width="120">Số lượng</th>
                                                    <th scope="col" width="150">Giá</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="book" items="${requestScope.selectedBooks}">
                                                    <c:forEach var="cart" items="${requestScope.cartItems}">
                                                        <c:if test="${book.getBookID() == cart.getBookID()}">
                                                            <tr>
                                                                <td>
                                                                    <a href="${pageContext.request.contextPath}/detailbook?id=${book.bookID}">
                                                                        ${book.title}
                                                                    </a>
                                                                    <input type="hidden" name="bookID" value="${book.bookID}" />
                                                                </td>
                                                                <td>
                                                                    ${cart.quantity}
                                                                    <input type="hidden" name="quantity" value="${cart.quantity}" />
                                                                </td>
                                                                <td>
                                                                    <fmt:formatNumber pattern="#,##0" value="${empty requestScope.promotion ? book.price : book.price * (100 - requestScope.promotion.discountPercentage) / 100}"/>₫
                                                                    <input type="hidden" name="price" value="${empty requestScope.promotion ? book.price : book.price * (100 - requestScope.promotion.discountPercentage) / 100}" />
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="card-body">
                                        <p class="card-title">Hình thức giao hàng</p>
                                        <div class="form-check mb-2">
                                            <input class="form-check-input delivery-method" type="radio" name="delivery-method" id="delivery-method-1" value="0" checked="">
                                            <label class="form-check-label" for="delivery-method-1">Giao tiêu chuẩn</label>
                                        </div>
                                        <div class="form-check mb-2">
                                            <input class="form-check-input delivery-method" type="radio" name="delivery-method" id="delivery-method-2" value="30000">
                                            <label class="form-check-label" for="delivery-method-2">Giao nhanh</label>
                                        </div>
                                    </div>

                                    <!-- Hidden fields -->
                                    <div id="hidden-fields-container">
                                        <input type="hidden" id="delivery-method-name" name="delivery-method-name" value="">
                                        <input type="hidden" id="delivery-time" name="delivery-time" value="">
                                    </div>

                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <p class="card-title">Hình thức thanh toán</p>
                                            <div class="form-check mb-2">
                                                <input class="form-check-input payment-method" type="radio" name="payment-method" id="payment-method-1" value="Thanh toán khi nhận hàng (COD)" checked="">
                                                <label class="form-check-label" for="payment-method-1">Thanh toán khi nhận hàng (COD)</label>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" id="totalPriceInput" name="totalPrice">
                                    <div class="card-body border-top">
                                        <button type="submit" class="btn btn-primary float-end">Xác nhận đặt hàng</button>
                                        <a href="${pageContext.request.contextPath}/cart" class="btn btn-light">Quay lại giỏ hàng</a>
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
                                            <fmt:formatNumber pattern="#,##0" value="${tempPrice}" type="currency" />
                                        </span>
                                    </dd>
                                    <dt class="col-xxl-6 col-lg-12 col-6">Phí vận chuyển:</dt>
                                    <dd class="col-xxl-6 col-lg-12 col-6 text-end mb-3">
                                        <span id="delivery-price">
                                            <fmt:formatNumber value="${deliveryPrice}" type="currency"/>
                                        </span>
                                    </dd>
                                    <dt class="col-xxl-6 col-lg-12 col-6">Thành tiền:</dt>
                                    <dd class="col-xxl-6 col-lg-12 col-6 text-end mb-3">
                                        <strong>
                                            <span id="total-price">
                                                <fmt:formatNumber value="${totalPrice}" type="currency"/>
                                            </span>
                                        </strong>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                    </aside>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const deliveryMethodInputs = document.querySelectorAll(".delivery-method");
            const tempPriceElement = document.getElementById("temp-price");
            const deliveryPriceElement = document.getElementById("delivery-price");
            const totalPriceElement = document.getElementById("total-price");
            const deliveryMethodNameField = document.getElementById('delivery-method-name');
            const deliveryTimeField = document.getElementById('delivery-time');

            let discount = 0;

            function calculateTotalPrice() {
                let tempPrice = 0;
                const cartItems = document.querySelectorAll("tbody tr");
                cartItems.forEach(item => {
                    const quantity = parseInt(item.querySelector("td:nth-child(2)").textContent, 10);
                    const priceString = item.querySelector("td:nth-child(3)").textContent.replace(/[^\d]/g, '');
                    const price = parseFloat(priceString);
                    tempPrice += quantity * price;
                });

                let deliveryPrice = 0;
                deliveryMethodInputs.forEach(input => {
                    if (input.checked) {
                        deliveryPrice = parseFloat(input.value);
                        deliveryMethodNameField.value = input.nextElementSibling.textContent.trim();

                        // Calculate estimated delivery date
                        let daysToAdd = 0;
                        if (input.id === 'delivery-method-1') {
                            daysToAdd = 5;
                        } else if (input.id === 'delivery-method-2') {
                            daysToAdd = 3;
                        }
                        const currentDate = new Date();
                        const estimatedDeliveryDate = new Date(currentDate.setDate(currentDate.getDate() + daysToAdd));
                        const formattedDate = estimatedDeliveryDate.toISOString().split('T')[0];
                        deliveryTimeField.value = formattedDate;
                    }
                });

                const discountedPrice = tempPrice - (tempPrice * discount);
                const totalPrice = discountedPrice + deliveryPrice;

                tempPriceElement.textContent = discountedPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                deliveryPriceElement.textContent = deliveryPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                totalPriceElement.textContent = totalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                document.getElementById("totalPriceInput").value = totalPrice;
            }

            deliveryMethodInputs.forEach(input => {
                input.addEventListener("change", calculateTotalPrice);
            });


            calculateTotalPrice(); // Initial calculation
        });

    </script>
</html>
