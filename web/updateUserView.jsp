<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp" />
        <title>Sửa người dùng #${requestScope.account.userID}</title>
    </head>

    <body>
        <c:choose>
            <c:when test="${empty sessionScope.account.getRoleID() || sessionScope.account.getRoleID() == 3 || sessionScope.account.getRoleID() == 2}">
                <jsp:include page="admin401View.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="_headerAdmin.jsp" />

                <section class="section-content">
                    <div class="container">
                        <header class="section-heading py-4">
                            <h3 class="section-title">Sửa người dùng #${requestScope.account.userID}</h3>
                        </header>
                        <!-- section-heading.// -->

                        <main class="row mb-5">
                            <c:set var="a" value="${requestScope.account}" />
                            <form class="col-lg-6" action="edit" method="post">
                                <!-- Các trường input khác -->
                                <div class="mb-3">
                                    <label for="user-username" class="form-label">UserID<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="user-username" name="userID" value="${a.userID}"
                                           readonly>
                                </div>

                                <div class="mb-3">
                                    <label for="user-password" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="user-password" name="password"
                                           value="${a.password}">
                                </div>
                                <span class="text-danger">${requestScope.errorMessage2}</span>
                                <div class="mb-3">
                                    <label for="user-email" class="form-label">Email<span class="text-danger">*</span></label>
                                    <input type="email" class="form-control" id="user-email" name="email" value="${a.email}"
                                           readonly>
                                </div>

                                <span class="text-danger">${requestScope.errorMessage1}</span>
                                <div class="mb-3">
                                    <label for="user-fullname" class="form-label">Full Name<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="user-fullname" name="fullname"
                                           value="${a.fullName}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="user-address" class="form-label">Address<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="user-address" name="address" value="${a.address}"
                                           required>
                                </div>
                                <div class="mb-3">
                                    <label for="user-phone" class="form-label">Phone number<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="user-phone" name="phone" value="${a.phone}"
                                           required>
                                </div>

                                <div class="mb-3">
                                    <label for="user-active-role" class="form-label">Quyền hoạt động <span class="text-danger">*</span></label>
                                    <select class="form-select" id="user-active-role" name="isActive" required>
                                        <option value="" ${a.isIsActive() == null ? 'selected' : ''}>Quyền hoạt động...</option>
                                        <option value="false" ${a.isIsActive() != null && !a.isIsActive() ? 'selected' : ''}>Cấm</option>
                                        <option value="true" ${a.isIsActive() != null && a.isIsActive() ? 'selected' : ''}>Hoạt động</option>
                                    </select>
                                </div>



                                <span class="text-danger">${requestScope.errorMessage3}</span>
                                <div class="mb-3">
                                    <label for="user-role" class="form-label">Quyền <span class="text-danger">*</span></label>
                                    <select class="form-select" id="user-role" name="roleID" required>
                                        <option disabled>Chọn một quyền...</option>
                                        <option value="1" ${a.roleID == 1 ? 'selected' : ''}>Quản trị viên</option>
                                        <option value="2" ${a.roleID == 2 ? 'selected' : ''}>Nhân viên</option>
                                        <option value="3" ${a.roleID == 3 ? 'selected' : ''}>Khách hàng</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Sửa</button>
                                <button type="reset" class="btn btn-warning">Reset</button>
                                <a class="btn btn-danger" href="userManager">Hủy</a>

                            </form>

                        </main>
                    </div>
                    <!-- container.// -->
                </section>
                <!-- section-content.// -->

                <jsp:include page="_footerAdmin.jsp" />
            </c:otherwise>
        </c:choose>
    </body>

</html>
