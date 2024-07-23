<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Quản lý người dùng</title>
        <script>
            window.onload = function () {
                setTimeout(function () {
                    const successAlerts = document.querySelectorAll('.alert-success');
                    const errorAlerts = document.querySelectorAll('.alert-danger');

                    successAlerts.forEach(function (alert) {
                        alert.classList.remove('show');
                        alert.classList.add('fade');
                        setTimeout(() => alert.remove(), 150); // Ensure the alert is removed after the fade effect
                    });

                    errorAlerts.forEach(function (alert) {
                        alert.classList.remove('show');
                        alert.classList.add('fade');
                        setTimeout(() => alert.remove(), 150); // Ensure the alert is removed after the fade effect
                    });
                }, 5000);
            };
        </script>
    </head> 
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.account.getRoleID() || sessionScope.account.getRoleID() == 3 || sessionScope.account.getRoleID() == 2}">
                <jsp:include page="admin401View.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="_headerAdmin.jsp"/>

                <section class="section-content">
                    <div class="container">
                        <c:if test="${not empty sessionScope.mess}">
                            <div class="alert alert-success mb-0 mt-4" role="alert">
                                ${sessionScope.mess}
                            </div>
                        </c:if>
                        <c:if test="${not empty sessionScope.succsess}">
                            <div class="alert alert-success mb-0 mt-4"" role="alert">
                                ${sessionScope.succsess}
                            </div>
                        </c:if>
                        <c:remove var="successMessage" scope="session"/>
                        <c:remove var="errorMessage" scope="session"/>

                        <c:if test="${sessionScope.account.getRoleID() == 1}">
                            <header class="section-heading py-4 d-flex justify-content-between align-items-center">
                                <h3 class="section-title">Quản lý người dùng</h3>
                                <form action="searchAccount" method="post" class="search">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Nhập từ khóa cần tìm ..." name="txtSearch" value="${requestScope.search}">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="submit">
                                                <i class="bi bi-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <a class="btn btn-primary" href="addAccount.jsp" role="button" style="height: fit-content;">Thêm người dùng</a>
                            </header>

                            <main class="table-responsive-xl mb-5">
                                <table class="table table-bordered table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Email</th>
                                            <th>Họ và tên</th>
                                            <th>Địa chỉ</th>
                                            <th>Số điện thoại</th>
                                            <th>Vai trò</th>
                                            <th>Ngày tạo</th>
                                            <th>Ngày cập nhật</th>
                                            <th>Trạng thái</th>
                                            <th colspan="2">Thao tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="account" items="${requestScope.data}">
                                            <tr>
                                                <td>${account.getUserID()}</td>
                                                <td>${account.getEmail()}</td>
                                                <td>${account.getFullName()}</td>
                                                <td>${account.getAddress()}</td>
                                                <td>${account.getPhone()}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${account.getRoleID() == 3}">Người dùng</c:when>
                                                        <c:when test="${account.getRoleID() == 2}">Nhân viên</c:when> 
                                                        <c:when test="${account.getRoleID() == 1}">Quản trị viên</c:when> 
                                                    </c:choose>
                                                </td>

                                                <td>${account.getCreatedAt()}</td>
                                                <td>${account.getUpdatedAt()}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${!account.isIsActive()}">Cấm</c:when>
                                                        <c:when test="${account.isIsActive()}">Hoạt động</c:when> 
                                                        <c:when test="${account.isIsActive() == null}">Hoạt động</c:when> 
                                                    </c:choose>
                                                </td>
                                                <th><a href="edit?id=${account.getUserID()}" class="btn btn-warning btn-sm">Sửa</a></th>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </main>
                        </c:if>
                        <c:if test="${requestScope.totalPages > 1}">
                            <nav class="mt-3 mb-5">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${requestScope.currentPage == 1 ? 'disabled' : ''}">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/userManager?page=${requestScope.currentPage - 1}">
                                            Trang trước
                                        </a>
                                    </li>

                                    <c:set var="maxDisplayPages" value="5"/>
                                    <c:set var="currentBlock" value="${(requestScope.currentPage - 1) / maxDisplayPages + 1}"/>
                                    <c:set var="startPage" value="${(currentBlock - 1) * maxDisplayPages + 1}"/>
                                    <c:set var="endPage" value="${startPage + maxDisplayPages - 1}"/>
                                    <c:if test="${endPage > requestScope.totalPages}">
                                        <c:set var="endPage" value="${requestScope.totalPages}"/>
                                    </c:if>

                                    <c:choose>
                                        <c:when test="${requestScope.totalPages <= 5}">
                                            <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                                                <li class="page-item ${requestScope.currentPage == i ? 'active' : ''}">
                                                    <a class="page-link"
                                                       href="${pageContext.request.contextPath}/userManager?page=${i}">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item ${requestScope.currentPage == 1 ? 'active' : ''}">
                                                <a class="page-link" href="${pageContext.request.contextPath}/userManager?page=1">1</a>
                                            </li>

                                            <c:if test="${startPage > 2}">
                                                <li class="page-item disabled"><a class="page-link">...</a></li>
                                                </c:if>

                                            <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                                <c:choose>
                                                    <c:when test="${i == 1}"></c:when>
                                                    <c:when test="${requestScope.currentPage == i}">
                                                        <li class="page-item active">
                                                            <a class="page-link">${i}</a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="page-item">
                                                            <a class="page-link"
                                                               href="${pageContext.request.contextPath}/userManager?page=${i}">
                                                                ${i}
                                                            </a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${endPage < requestScope.totalPages - 1}">
                                                <li class="page-item disabled"><a class="page-link">...</a></li>
                                                </c:if>

                                            <li class="page-item ${requestScope.currentPage == requestScope.totalPages ? 'active' : ''}">
                                                <a class="page-link" href="${pageContext.request.contextPath}/userManager?page=${requestScope.totalPages}">
                                                    ${requestScope.totalPages}
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>

                                    <li class="page-item ${requestScope.currentPage == requestScope.totalPages ? 'disabled' : ''}">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/userManager?page=${requestScope.currentPage + 1}">
                                            Trang sau
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                </section>

                <jsp:include page="_footerAdmin.jsp"/>
            </c:otherwise>
        </c:choose>


    </body>

</html>