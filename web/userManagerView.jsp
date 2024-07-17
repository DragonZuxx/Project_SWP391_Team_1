<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Quản lý người dùng</title>
    </head> 
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.account.getRoleID() || sessionScope.account.getRoleID() == 3}">
                <jsp:include page="admin401View.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="_headerAdmin.jsp"/>

                <section class="section-content">
                    <div class="container">
                        <c:if test="${not empty sessionScope.successMessage}">
                            <div class="alert alert-success mb-0 mt-4" role="alert">
                                ${sessionScope.successMessage}
                            </div>
                        </c:if>
                        <c:if test="${not empty sessionScope.errorMessage}">
                            <div class="alert alert-danger mb-0 mt-4" role="alert">
                                ${sessionScope.errorMessage}
                            </div>
                        </c:if>
                        <c:remove var="successMessage" scope="session"/>
                        <c:remove var="errorMessage" scope="session"/>

                        <c:if test="${sessionScope.account.getRoleID() == 1}">
                            <header class="section-heading py-4 d-flex justify-content-between align-items-center">
                                <h3 class="section-title">Quản lý người dùng</h3>
                                <form action="searchAccount" method="post" class="search">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Nhập từ khóa cần tìm ..." name="txtSearch" value="${requestScope.query}">
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
                        <c:if test="${totalPages != 0}">
                            <nav class="mt-3 mb-5">
                                <ul class="pagination justify-content-center">
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
                </section>

                <jsp:include page="_footerAdmin.jsp"/>
            </c:otherwise>
        </c:choose>


    </body>

</html>