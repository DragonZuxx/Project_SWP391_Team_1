<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Thêm thể loại</title>
    </head>

    <body>
        <jsp:include page="_headerAdmin.jsp"/>

        <section class="section-content">
            <div class="container">
                <header class="section-heading py-4">
                    <h3 class="section-title">Thêm thể loại</h3>
                </header> <!-- section-heading.// -->

                <main class="row mb-5">
                    <form class="col-lg-6" action="createcategory" method="post">
                        <c:if test="${not empty requestScope.successMessage}">
                            <div class="alert alert-danger mb-3" role="alert">
                                ${requestScope.successMessage}
                            </div>
                        </c:if>

                        <c:if test="${not empty requestScope.errorMessenger}">
                            <div class="alert alert-danger mb-3" role="alert">
                                ${requestScope.errorMessenger}
                            </div>
                        </c:if>
                        <div class="mb-3">
                            <label for="category-name" class="form-label">Tên thể loại <span class="text-danger">*</span></label>
                            <input type="text"
                                   class="form-control ${not empty requestScope.violations.nameViolations
                                                         ? 'is-invalid' : (not empty requestScope.category.CategoryName ? 'is-valid' : '')}"
                                   id="category-name"
                                   name="CategoryName"
                                   value="${requestScope.category.CategoryName}"
                                   required>
                            <c:if test="${not empty requestScope.violations.nameViolations}">
                                <div class="invalid-feedback">
                                    <ul class="list-unstyled">
                                        <c:forEach var="violation" items="${requestScope.violations.nameViolations}">
                                            <li>${violation}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </div>

                        <button type="submit" class="btn btn-primary me-2 mb-3">
                            Thêm
                        </button>

                        <a class="btn btn-danger mb-3"
                           href="${pageContext.request.contextPath}/categoryManager"
                           role="button"
                           onclick="return confirm('Bạn có muốn hủy?')">
                            Hủy
                        </a>
                    </form>
                </main>
            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <jsp:include page="_footerAdmin.jsp"/>
    </body>

</html>