<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="_meta.jsp" />
    <title>Thông tin thể loại #${requestScope.category.getCategoryID()}</title>
</head>

<body>
    <jsp:include page="_headerAdmin.jsp" />

    <section class="section-content">
        <div class="container">
            <header class="section-heading py-4">
                <h3 class="section-title">Thông tin thể loại</h3>
            </header> <!-- section-heading.// -->

            <div class="card mb-5">
                <div class="card-body">
                    <dl class="row">
                        <dt class="col-md-3">ID</dt>
                        <dd class="col-md-9">${requestScope.category.getCategoryID()}</dd>

                        <dt class="col-md-3">Tên thể loại: </dt>
                        <dd class="col-md-9">
                            <a href="${pageContext.request.contextPath}/category?id=${requestScope.category.getCategoryID()}"
                                target="_blank">
                                ${requestScope.category.getCategoryName()}
                            </a>
                        </dd>

                        <dt class="col-md-3">Ngày tạo thể loại:</dt>
                        <dd class="col-md-9">${requestScope.category.getCreatedAt()}</dd>

                        <dt class="col-md-3">Ngày cập nhật thể loại: </dt>
                        <dd class="col-md-9">${requestScope.category.getUpdatedAt()}</dd>
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
