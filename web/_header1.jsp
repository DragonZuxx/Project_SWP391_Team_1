<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-3 py-3">
                    <a class="text-body d-flex align-items-center" href="home" >
                        <img src="img/11zon_cropped.png" style="width: 50px; margin-right: 10px;" />
                        <h3 class="m-0">Book Haven Shop</h3>
                    </a>
                </div> <!-- col.// -->
                <div class="col-lg-4 col-xl-5 ${empty sessionScope.currentUser ? 'mb-3 mb-lg-0' : ''}">
                    <form action="${pageContext.request.contextPath}/search" method="post" class="search">
                        <div class="input-group w-100">
                            <input type="text" class="form-control" placeholder="Nhập từ khóa cần tìm ..." name="q" value="${requestScope.query}">
                            <button class="btn btn-primary" type="submit">
                                <i class="bi bi-search"></i>
                            </button>
                        </div>
                    </form>

                </div> <!-- col.// -->


                <div class="col-lg-5 col-xl-4">
                    <c:if test="${not empty sessionScope.account}">
                        <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                            <li>
                                <a href="#" class="nav-link text-body">
                                    <i class="bi bi-list-check d-block text-center fs-3"></i>
                                    Đơn hàng
                                </a>
                            </li>
                            <li>
                                <a href="cart" class="nav-link text-body position-relative">
                                    <span id="total-cart-items-quantity" class="position-absolute top-0 end-0 mt-2 badge rounded-pill bg-primary">
                                        ${sessionScope.count}
                                    </span>
                                    <i class="bi bi-cart d-block text-center fs-3 position-relative"></i>
                                    Giỏ hàng
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-person d-block text-center fs-3"></i>
                                    Tài khoản
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="changeinformation">Thay đổi thông tin</a></li>
                                    <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                                </ul>
                            </li>

                        </ul>
                    </c:if>


                    <c:choose>
                        <c:when test="${ empty sessionScope.account}">
                            <a class="btn btn-light me-2" href="signupView.jsp" role="button">
                                Đăng ký
                            </a>
                            <a class="btn btn-primary" href="signinView.jsp" role="button">
                                Đăng nhập
                            </a>
                        </c:when>
                    </c:choose>

                </div> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- header-main.// -->
</header> <!-- section-header.// -->