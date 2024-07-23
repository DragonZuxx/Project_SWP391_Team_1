<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Trang chủ Admin</title>
    </head>

    <body>  

        <c:choose>
            <c:when test="${empty sessionScope.account.getRoleID() || sessionScope.account.getRoleID() == 3}">
                <jsp:include page="admin401View.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="_headerAdmin.jsp"/>
                <section class="section-content padding-y">
                    <div class="container">
                        <div class="card bg-light">
                            <div class="card-body p-5">
                                <h1 class="display-5 mb-5">Quản lý Shop Bán Sách</h1>
                                <div class="row">
                                    <c:if test="${sessionScope.account.getRoleID() == 1}">
                                        <div class="col-6 col-lg-3">
                                            <figure class="card">

                                                <div class="p-3">
                                                    <h4 class="title">${requestScope.totalUsers}</h4>
                                                    <span><a href="userManager" class="text-dark mr-3" style="text-decoration: none;">Người dùng</a></span>
                                                </div>

                                            </figure>
                                        </div>
                                    </c:if>  
                                    <div class="col-6 col-lg-3">
                                        <figure class="card">
                                            <div class="p-3">
                                                <h4 class="title">${requestScope.totalCategories}</h4>
                                                <span><a href="categoryManager" class="text-dark mr-3" style="text-decoration: none;">Thể loại sách</a></span>
                                            </div>
                                        </figure>
                                    </div>
                                    <div class="col-6 col-lg-3">
                                        <figure class="card">
                                            <div class="p-3">
                                                <h4 class="title">${requestScope.totalProducts}</h4>
                                                <span><a href="productManager" class="text-dark mr-3" style="text-decoration: none;">Sản phẩm</a></span>
                                            </div>
                                        </figure>
                                    </div>
                                    <div class="col-6 col-lg-3">
                                        <figure class="card">
                                            <div class="p-3">
                                                <h4 class="title">${requestScope.totalOrders}</h4>
                                                <span><a href="reviewManager" class="text-dark mr-3" style="text-decoration: none;">Đánh giá</a></span>
                                            </div>
                                        </figure>
                                    </div>
                                    <div class="col-6 col-lg-3">
                                        <figure class="card">
                                            <div class="p-3">
                                                <h4 class="title">${requestScope.totalOrders}</h4>
                                                <span><a href="listorder" class="text-dark mr-3" style="text-decoration: none;">Đơn hàng</a></span>
                                            </div>
                                        </figure>
                                    </div>
                                    <div class="col-6 col-lg-3">
                                        <figure class="card">
                                            <div class="p-3">
                                                <h4 class="title">${requestScope.totalOrders}</h4>
                                                <span><a href="authorManager" class="text-dark mr-3" style="text-decoration: none;">Tác giả</a></span>
                                            </div>
                                        </figure>
                                    </div>
                                    <c:if test="${sessionScope.account.getRoleID() == 1}">
                                        <div class="col-6 col-lg-3">
                                            <figure class="card">
                                                <div class="p-3">
                                                    <h4 class="title">${requestScope.totalOrders}</h4>
                                                    <span><a href="revenueBook" class="text-dark mr-3" style="text-decoration: none;">Doanh thu</a></span>
                                                </div>
                                            </figure>
                                        </div>
                                    </c:if>
                                    <div class="col-6 col-lg-3">
                                        <figure class="card">
                                            <div class="p-3">
                                                <h4 class="title">${requestScope.totalOrders}</h4>
                                                <span><a href="promotionManager" class="text-dark mr-3" style="text-decoration: none;">Chương trình khuyến mãi</a></span>
                                            </div>
                                        </figure>
                                    </div>
                                </div>

                                <div class="container">
                                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                        <span class="navbar-toggler-icon"></span>
                                    </button>
                                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                            <c:if test="${sessionScope.acc.getRoleID() == 1}">
                                                <li class="nav-item">
                                                    <a class="nav-link ${fn:startsWith(servletPath, 'userManager') ? 'active' : ''}" href="${pageContext.request.contextPath}/userManager">
                                                        <i class="bi bi-people"></i> Quản lý người dùng
                                                    </a>
                                                </li>
                                            </c:if>

                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, 'categoryManager') ? 'active' : ''}" href="${pageContext.request.contextPath}/categoryManager">
                                                    <i class="bi bi-tags"></i> Quản lý thể loại
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, '/productManager') ? 'active' : ''}" href="${pageContext.request.contextPath}/productManager">
                                                    <i class="bi bi-book"></i> Quản lý sản phẩm
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, '/reviewManager') ? 'active' : ''}" href="${pageContext.request.contextPath}/reviewManager">
                                                    <i class="bi bi-star"></i> Quản lý đánh giá
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, '/listorder') ? 'active' : ''}" href="${pageContext.request.contextPath}/listorder">
                                                    <i class="bi bi-inboxes"></i> Quản lý đơn hàng
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, '/authormanager') ? 'active' : ''}" href="${pageContext.request.contextPath}/authorManager">
                                                    <i class="bi bi-person-check"></i> Quản lý tác giả
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, '/admin/revenueBook') ? 'active' : ''}"
                                                   href="revenueBook">
                                                    <i class="bi bi-window"></i> Quản lý doanh thu
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${fn:startsWith(servletPath, '/promotionManager') ? 'active' : ''}" href="${pageContext.request.contextPath}/promotionManager">
                                                    <i class="bi-megaphone-fill"></i> Quản lý chương trình khuyến mãi
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- card.// -->
                    </div> <!-- container.// -->
                </section> <!-- section-content.// -->
                <jsp:include page="_footerAdmin.jsp"/>
            </c:otherwise>
        </c:choose>
    </body>

</html>
