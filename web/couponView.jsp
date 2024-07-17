<%-- 
    Document   : couponView
    Created on : Jul 8, 2024, 7:46:27 AM
    Author     : Aplal
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Trang Khuyến Mãi</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/coupon.css">
    </head>
    <body>
        <jsp:include page="_header1.jsp"/>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5">
            <h1 class="mb-4">Chương Trình Khuyến Mãi</h1>



            <!-- Mã giảm giá -->
            <div class="mt-5">
                <h2>Mã Giảm Giá</h2>
                <div class="row">
                    <c:forEach var="code" items="${requestScope.coupons}">
                        <div class="col-md-4 mb-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <p class="coupon-code font-weight-bold" id="coupon-${code.getCode()}">${code.getCode()}</p>
                                    <p class="coupon-description">${code.getDescription()}</p>
                                    <p><strong>Giảm:</strong> ${code.getDiscountPercentage()}%</p>
                                    <p class="coupon-expiry"><strong>Hạn sử dụng:</strong> <fmt:formatDate value="${code.getExpirationDate()}" pattern="dd/MM/yyyy" /></p>
                                    <button class="btn btn-primary copy-button" onclick="copyToClipboard('${code.getCode()}')">Sao chép mã</button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Sản phẩm liên quan -->
            <div class="related-products mt-5">
                <h2>Sản Phẩm Đề Xuất</h2>

                <div class="product-list">
                    <c:forEach var="product" items="${requestScope.bookrelated}">
                        <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                            <div class="card p-3 h-100">
                                <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="img-wrap text-center">
                                    <img width="200" height="200" class="img-fluid" src="${product.getCoverImage()}" alt="${product.getTitle()}">
                                </a>
                                <figcaption class="info-wrap mt-2">
                                    <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="title">${product.getTitle()}</a>
                                    <div>
                                        <c:choose>
                                            <c:when test="${empty requestScope.promotions}">
                                                <!-- If no promotion -->
                                                <span class="price mt-1 fw-bold">
                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- If there is a promotion -->
                                                <span class="price mt-1 fw-bold">
                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice() * (100 - requestScope.promotions.getDiscountPercentage()) / 100}"/>₫
                                                </span>
                                                <span class="ms-2 text-muted text-decoration-line-through">
                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                                </span>
                                                <span class="ms-2 badge bg-info">
                                                    -<fmt:formatNumber pattern="#,##0" value="${requestScope.promotions.getDiscountPercentage()}"/>%
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </figcaption>
                            </div>
                        </div> 
                    </c:forEach>
                </div>

            </div>
        </div>
        <jsp:include page="_footer.jsp" />

        <script>
            $(document).ready(function () {
                $('.product-list').slick({
                    infinite: true,
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    dots: true,
                    arrows: true,
                    prevArrow: '<button type="button" class="slick-prev">Trước</button>',
                    nextArrow: '<button type="button" class="slick-next">Tiếp</button>',
                    customPaging: function (slider, i) {
                        return '<button type="button" class="slick-page">' + (i + 1) + '</button>';
                    },
                    responsive: [
                        {
                            breakpoint: 1024,
                            settings: {
                                slidesToShow: 3,
                                slidesToScroll: 3
                            }
                        },
                        {
                            breakpoint: 600,
                            settings: {
                                slidesToShow: 2,
                                slidesToScroll: 2
                            }
                        },
                        {
                            breakpoint: 480,
                            settings: {
                                slidesToShow: 1,
                                slidesToScroll: 1
                            }
                        }
                    ]
                });
            });



            function copyToClipboard(code) {
                const tempInput = document.createElement('input');
                tempInput.value = code;
                document.body.appendChild(tempInput);
                tempInput.select();
                document.execCommand('copy');
                document.body.removeChild(tempInput);
                alert("Mã giảm giá đã được sao chép: " + code);
            }
        </script>
        
    </body>
</html>
