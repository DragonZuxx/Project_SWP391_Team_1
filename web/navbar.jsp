<%-- 
    Document   : navbar
    Created on : May 23, 2024, 10:12:43 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
            <div class="container">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                <strong><i class="bi bi-list"></i> Danh mục sản phẩm</strong>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach var="category" items="${category}">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/category?id=${category.getCategoryID()}">${category.getCategoryName()}</a></li>
                                    </c:forEach>
                                
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="listSelling">Sản phẩm bán chạy</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="wishlisttop">Sản phẩm được yêu thích nhất</a>
                        </li>
                    </ul>
                </div>
            </div> <!-- container.// -->
        </nav> <!-- navbar-main.// -->
    </body>
</html>
