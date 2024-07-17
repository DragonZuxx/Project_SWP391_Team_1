<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-3 py-3">
                    <a class="text-body" href="home">
                        <h3>Book Haven Shop</h3>
                    </a>
                </div> <!-- col.// -->

                <div class="col-lg-6 col-xl-6 ${empty sessionScope.account ? 'mb-3 mb-lg-0' : ''}">
                    <form action="${pageContext.request.contextPath}/search" method="post" class="search">
                        <div class="input-group w-100">
                            <input type="text"
                                   class="form-control"
                                   placeholder="Nhập từ khóa cần tìm ..."
                                   name="q"
                                   value="${requestScope.query}">
                            <button class="btn btn-primary" type="submit">
                                <i class="bi bi-search"></i>
                            </button>
                        </div>
                    </form>
                </div> <!-- col.// -->

                <div class="col-lg-3 col-xl-3">
                    <c:if test="${not empty sessionScope.account}">
                        <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                           <c:choose>
                        <c:when test="${not empty sessionScope.account}">
                            <c:if test="${sessionScope.account.getRoleID() == 1 || sessionScope.account.getRoleID() == 2 }"> 
                                <div class="d-flex justify-content-end">
                                    <button class="btn btn-light me-2" style="background-color: white; border: 1px solid #007bff; margin-right: 5px; display: flex; justify-content: center; align-items: center; padding: 10px 5px;" type="button">
                                        <a href="userManager" class="text-dark mr-3" style="text-decoration: none;">Admin</a>
                                    </button>
                                </div>
                            </c:if>
                        </c:when>
                    </c:choose>
                            <li>
                                <a href="cart" class="nav-link text-body position-relative">
                                    <span id="total-cart-items-quantity" class="position-absolute top-0 end-0 mt-2 badge rounded-pill bg-primary">
                                        ...
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
                                    <li><a class="dropdown-item" href="orderUser">Đơn Hàng</a></li>
                                    <li><a class="dropdown-item" href="userView.jsp">Thay đổi thông tin</a></li>
                                    <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </ul>
                    </c:if>

                    <c:choose>
                        <c:when test="${ empty sessionScope.account}">
                            <a class="btn btn-light me-2" href="register" role="button">
                                Đăng ký
                            </a>
                            <a class="btn btn-primary" href="login" role="button">
                                Đăng nhập
                            </a>
                        </c:when>
                    </c:choose>
                </div> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- header-main.// -->
</header> <!-- section-header.// -->
