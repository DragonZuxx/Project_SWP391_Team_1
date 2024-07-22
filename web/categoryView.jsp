<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>${requestScope.category.getCategoryName()}</title>
    </head>
    <body>
        <jsp:include page="_header.jsp"/>
        <section class="section-pagetop bg-light">
            <div class="container">
                <h2 class="title-page">${requestScope.category.getCategoryName()}</h2>
               
            </div> <!-- container.// -->
        </section> <!-- section-pagetop.// -->
        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <aside class="col-md-4 col-lg-3 mb-md-0 mb-3">
                        <div class="card">
                            <form action="${pageContext.request.contextPath}/listdetailcategory" method="get">
                                
                                <input type="hidden" name="categoryID" value="${requestScope.category.getCategoryID() != null ? requestScope.category.getCategoryID() : categoryID}">
                                <!-- Nhà xuất bản -->
                                <article class="filter-group">
                                    <header class="card-header my-1">
                                        <a data-bs-toggle="collapse" href="#collapse_1" aria-expanded="true" aria-controls="collapse_1">
                                            <i class="float-end bi bi-chevron-down"></i>
                                            <h6 class="title fw-bold">Nhà xuất bản</h6>
                                        </a>
                                    </header>
                                    <div class="filter-content collapse show" id="collapse_1">
                                        <div class="card-body pt-0">
                                            <c:choose>
                                                <c:when test="${not empty requestScope.publishers}">
                                                    <c:forEach var="publisher" items="${requestScope.publishers}" varStatus="status">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" value="${publisher}"
                                                                   id="radio_publisher_${status.index}" name="selectedPublisher"
                                                                   ${requestScope.selectedPublisher eq publisher ? 'checked' : ''}>
                                                            <label class="form-check-label" for="radio_publisher_${status.index}">
                                                                ${publisher}
                                                            </label>
                                                        </div>
                                                    </c:forEach>
                                                </c:when>      
                                            </c:choose>
                                        </div>
                                    </div>
                                </article>

                                <!-- Giá bán -->
                                <article class="filter-group">
                                    <header class="card-header my-1">
                                        <a data-bs-toggle="collapse" href="#collapse_2" aria-expanded="true" aria-controls="collapse_2">
                                            <i class="float-end bi bi-chevron-down"></i>
                                            <h6 class="title fw-bold">Giá bán</h6>
                                        </a>
                                    </header>
                                    <div class="filter-content collapse show" id="collapse_2">
                                        <div class="card-body pt-0">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="0-50000"
                                                       id="checkbox_price_1" name="priceRanges"
                                                       ${requestScope.priceRanges.contains('0-50000') ? 'checked' : ''}>
                                                <label class="form-check-label" for="checkbox_price_1">
                                                    Dưới 50.000₫
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="50000-200000"
                                                       id="checkbox_price_2" name="priceRanges"
                                                       ${requestScope.priceRanges.contains('50000-200000') ? 'checked' : ''}>
                                                <label class="form-check-label" for="checkbox_price_2">
                                                    Từ 50.000₫ đến 200.000₫
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="200000-infinity"
                                                       id="checkbox_price_3" name="priceRanges"
                                                       ${requestScope.priceRanges.contains('200000-infinity') ? 'checked' : ''}>
                                                <label class="form-check-label" for="checkbox_price_3">
                                                    Trên 200.000₫
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </article>

                                <!-- Sắp xếp theo -->
                                <article class="filter-group">
                                    <header class="card-header my-1">
                                        <a data-bs-toggle="collapse" href="#collapse_3" aria-expanded="true"
                                           aria-controls="collapse_3">
                                            <i class="float-end bi bi-chevron-down"></i>
                                            <h6 class="title fw-bold">Sắp xếp theo</h6>
                                        </a>
                                    </header>
                                    <div class="filter-content collapse show" id="collapse_3">
                                        <div class="card-body pt-0">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="totalBuy-DESC" name="order"
                                                       id="radio_order_1" ${requestScope.order == 'totalBuy-DESC' ? 'checked' : ''}>
                                                <label class="form-check-label" for="radio_order_1">
                                                    Bán chạy nhất
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="createdAt-DESC" name="order"
                                                       id="radio_order_2" ${requestScope.order == 'createdAt-DESC' ? 'checked' : ''}>
                                                <label class="form-check-label" for="radio_order_2">
                                                    Mới nhất
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="price-ASC" name="order"
                                                       id="radio_order_3" ${requestScope.order == 'price-ASC' ? 'checked' : ''}>
                                                <label class="form-check-label" for="radio_order_3">
                                                    Giá thấp nhất
                                                </label>
                                            </div>
                                        </div> <!-- card-body.// -->
                                    </div>
                                </article>

                                <article class="card-body">
                                    <button type="submit" class="btn btn-primary w-100">Lọc</button>
                                </article>
                            </form>
                        </div>
                    </aside>
                    <main class="col-md-8 col-lg-9">
                        <header class="border-bottom mb-4 pb-3">
                            <div class="form-inline d-flex justify-content-between align-items-center">
                                <span>${requestScope.books.size()} sản phẩm</span>
                            </div>
                        </header>

                        <div class="row item-grid">
                            <c:forEach var="product" items="${requestScope.books}">
                                <div class="col-xl-4 col-lg-6">
                                    <div class="card p-3 mb-4">
                                        <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}"
                                           class="img-wrap text-center">
                                            <c:choose>
                                                <c:when test="${empty product.getCoverImage()}">
                                                    <img width="200" height="200" class="img-fluid"
                                                         src="${pageContext.request.contextPath}/img/280px.png" alt="280px.png">
                                                </c:when>
                                                <c:otherwise>
                                                    <img width="200" height="200" class="img-fluid" src="${product.getCoverImage()}"
                                                         alt="${product.getCoverImage()}">
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                        <figcaption class="info-wrap">
                                            <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}"
                                               class="title text-truncate">${product.getTitle()}</a>
                                            <div class="price-wrap">
                                                <var class="price">
                                                    <fmt:formatNumber value="${product.getPrice()}" type="currency"/>
                                                </var>
                                            </div>
                                        </figcaption>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </main>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>
