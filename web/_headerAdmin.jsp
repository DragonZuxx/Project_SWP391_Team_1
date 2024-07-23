<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <c:if test="${sessionScope.account.getRoleID() == 1}">
                    <div class="col py-3">
                        <a class="text-body" href="${pageContext.request.contextPath}/home">
                            <h3><span class="badge bg-primary">Admin</span> Book Haven Shop</h3>
                        </a>
                    </div>
                </c:if>

                <c:if test="${sessionScope.account.getRoleID() == 2}">
                    <div class="col py-3">
                        <a class="text-body" href="${pageContext.request.contextPath}/home">
                            <h3><span class="badge bg-primary">Nhân Viên</span> Book Haven Shop</h3>
                        </a>
                    </div>
                </c:if>
                <div class="col-sm-1">
                    <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                        <li>
                            <a href="${pageContext.request.contextPath}/home" class="nav-link text-body" target="_blank">
                                <i class="bi bi-window d-block text-center fs-3"></i>
                                Client
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</header>

<nav class="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${sessionScope.account.getRoleID() == 1}">
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
                <c:if test="${sessionScope.account.getRoleID() == 1}">
                <li class="nav-item">
                    <a class="nav-link ${fn:startsWith(servletPath, '/admin/revenueBook') ? 'active' : ''}"
                       href="revenueBook">
                        <i class="bi bi-window"></i> Quản lý doanh thu
                    </a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
