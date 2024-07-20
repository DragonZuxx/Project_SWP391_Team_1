<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Tài khoản</title>
        <!-- Thêm CSS cho icon và thiết kế -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            /* Thêm CSS tùy chỉnh cho trang của bạn */
            /* Ví dụ */
            .card {
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .card-body {
                padding: 20px;
            }
            .bi {
                font-size: 20px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="_header1.jsp"/>

        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page">Tài khoản</h2>
            </div> <!-- container.// -->
        </section> <!-- section-pagetop.// -->
        <h3 style="color: red; font-size: 24px; font-weight: bold; text-align: center; margin-top: 20px;">
            ${requestScope.mess}
        </h3>
        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <jsp:include page="_navUser.jsp">
                        <jsp:param name="active" value="USER"/>
                    </jsp:include>

                    <main class="col-md-9">
                        <c:choose>
                            <c:when test="${empty sessionScope.account}">
                                <p>
                                    Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để xem thông tin tài khoản.
                                </p>
                            </c:when>
                            <c:otherwise>
                                <article class="card">
                                    <div class="card-body">
                                        <div>
                                            <h3>Thông tin người dùng</h3>
                                        </div>
                                        <hr>
                                        <p><i class="fas fa-envelope"></i> Email: ${sessionScope.account.getEmail()}</p>
                                        <p><i class="fas fa-user"></i> FullName: ${sessionScope.account.getFullName()}</p>
                                        <p><i class="fas fa-phone"></i> Số điện thoại: ${sessionScope.account.getPhone()}</p>
                                        <p class="bi bi-map d-block lh-lg">
                                            Địa chỉ: ${sessionScope.account.getAddress()}
                                        </p>
                                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editAccount">Sửa</button>
                                    </div> <!-- card-body.// -->
                                </article>
                            </c:otherwise>
                        </c:choose>
                    </main> <!-- col.// -->
                </div> <!-- row.// -->
            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <div class="modal fade" id="editAccount" tabindex="-1" aria-labelledby="editAccount" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Edit Account</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit Account Form -->
                        <form action="updateuser" method="post"> 
                            <div class="form-group">
                                <label for="Email">Email:</label>
                                <input type="text" class="form-control" id="email" name="email" value="${sessionScope.account.getEmail()}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="FullName">FullName:</label>
                                <input type="text" class="form-control" id="fullname" name="fullname" value="${sessionScope.account.getFullName()}">
                            </div>
                            <div class="form-group">
                                <label for="Phone">Số Điện Thoại:</label>
                                <input type="text" class="form-control" id="phone" name="phone" value="${sessionScope.account.getPhone()}">
                            </div>
                            <div class="form-group">
                                <label for="Address">Địa chỉ:</label>
                                <input type="text" class="form-control" id="address" name="address" value="${sessionScope.account.getAddress()}">
                            </div>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="_footer.jsp"/>
        <!-- Thêm JavaScript của Bootstrap -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>

</html>

