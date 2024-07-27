<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Quên mật khẩu</title>
    </head>

    <body>
        <jsp:include page="_header1.jsp"/>

        <h1 style="color: red; font-size: 24px; margin-top: 20px ;font-family: Arial, sans-serif; text-align: center;">${requestScope.mess}</h1>
        <section class="section-content" style="margin: 100px 0;">
            <div class="card mx-auto" style="max-width: 380px">
                <div class="card-body">
                    <h4 class="card-title mb-4">Quên mật khẩu</h4>
                    <form action="forgot-password" method="post">
                        <div class="mb-3">
                            <input name="email"
                                   class="form-control ${not empty requestScope.violations.usernameViolations
                                                         ? 'is-invalid' : (not empty requestScope.values.username ? 'is-valid' : '')}"
                                   placeholder="Email"
                                   type="email"
                                   autocomplete="off"
                                   value="${requestScope.values.username}" required>
                            <c:if test="${not empty requestScope.violations.usernameViolations}">
                                <div class="invalid-feedback">
                                    <ul class="list-unstyled">
                                        <c:forEach var="violation" items="${requestScope.violations.usernameViolations}">
                                            <li>${violation}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </div>

                        <a href="login" style="display: flex; align-items: center; justify-content: flex-end;">
                            <span style="margin-left: 5px;">
                                <!-- Thêm biểu tượng hoặc icon vào đây -->
                                <i class="fas fa-question-circle"></i>
                            </span>
                            <span style="margin-right: 5px;">Đăng nhập</span>
                        </a>
                        <button type="submit" class="btn btn-primary w-100">Lấy lại mật khẩu</button>

                    </form>
                </div> <!-- card-body.// -->
            </div> <!-- card .// -->
            <p class="text-center mt-4">Không có tài khoản? <a href="signupView.jsp">Đăng ký</a></p> 
        </section> <!-- section-content.// -->

        <jsp:include page="_footer.jsp"/>
    </body>

</html>
