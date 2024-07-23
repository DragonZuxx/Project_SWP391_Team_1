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
                            <div class="card p-3 h-100">
                                <a href="${pageContext.request.contextPath}/detailbook?id=${book.getBookID()}">
                                    <c:choose>
                                        <c:when test="${empty book.getCoverImage()}">
                                            <img src="${pageContext.request.contextPath}/img/280px.png" alt="No image available" style="max-width: 100%; height: auto">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${book.getCoverImage()}" alt="${book.getTitle()}" style="max-width: 100%; height: auto">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                <div class="book-details">
                                    <h5>${book.getTitle()}</h5>
                                    <p>Nhà xuất bản: ${book.getPublisher()}</p>
                                    <p>Giá: <fmt:formatNumber value="${book.getPrice()}" type="currency" currencySymbol="VND"/></p>
                                    <p>Số lượng đã bán: ${book.getSoldQuantity()}</p>
                                    <a href="${pageContext.request.contextPath}/detailbook?id=${book.getBookID()}" class="btn-read-more">Mua ngay</a>
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
            </div>
        </section>
        
        <section class="section-content mb-5">
            <div class="container">
                <h3 class="pb-2">Sản phẩm liên quan</h3>
                <div class="related-products-container position-relative">
                    <button class="scroll-button left" onclick="scrollLeft()">&#10094;</button>
                    <div class="related-products-wrapper d-flex overflow-hidden">
                        <div class="row item-grid">

                            <c:forEach var="relatedProduct" items="${requestScope.bookgetbyid}">
                                <c:choose>
                                    <c:when test="${requestScope.product.getBookID() != relatedProduct.getBookID()}">
                                        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 related-product-item">
                                            <div class="card p-3 mb-4">
                                                <a href="${pageContext.request.contextPath}/detailbook?id=${relatedProduct.getBookID()}"
                                                   class="img-wrap text-center">
                                                    <c:choose>
                                                        <c:when test="${empty relatedProduct.getCoverImage()}">
                                                            <img width="200" height="200" class="img-fluid"
                                                                 src="${pageContext.request.contextPath}/img/280px.png"
                                                                 alt="280px.png">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img width="200" height="200" class="img-fluid"
                                                                 src="${relatedProduct.getCoverImage()}"
                                                                 alt="${relatedProduct.getTitle()}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                                <figcaption class="info-wrap mt-2">
                                                    <a href="${pageContext.request.contextPath}/detailbook?id=${relatedProduct.getBookID()}"
                                                       class="title">${relatedProduct.getTitle()}</a>
                                                    <div>
                                                        <c:choose>
                                                            <c:when test="${empty requestScope.promotion}">
                                                                <!-- If no promotion -->
                                                                <span class="price mt-1 fw-bold">
                                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                                                </span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <!-- If there is a promotion -->
                                                                <span class="price mt-1 fw-bold">
                                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice() * (100 - requestScope.promotion.getDiscountPercentage()) / 100}"/>₫
                                                                </span>
                                                                <span class="ms-2 text-muted text-decoration-line-through">
                                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                                                </span>
                                                                <span class="ms-2 badge bg-info">
                                                                    -<fmt:formatNumber pattern="#,##0" value="${requestScope.promotion.getDiscountPercentage()}"/>%
                                                                </span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </figcaption>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <button class="scroll-button right" onclick="scrollRight()">&#10095;</button>
                </div>
            </div>
        </section>
        <jsp:include page="_footerAdmin.jsp"/>
        <script src="<c:url value='/js/bootstrap.bundle.min.js'/>"></script>
    </body>
</html>
