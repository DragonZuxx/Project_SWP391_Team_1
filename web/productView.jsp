<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp" />
        <title>${requestScope.product.getTitle()}</title>
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/coupon.css">
        <!-- Custom Scripts -->
        <script src="${pageContext.request.contextPath}/js/toast.js" type="module"></script>
        <script src="${pageContext.request.contextPath}/js/product.js" type="module"></script>
    </head>

    <body>
        <jsp:include page="_header.jsp" />

        <section class="section-pagetop-2 bg-light">
            <div class="container">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            ${requestScope.product.getTitle()}</li>
                    </ol>
                </nav>
            </div>
        </section>

        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <aside class="col-md-5 mb-md-0 mb-4 d-flex justify-content-center align-items-center">
                        <c:choose>
                            <c:when test="${empty requestScope.product.getCoverImage()}">
                                <img width="280" height="280" class="img-fluid"
                                     src="${pageContext.request.contextPath}/img/280px.png" alt="280px.png">
                            </c:when>
                            <c:otherwise>
                                <img width="280" height="280" class="img-fluid"
                                     src="${requestScope.product.getCoverImage()}"
                                     alt="${requestScope.product.getCoverImage()}">
                            </c:otherwise>
                        </c:choose>
                    </aside>

                    <main class="col-md-7">
                        <h2 class="title">${requestScope.product.getTitle()}</h2>

                        <div class="rating-wrap my-3">
                            <small class="label-rating text-muted me-2">${requestScope.countReview} đánh
                                giá</small>
                            <small class="label-rating text-success">
                                <i class="bi bi-bag-check-fill"></i> ${requestScope.product.getSoldQuantity()}
                                đã mua
                            </small>
                        </div>

                        <div class="mb-4">
                            <c:choose>
                                <c:when test="${empty requestScope.promotion}">
                                    <!-- If no promotion -->
                                    <span class="price mt-1 fw-bold">
                                        <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <!-- If there is a promotion -->
                                    <span class="price mt-1 fw-bold">
                                        <fmt:formatNumber pattern="#,##0" value="${product.getPrice() * (100 - requestScope.promotion.getDiscountPercentage()) / 100}"/>₫
                                    </span>
                                    <span class="ms-2 text-muted text-decoration-line-through">
                                        <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                    </span>
                                    <span class="ms-2 badge bg-info">
                                        -<fmt:formatNumber pattern="#,##0" value="${requestScope.promotion.getDiscountPercentage()}"/>%
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <dl class="row mb-4">
                            <dt class="col-xl-4 col-sm-5 col-6">Tác giả</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">
                                <c:forEach var="author" items="${requestScope.author}" varStatus="loop">
                                    ${author.getName()}
                                    <c:if test="${not loop.last}">, </c:if>
                                </c:forEach>
                            </dd>

                            <dt class="col-xl-4 col-sm-5 col-6">ISBN</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.getISBN()}</dd>

                            <dt class="col-xl-4 col-sm-5 col-6">Nhà xuất bản</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.getPublisher()}</dd>

                            <dt class="col-xl-4 col-sm-5 col-6">Năm xuất bản</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.getPublicationDate()}</dd>

                            <dt class="col-xl-4 col-sm-5 col-6">Số lượng</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">
                                <input type="number" id="cart-item-quantity" name="quantity" class="form-control w-50" value="1"
                                       min="1" max="${requestScope.product.getStock()}" step="1" />
                            </dd>
                        </dl>


                        <div>
                            <button type="button" class="btn btn-danger" id="add-wishlist-item" title="Thêm vào danh sách yêu thích" ${requestScope.isWishlistItem == 1 ? 'disabled' : '' }>
                                <i class="bi bi-heart"></i>
                            </button>
                            <button type="button" class="btn btn-primary ms-2" id="buy-now">Mua ngay</button>
                            <button type="button" class="btn btn-light ms-2" id="add-cart-item">Thêm vào giỏ hàng</button>

                        </div>

                    </main>
                </div>
                <br>
                <c:if test="${not empty sessionScope.success_addwishlist}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${sessionScope.success_addwishlist}
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.error_addwishlist}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${sessionScope.error_addwishlist}
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.successAddToCartMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${sessionScope.successAddToCartMessage}
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.errorAddToCartMessage}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${sessionScope.errorAddToCartMessage}
                    </div>
                </c:if>

                <c:remove var="successAddToCartMessage" scope="session" />
                <c:remove var="errorAddToCartMessage" scope="session" />
                <c:remove var="success_addwishlist" scope="session" />
                <c:remove var="error_addwishlist" scope="session" />

                <!-- Add this script at the bottom of your JSP -->
                <script>
                    window.onload = function () {
                        setTimeout(function () {
                            const successAlerts = document.querySelectorAll('.alert-success');
                            const errorAlerts = document.querySelectorAll('.alert-danger');

                            successAlerts.forEach(function (alert) {
                                alert.classList.remove('show');
                                alert.classList.add('fade');
                                setTimeout(() => alert.remove(), 150); // Ensure the alert is removed after the fade effect
                            });

                            errorAlerts.forEach(function (alert) {
                                alert.classList.remove('show');
                                alert.classList.add('fade');
                                setTimeout(() => alert.remove(), 150); // Ensure the alert is removed after the fade effect
                            });
                        }, 5000);
                    };
                </script>


            </div>
        </section>

        <section class="section-content mb-4">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h3 class="pb-2">Mô tả sản phẩm</h3>
                        <div>${requestScope.product.getDescription()}</div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section-content mb-5">
            <div class="container">
                <c:if test="${not empty sessionScope.successAddReviewMessage}">
                    <div class="alert alert-success">${sessionScope.successAddReviewMessage}</div>
                </c:if>
                <c:if test="${not empty sessionScope.errorAddReviewMessage}">
                    <div class="alert alert-danger">${sessionScope.errorAddReviewMessage}</div>
                </c:if>
                <c:if test="${not empty sessionScope.successDeleteReviewMessage}">
                    <div class="alert alert-success">${sessionScope.successDeleteReviewMessage}</div>
                </c:if>
                <c:if test="${not empty sessionScope.errorDeleteReviewMessage}">
                    <div class="alert alert-danger">${sessionScope.errorDeleteReviewMessage}</div>
                </c:if>
                <c:if test="${requestScope.countReview != 0}">
                    <h3 id="review" class="pb-2">${requestScope.countReview} đánh giá</h3>


                    <c:forEach var="review" items="${requestScope.review}">
                        <div class="border p-3 rounded mb-3">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex align-items-center">
                                    <c:forEach var="userreviews" items="${requestScope.userreviews}">
                                        <c:if test="${userreviews.getUserID() == review.getUserID()}">
                                            <h5 class="mb-0 me-3">${userreviews.getFullName()}</h5>
                                        </c:if>
                                    </c:forEach>
                                    <small class="text-muted">
                                        <fmt:parseDate value="${review.getCreatedAt()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedCreatedAt" type="both"/>
                                        <fmt:formatDate pattern="HH:mm dd/MM/yyyy" value="${parsedCreatedAt}"/>
                                    </small>
                                </div>
                                <div>

                                    <c:if test="${review.getUserID() == sessionScope.account.getUserID()}">
                                        <form action="${pageContext.request.contextPath}/deleteProductReview"
                                              method="post">
                                            <input type="hidden" name="contentdele" value="${review.getComment()}">
                                            <input type="hidden" name="productReviewId" value="${review.getUserID()}">
                                            <input type="hidden" name="productId" value="${requestScope.product.getBookID()}">
                                            <div class="btn-group" role="group">

                                                <button type="submit" class="btn btn-danger btn-sm"
                                                        onclick="return confirm('Bạn có muốn xóa?')">Xóa
                                                </button>
                                            </div>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                            <div class="mt-3">
                                <p class="mb-0">${review.getComment()}</p>
                            </div>
                        </div>
                    </c:forEach>

                </c:if>

                <h3 id="review-form" class="pb-2">Thêm đánh giá</h3>



                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <div class="ratting-form-wrapper">
                            <div class="ratting-form">
                                <form action="${pageContext.request.contextPath}/addProductReview"
                                      method="post">
                                    <div class="row mb-3">
                                        <div class="col">
                                            <textarea
                                                class="form-control ${not empty sessionScope.violations.contentViolations ? 'is-invalid' : (not empty sessionScope.values.content ? 'is-valid' : '')}"
                                                name="content" placeholder="Nội dung đánh giá"
                                                rows="3">${sessionScope.values.content}</textarea>
                                            <c:if test="${not empty sessionScope.violations.contentViolations}">
                                                <div class="invalid-feedback">
                                                    <ul class="list-unstyled mb-0">
                                                        <c:forEach var="violation"
                                                                   items="${sessionScope.violations.contentViolations}">
                                                            <li>${violation}</li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <input type="hidden" name="userId" value="${sessionScope.account.getUserID()}">
                                    <input type="hidden" name="productId" value="${requestScope.product.getBookID()}">
                                    <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                                </form>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Vui lòng <a href="${pageContext.request.contextPath}/login">đăng nhập</a> để đánh giá
                            sản phẩm.</p>
                        </c:otherwise>
                    </c:choose>
                    <c:remove var="values" scope="session" />
                    <c:remove var="violations" scope="session" />
                    <c:remove var="successAddReviewMessage" scope="session" />
                    <c:remove var="errorAddReviewMessage" scope="session" />
                    <c:remove var="successDeleteReviewMessage" scope="session" />
                    <c:remove var="errorDeleteReviewMessage" scope="session" />
            </div>
        </section>
        <section class="section-content mb-5">
            <div class="container">
                <h3 class="pb-2">Sản phẩm liên quan</h3>
                <div class="product-list">

                            <c:forEach var="relatedProduct" items="${requestScope.bookgetbyid}">
                                <c:choose>
                                    <c:when test="${requestScope.product.getBookID() != relatedProduct.getBookID()}">
                                        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 related-product-item">
                                            <div class="card p-3 mb-4">
                                                <a href="${pageContext.request.contextPath}/detailbook?id=${relatedProduct.getBookID()}"
                                                   class="img-wrap text-center">
                                                    <c:choose>
                                                        <c:when test="${empty relatedProduct.getCoverImage()}">
                                                            <img width="200" height="200" class="img-fluid"
                                                                 src="${pageContext.request.contextPath}/img/280px.png"
                                                                 alt="280px.png">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img width="200" height="200" class="img-fluid"
                                                                 src="${relatedProduct.getCoverImage()}"
                                                                 alt="${relatedProduct.getTitle()}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                                <figcaption class="info-wrap mt-2">
                                                    <a href="${pageContext.request.contextPath}/detailbook?id=${relatedProduct.getBookID()}"
                                                       class="title">${relatedProduct.getTitle()}</a>
                                                    <div>
                                                        <c:choose>
                                                            <c:when test="${empty requestScope.promotion}">
                                                                <!-- If no promotion -->
                                                                <span class="price mt-1 fw-bold">
                                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                                                </span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <!-- If there is a promotion -->
                                                                <span class="price mt-1 fw-bold">
                                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice() * (100 - requestScope.promotion.getDiscountPercentage()) / 100}"/>₫
                                                                </span>
                                                                <span class="ms-2 text-muted text-decoration-line-through">
                                                                    <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                                                </span>
                                                                <span class="ms-2 badge bg-info">
                                                                    -<fmt:formatNumber pattern="#,##0" value="${requestScope.promotion.getDiscountPercentage()}"/>%
                                                                </span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </figcaption>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
        </section>


        <!-- JavaScript đặt sau nội dung HTML -->
         <script>
            $(document).ready(function () {
                $('.product-list').slick({
                    infinite: true,
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    dots: true,
                    arrows: true,
                    prevArrow: '<button type="button" class="slick-prev">Trước</button>',
                    nextArrow: '<button type="button" class="slick-next">Tiếp</button>',
                    customPaging: function (slider, i) {
                        return '<button type="button" class="slick-page">' + (i + 1) + '</button>';
                    },
                    responsive: [
                        {
                            breakpoint: 1024,
                            settings: {
                                slidesToShow: 3,
                                slidesToScroll: 3
                            }
                        },
                        {
                            breakpoint: 600,
                            settings: {
                                slidesToShow: 2,
                                slidesToScroll: 2
                            }
                        },
                        {
                            breakpoint: 480,
                            settings: {
                                slidesToShow: 1,
                                slidesToScroll: 1
                            }
                        }
                    ]
                });
            });
        </script>

        
        <jsp:include page="_footer.jsp" />
    </body>

    <div class="toast-container position-fixed bottom-0 start-0 p-3"></div>

    <!-- JavaScript đặt sau nội dung HTML -->
    <script>
        window.onload = function () {
            const quantityInput = document.getElementById('cart-item-quantity');
            const stock = parseInt(quantityInput.getAttribute('max')); // Lấy giá trị tối đa từ thuộc tính max

            quantityInput.addEventListener('input', function () {
                const quantity = parseInt(quantityInput.value);

                if (quantity > stock) {
                    // Hiển thị thông báo lỗi nếu số lượng vượt quá số lượng tồn kho
                    showToast('Số lượng tồn kho không đủ hàng.', 'danger');
                    quantityInput.value = stock; // Đặt giá trị số lượng về giá trị tối đa
                }
            });

            function showToast(message, type) {
                const toastContainer = document.querySelector('.toast-container');
                const toast = document.createElement('div');
                toast.className = `toast align-items-center text-white bg-${type} border-0`;
                toast.innerHTML = `
                    <div class="d-flex">
                        <div class="toast-body">
        ${message}
                        </div>
                        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                `;
                toastContainer.appendChild(toast);
                const toastElement = new bootstrap.Toast(toast);
                toastElement.show();
            }

            // Ensure Bootstrap JavaScript is included and initialized
            if (typeof bootstrap !== 'undefined' && bootstrap.Toast) {
                console.log('Bootstrap Toast is available.');
            } else {
                console.error('Bootstrap Toast is not available.');
            }
        };
    </script>

</html>