<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Trang chủ</title>
        <!-- Thêm Google Fonts -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/home.css"/>
    </head>

    <body>
        <jsp:include page="_header1.jsp"/>
        <jsp:include page="navbar.jsp"/>

        <section class="section-content mb-2">
            <div class="banner-container">
                <button class="control-btn next-btn">&#10095;</button>
                <a class="text-body d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/banner.jpg" alt="Banner 1">
                </a>
                <a class="text-body d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/banner_1.jpg" alt="Banner 2">
                </a>
                <a class="text-body d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/banner_2.jpg" alt="Banner 3">
                </a>
                <button class="control-btn prev-btn">&#10094;</button>
            </div>
        </section>

        <div class="container">
            <header class="section-heading py-4 d-flex justify-content-between">
                <h3 class="section-title">Danh mục sản phẩm</h3>
                <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
            </header>
        </div> 
    </section>


    <div class="container"> 
        <div class="row item-grid mb-5"> 
            <c:forEach var="category" items="${requestScope.category}">
                <div class="col-lg-3 col-md-6">
                    <div class="card mb-4">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/category?id=${category.getCategoryID()}" class="stretched-link">
                                <div class="d-flex align-items-center">
                                    <span class="category-title ms-3">${category.getCategoryName()}</span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div> <!-- col.// -->
            </c:forEach>
        </div> 
        <div class="col-lg-12">
            <div class="iq-card-transparent iq-card-block iq-card-stretch iq-card-height rounded">
                <header class="section-heading py-4">
                    <h3 class="section-title">Sách mới nhất</h3>
                </header>
                <div class="newrealease-contens">
                    <ul id="newrealease-slider" class="list-inline p-0 m-0 d-flex align-items-center">
                        <c:forEach var="product" items="${arraybook}">
                            <li class="item">
                                <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}">
                                    <img src="${product.getCoverImage()}" class="img-fluid w-100 rounded" alt="${product.getTitle()}">
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <section class="section-content">
            <header class="section-heading py-4 d-flex justify-content-between">
                <h3 class="section-title mb-0">Gợi ý cho bạn</h3>
                <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
            </header> <!-- section-heading.// -->
            <div class="row item-grid">
                <c:forEach var="product" items="${requestScope.listbook}">
                    <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                        <div class="card p-3 h-100">
                            <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="img-wrap text-center">
                                <img width="200" height="200" class="img-fluid" src="${product.getCoverImage()}" alt="${product.getTitle()}">
                            </a>
                            <figcaption class="info-wrap mt-2">
                                <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="title">${product.getTitle()}</a>
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
                </c:forEach>

            </div>
        </section> 
    </div> 
    <jsp:include page="_footer.jsp"/>
    <script src="js/home.js"></script>
</body>


</html>
