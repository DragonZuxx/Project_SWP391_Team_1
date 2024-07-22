<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Kết quả tìm kiếm cho "${requestScope.bookName}"</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-content mb-5">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">
        Kết quả tìm kiếm cho "${requestScope.bookName}": ${requestScope.count} sản phẩm
      </h3>
    </header> <!-- section-heading.// -->

    <div class="row item-grid">
      <c:forEach var="product" items="${requestScope.listBook}">
        <div class="col-xl-3 col-lg-4 col-md-6">
          <div class="card p-3 mb-4">
            <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="img-wrap text-center">
              <c:if test="${empty product.getCoverImage()}">
                <img width="200"
                     height="200"
                     class="img-fluid"
                     src="${pageContext.request.contextPath}/img/280px.png"
                     alt="280px.png">
              </c:if>
              <c:if test="${not empty product.getCoverImage()}">
                <img width="200"
                     height="200"
                     class="img-fluid"
                     src="${product.getCoverImage()}"
                     alt="${product.getCoverImage()}">
              </c:if>
            </a>
            <figcaption class="info-wrap mt-2">
              <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="title">${product.title}</a>
              <div>
                <span class="price mt-1 fw-bold">
                  <fmt:formatNumber
                          pattern="#,##0"
                          value="${product.getPrice()}"/>₫
                </span>
                <span class="ms-2 text-muted text-decoration-line-through">
                  <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                </span>
                <span class="ms-2 badge bg-info">
                  -<fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>%
                </span>
              </div>
            </figcaption>
          </div>
        </div> <!-- col.// -->
      </c:forEach>
    </div> <!-- row.// -->

    
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>
</body>

</html>
