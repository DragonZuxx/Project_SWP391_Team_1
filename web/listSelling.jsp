<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Danh sách bán chạy nhất</title>
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/coupon.css">
        <style>
            .content-container {
                display: flex;
                flex-wrap: wrap;
            }
            .book-list-container {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: space-between;
                width: 100%;
            }
            .book-item {
                flex: 0 1 calc(33.333% - 20px);
                background: #fff;
                border: 1px solid #ddd;
                border-radius: 5px;
                overflow: hidden;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                display: flex;
                flex-direction: column;
                text-align: center;
            }
            .book-item img {
                width: 100%;
                height: auto;
            }
            .book-details {
                padding: 15px;
            }
            .book-details h5 {
                font-size: 16px;
                margin-bottom: 10px;
            }
            .book-details p {
                font-size: 14px;
                margin-bottom: 5px;
            }
            .btn-read-more {
                display: inline-block;
                margin-top: 10px;
                padding: 5px 10px;
                background: #007bff;
                color: #fff;
                border-radius: 3px;
                text-decoration: none;
            }
            .pagination-container {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="_header1.jsp"/>
        <jsp:include page="navbar.jsp"/>
        <section class="section-pagetop bg-light">
            <div class="container">
                <h3 class="title-page">Top sản phẩm bán chạy nhất</h3>
            </div> 
        </section>
        <section class="section-content padding-y">
            <div class="container">
                <div class="row item-grid">
                    <c:forEach var="book" items="${requestScope.products}">
                        <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                            <div class="card p-2">
                                <a href="${pageContext.request.contextPath}/detailbook?id=${book.getBookID()}">
                                    <c:choose>
                                        <c:when test="${empty book.getCoverImage()}">
                                            <img src="${pageContext.request.contextPath}/img/280px.png" alt="No image available" width="200" height="200" class="img-fluid">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${book.getCoverImage()}" alt="${book.getTitle()}" width="200" height="200" class="img-fluid">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                <div class="book-details">
                                    <h5>${book.getTitle()}</h5>
                                    <p>Nhà xuất bản: ${book.getPublisher()}</p>
                                    <p>Giá: 
                                        <c:choose>
                                            <c:when test="${empty requestScope.promotions}">
                                                <!-- If no promotion -->
                                                <span class="price mt-1 fw-bold">
                                                    <fmt:formatNumber pattern="#,##0" value="${book.getPrice()}"/>₫
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- If there is a promotion -->
                                                <span class="price mt-1 fw-bold">
                                                    <fmt:formatNumber pattern="#,##0" value="${book.getPrice() * (100 - requestScope.promotions.getDiscountPercentage()) / 100}"/>₫
                                                </span>
                                                <span class="ms-2 text-muted text-decoration-line-through">
                                                    <fmt:formatNumber pattern="#,##0" value="${book.getPrice()}"/>₫
                                                </span>
                                                <span class="ms-2 badge bg-info">
                                                    -<fmt:formatNumber pattern="#,##0" value="${requestScope.promotions.getDiscountPercentage()}"/>%
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                    <p>Số lượng đã bán: ${book.getSoldQuantity()}</p>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pagination-container">
                    <c:if test="${totalPages != 0}">
                        <nav class="mt-3 mb-5">
                            <ul class="pagination">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="?page=${currentPage - 1}">Trang trước</a>
                                </li>
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                    <a class="page-link" href="?page=${currentPage + 1}">Trang sau</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
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
        </section>
        <jsp:include page="_footer.jsp"/>
        <script src="<c:url value='/js/bootstrap.bundle.min.js'/>"></script>
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
        </script>
    </body>
</html>