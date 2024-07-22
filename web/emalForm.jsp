<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Xác nhận Email</title>
    </head>

    <body>
        <style>
            .btn-secondary {
                background-color: #6c757d;
                color: #fff;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
            }

            /* Định dạng giá sản phẩm */
            .price {
                color: #dc3545;
                font-size: 1.25rem;
            }

            /* Định dạng badge */
            .badge {
                font-size: 0.875rem;
                padding: 4px 8px;
                border-radius: 4px;
            }
        </style>
        <jsp:include page="_header1.jsp"/>

        <h1 style="color: red; display: flex; align-items: center; justify-content: center;">${requestScope.mess}</h1>
        <section class="section-content" style="margin: 100px 0;">
            <div class="card mx-auto" style="max-width: 380px">
                <div class="card-body">
                    <c:if test="${not empty requestScope.successMessage}">
                        <div class="alert alert-success" role="alert">${requestScope.successMessage}</div>
                    </c:if>
                    <c:if test="${not empty requestScope.errorMessage}">
                        <div class="alert alert-danger" role="alert">${requestScope.errorMessage}</div>
                    </c:if>
                    <h4 class="card-title mb-4" style="text-align: center">Xác nhận đăng nhập <br/>
                        Nhập mã từ email được gửi tới email của bạn</h4>
                    <form action="${pageContext.request.contextPath}/verify" method="post">
                        <div class="mb-3">
                            <input type="text" name="email" value="${sessionScope.email}" readonly=""  ><br>
                            <label for="inputEmail" class="form-label">Mã xác nhận</label>
                            <input type="text"
                                   class="form-control ${not empty requestScope.violations.emailViolations
                                                         ? 'is-invalid' : (not empty requestScope.values.email ? 'is-valid' : '')}"
                                   id="inputEmail"
                                   name="otp"
                                   value="${requestScope.values.email}"
                                   placeholder="Nhập mã xác minh">
                            <c:if test="${not empty requestScope.violations.emailViolations}">
                                <div class="invalid-feedback">
                                    <ul class="list-unstyled">
                                        <c:forEach var="violation" items="${requestScope.violations.emailViolations}">
                                            <li>${violation}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Xác nhận</button>
                    </form>
                </div> <!-- card-body.// -->
            </div> <!-- card.// -->
        </section> <!-- section-content.// -->

        <jsp:include page="_footer.jsp"/>
    </body>

</html>
