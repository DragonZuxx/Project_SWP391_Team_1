<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="_meta.jsp"/>
    <title>Sản phẩm yêu thích</title>
    <style>
        .favorites-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
        }
        .favorite-item {
            flex: 0 1 calc(33.333% - 20px);
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            text-align: center;
            margin-bottom: 20px;
        }
        .favorite-item img {
            width: 100%;
            height: auto;
        }
        .favorite-details {
            padding: 15px;
        }
        .favorite-details h5 {
            font-size: 16px;
            margin-bottom: 10px;
        }
        .favorite-details p {
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
        .item-grid .col-xl-3, .item-grid .col-lg-4, .item-grid .col-md-6 {
            display: flex;
        }

    </style>
</head>
<body>
    <jsp:include page="_header1.jsp"/>
    <jsp:include page="navbar.jsp"/>
    <section class="section-pagetop bg-light">
        <div class="container">
            <h3 class="title-page">Top 10 Sản phẩm được yêu thích nhất</h3>
        </div> 
    </section>

    <section class="section-content padding-y">
        <div class="container">
            <div class="row item-grid">
                <c:forEach var="wishlistItem" items="${requestScope.wishlistItems}">
                    <c:forEach var="wishlistBooks" items="${requestScope.wishlistBooks}">
                        <c:if test="${wishlistBooks.getBookID() == wishlistItem.getBookID()}">
                            <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                                <div class="card p-3 h-100">
                                    <c:choose>
                                        <c:when test="${empty wishlistBooks.getCoverImage()}">
                                            <a href="detailbook?id=${wishlistBooks.getBookID()}">
                                                <img src="${pageContext.request.contextPath}" alt="No image available" style="max-width: 100%; height: auto">
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="detailbook?id=${wishlistBooks.getBookID()}">
                                                <img src="${wishlistBooks.getCoverImage()}" alt="${wishlistBooks.getTitle()}" style="max-width: 100%; height: auto">
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="favorite-details">
                                        <h5>${wishlistBooks.getTitle()}</h5>
                                        <p>Đã bán: ${wishlistBooks.getSoldQuantity()}</p>
                                        <p>Số lượng yêu thích: ${wishlistItem.getWishlistID()}</p>
                                        <a href="detailbook?id=${wishlistBooks.getBookID()}" class="btn-read-more">Mua ngay</a>
                                    </div>  
                                </div>
                            </div> <!-- col.// -->
                        </c:if>
                    </c:forEach>
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

    <jsp:include page="_footer.jsp"/>
</body>
</html>
