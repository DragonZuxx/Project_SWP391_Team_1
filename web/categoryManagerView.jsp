<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="_meta.jsp"/>
    <title>Quản lý thể loại</title>
    <style>
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
        }

        .header-container .input-group {
            width: 100%;
            max-width: 400px;
            margin-right: 20px;
        }
    </style>
    <script type="text/javascript">
        function doDelete(CategoryID) {
            if (confirm("Are you sure to delete Category with ID = " + CategoryID + "?")) {
                window.location = "deletecategory?CategoryID=" + CategoryID;
            }
        }
    </script>
</head>

<body>
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

            <div class="header-container">
                <h3 class="section-title">Quản lý thể loại</h3>
                <div class="search-container">
                    <form action="${pageContext.request.contextPath}/searchmanagercate" method="post" class="searchmanagercate">
                        <div class="input-group">
                            <input type="text"
                                   class="form-control"
                                   placeholder="Nhập từ khóa cần tìm ..."
                                   name="searchcate"
                                   value="${requestScope.searchcate}">
                            <button class="btn btn-primary" type="submit">
                                <i class="bi bi-search"></i>
                            </button>
                        </div>
                    </form>
                </div>
                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/createCategoryView.jsp"
                   role="button">
                    Thêm thể loại
                </a>
            </div> <!-- header-container.// -->

            <main class="table-responsive-xl mb-5">
                <table class="table table-bordered table-striped table-hover align-middle">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">ID</th>
                            <th scope="col">Tên thể loại</th>
                            <th scope="col" style="width: 225px;">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="category" varStatus="loop" items="${requestScope.categories}">
                            <tr>
                                <th scope="row">${loop.index + 1}</th>
                                <td>${category.getCategoryID()}</td>

                                <td>
                                    <a href="${pageContext.request.contextPath}/category?id=${category.getCategoryID()}"
                                       target="_blank">${category.getCategoryName()}</a>
                                </td>
                                <td class="text-center text-nowrap">
                                    <a class="btn btn-primary me-2"
                                       href="${pageContext.request.contextPath}/detailCate?id=${category.getCategoryID()}"
                                       role="button">
                                        Xem
                                    </a>
                                    <a class="btn btn-success me-2"
                                       href="updatecategory?id=${category.getCategoryID()}"
                                       role="button">
                                        Sửa
                                    </a>
                                    <a class="btn btn-danger me-2"
                                       href="#" 
                                       onclick="doDelete('${category.getCategoryID()}')" 
                                       role="button">
                                        Xóa
                                    </a>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>

            <c:if test="${requestScope.totalPages > 1}">
                <nav class="mt-3 mb-5">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${requestScope.currentPage == 1 ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/categoryManager?page=${requestScope.currentPage - 1}">
                                Trang trước
                            </a>
                        </li>

                        <c:set var="maxDisplayPages" value="5"/>
                        <c:set var="currentBlock" value="${(requestScope.currentPage - 1) / maxDisplayPages + 1}"/>
                        <c:set var="startPage" value="${(currentBlock - 1) * maxDisplayPages + 1}"/>
                        <c:set var="endPage" value="${startPage + maxDisplayPages - 1}"/>
                        <c:if test="${endPage > requestScope.totalPages}">
                            <c:set var="endPage" value="${requestScope.totalPages}"/>
                        </c:if>

                        <c:choose>
                            <c:when test="${requestScope.totalPages <= 5}">
                                <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                                    <li class="page-item ${requestScope.currentPage == i ? 'active' : ''}">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/categoryManager?page=${i}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item ${requestScope.currentPage == 1 ? 'active' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/categoryManager?page=1">1</a>
                                </li>

                                <c:if test="${startPage > 2}">
                                    <li class="page-item disabled"><a class="page-link">...</a></li>
                                    </c:if>

                                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                    <c:choose>
                                        <c:when test="${i == 1}"></c:when>
                                        <c:when test="${requestScope.currentPage == i}">
                                            <li class="page-item active">
                                                <a class="page-link">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="${pageContext.request.contextPath}/categoryManager?page=${i}">
                                                    ${i}
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${endPage < requestScope.totalPages - 1}">
                                    <li class="page-item disabled"><a class="page-link">...</a></li>
                                    </c:if>

                                <li class="page-item ${requestScope.currentPage == requestScope.totalPages ? 'active' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/categoryManager?page=${requestScope.totalPages}">
                                        ${requestScope.totalPages}
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <li class="page-item ${requestScope.currentPage == requestScope.totalPages ? 'disabled' : ''}">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/categoryManager?page=${requestScope.currentPage + 1}">
                                Trang sau
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div> <!-- container.// -->
    </section> <!-- section-content.// -->

    <jsp:include page="_footerAdmin.jsp"/>
    
</body>

</html>