<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Sửa thể loại</title>
    </head>

    <body>
        <jsp:include page="_headerAdmin.jsp"/>

        <section class="section-content">
            <div class="container">
                <header class="section-heading py-4">
                    <h3 class="section-title">Sửa Tác giả</h3>
                </header> <!-- section-heading.// -->

                <main class="row mb-5">
                    <form class="col-lg-6" method="post" action="updateauthor">

                        <!-- Hiển thị thông báo thành công -->
                        <c:if test="${not empty requestScope.successMessage}">
                            <div class="alert alert-success mb-3" role="alert">
                                ${requestScope.successMessage}
                            </div>
                        </c:if>

                        <!-- Hiển thị thông báo lỗi -->
                        <c:if test="${not empty requestScope.errorMessenger}">
                            <div class="alert alert-danger mb-3" role="alert">
                                ${requestScope.errorMessenger}
                            </div>
                        </c:if>

                        <div class="mb-3">
                            <label for="author-name" class="form-label">Tên Tác giả <span class="text-danger">*</span></label>
                            <input type="text"
                                   class="form-control ${not empty requestScope.violations.nameViolations
                                                         ? 'is-invalid' : (not empty requestScope.author.getName() ? 'is-valid' : '')}"
                                   id="author-name"
                                   name="authorname"
                                   value="${requestScope.author.getName()}"
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
                       
                        <div class="mb-3">
                            <label for="author-biography" class="form-label">Mô tả Tác giả <span class="text-danger">*</span></label>
                            <textarea
                                class="form-control ${not empty requestScope.violations.nameViolations
                                                      ? 'is-invalid' : (not empty requestScope.author.getBiography() ? 'is-valid' : '')}"
                                id="author-biography"
                                name="authorbiography"
                                rows="5"
                                required>${requestScope.author.getBiography()}</textarea>
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

                        <!-- Hidden input để giữ lại giá trị ID -->
                        <input type="hidden" name="id" value="${requestScope.author.getAuthorID()}">

                        <button type="submit" class="btn btn-primary me-2 mb-3" name="sua">
                            Sửa
                        </button>

                        <a class="btn btn-danger mb-3"
                           href="${pageContext.request.contextPath}/authorManager"
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
