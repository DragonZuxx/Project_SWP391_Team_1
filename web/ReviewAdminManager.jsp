<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
        <title>Quản lý Đánh giá</title>
    </head>
    <body>
        <jsp:include page="_headerAdmin.jsp"/>
        <section class="section-content py-5">
            <div class="container">
                <header class="section-heading d-flex justify-content-between align-items-center mb-4">
                    <h3 class="section-title m-0">Danh sách Đánh giá mới nhất</h3>
                </header>
                <main class="table-responsive">
                    <table class="table table-bordered table-striped table-hover align-middle">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">ID Đánh giá</th>
                                <th scope="col">Tên Sách</th>
                                <th scope="col">Tên Người dùng</th>
                                <th scope="col">Số điện thoại người dùng</th>
                                <th scope="col">Bình luận</th>
                                <th scope="col">Ngày đánh giá</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${latereview}">
                                <tr>
                                    <th scope="row">${entry.key.reviewID}</th>
                                    <td>${entry.key.reviewID}</td>
                                    <td>${entry.value.bookTitle}</td>
                                    <td>${entry.value.fullName}</td>
                                    <td>${entry.value.phone}</td>
                                    <td>${entry.key.comment}</td>
                                    <td>${entry.key.createdAt}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </main>

                <c:if test="${totalPages > 1}">
                    <nav class="mt-3 mb-5">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="${pageContext.request.contextPath}/reviewManager?page=${currentPage - 1}">
                                    Trang trước
                                </a>
                            </li>

                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/reviewManager?page=${i}">
                                        ${i}
                                    </a>
                                </li>
                            </c:forEach>

                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="${pageContext.request.contextPath}/reviewManager?page=${currentPage + 1}">
                                    Trang sau
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </section>
        <jsp:include page="_footerAdmin.jsp"/>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
