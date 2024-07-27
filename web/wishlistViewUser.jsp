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
            .card-img-top {
                width: 80%; /* Đặt chiều rộng bằng 100% */
                height: 100%; /* Đặt chiều cao cố định */
                object-fit: cover; /* Đảm bảo ảnh không bị méo */
            }
            .card {
                height: 100%; /* Đảm bảo card có chiều cao đồng nhất */
            }
        </style>
    </head>
    <body>
        <jsp:include page="_header.jsp"/>

        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page">Sản phẩm yêu thích</h2>
            </div> <!-- container.// -->
        </section> <!-- section-pagetop.// -->

        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <jsp:include page="_navUser.jsp">
                        <jsp:param name="active" value="WISHLIST"/>
                    </jsp:include>

                    <main class="col-md-9">
                        <article class="card">
                            <div class="card-body">
                                <c:choose>
                                    <c:when test="${empty sessionScope.account}">
                                        <p>
                                            Vui lòng <a href="${pageContext.request.contextPath}/login.jsp">đăng nhập</a> để sử dụng chức năng sản phẩm yêu thích.
                                        </p>
                                    </c:when>
                                    <c:when test="${empty wishlistProducts}">
                                        <p>
                                            Người dùng không có sản phẩm yêu thích.
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="row g-3">
                                            <c:forEach var="product" items="${wishlistProducts}">
                                                <div class="col-lg-4 col-md-6">
                                                    <div class="card mb-3">
                                                        <img class="card-img-top" 
                                                             src="${product.coverImage != null ? product.coverImage : pageContext.request.contextPath + '/image/default.png'}" 
                                                             alt="${product.title}">
                                                        <div class="card-body">
                                                            <h5 class="card-title">
                                                                <a href="${pageContext.request.contextPath}/detailbook?id=${product.bookID}" class="text-decoration-none">${product.title}</a>
                                                            </h5>
                                                            <h6 class="card-subtitle mb-2 text-muted">
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
                                                            </h6>
                                                            <form action="${pageContext.request.contextPath}/wishlistuser" method="post">
                                                                <input type="hidden" name="action" value="remove">
                                                                <input type="hidden" name="bookId" value="${product.bookID}">
                                                                <button type="submit" class="btn btn-danger btn-sm" title="Xóa khỏi danh sách yêu thích" onclick="return confirm('Bạn có muốn xóa sản phẩm yêu thích này?')">
                                                                    <i class="bi bi-trash"></i> Xóa
                                                                </button>
                                                            </form>
                                                        </div> <!-- card-body.// -->
                                                    </div> <!-- card.// -->
                                                </div> <!-- col.// -->
                                            </c:forEach>
                                        </div> <!-- row .// -->
                                    </c:otherwise>
                                </c:choose>
                            </div> <!-- card-body.// -->
                        </article>
                    </main> <!-- col.// -->

                </div> <!-- row.// -->
            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <jsp:include page="_footer.jsp"/>
    </body>

</html>
