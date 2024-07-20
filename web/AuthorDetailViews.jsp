jsp
Copy code
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="_meta.jsp" />
    <title>Thông tin Tác giả #${requestScope.author.getAuthorID()}</title>
</head>

<body>
    <jsp:include page="_headerAdmin.jsp" />

    <section class="section-content">
        <div class="container">
            <header class="section-heading py-4">
                <h3 class="section-title">Thông tin Tác giả</h3>
            </header> <!-- section-heading.// -->

            <div class="card mb-5">
                <div class="card-body">
                    <dl class="row">
                        <dt class="col-md-3">ID</dt>
                        <dd class="col-md-9">${requestScope.author.getAuthorID()}</dd>

                        <dt class="col-md-3">Tên Tác giả: </dt>
                        <dd class="col-md-9">
                            <a href="${pageContext.request.contextPath}/author?id=${requestScope.author.getAuthorID()}"
                                target="_blank">
                                ${requestScope.author.getName()}
                            </a>
                        </dd>

                        <dt class="col-md-3">Thông tin Tác giả:</dt>
                        <dd class="col-md-9">${requestScope.author.getBiography()}</dd>

                        <dt class="col-md-3">Ngày tạo Tác giả:</dt>
                        <dd class="col-md-9">${requestScope.author.getCreatedAt()}</dd>

                        <dt class="col-md-3">Ngày cập nhật Tác giả:</dt>
                        <dd class="col-md-9">${requestScope.author.getUpdatedAt()}</dd>
                    </dl>
                </div>
            </div> <!-- card.// -->

            <!-- Nút Quay lại -->
            <button class="btn btn-secondary" onclick="goBack()">Quay lại</button>
        </div> <!-- container.// -->
    </section> <!-- section-content.// -->

    <jsp:include page="_footerAdmin.jsp" />
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>