
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
                <h3 class="section-title">Thêm Tác giả</h3>
            </header> <!-- section-heading.// -->
            <main class="row mb-5">
                <form class="col-lg-6" action="createauthor" method="post">
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success mb-3" role="alert">
                            ${successMessage}
                        </div>
                    </c:if>
                    <c:if test="${not empty errorMessenger}">
                        <div class="alert alert-danger mb-3" role="alert">
                            ${errorMessenger}
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <label for="author-name" class="form-label">Tên Tác giả <span class="text-danger">*</span></label>
                        <input type="text"
                               class="form-control"
                               id="author-name"
                               name="Name"
                               value="${param.Name != null ? param.Name : ''}"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="author-detail" class="form-label">Mô tả tác giả <span class="text-danger">*</span></label>
                        <textarea
                               class="form-control"
                               id="author-detail"
                               name="Biography"
                               required>${param.Biography != null ? param.Biography : ''}</textarea>
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