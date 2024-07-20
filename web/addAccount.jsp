<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Thêm tài khoản mới</title>
    </head>

    <body>
        <jsp:include page="_headerAdmin.jsp"/>

        <section class="section-content">
            <div class="container">
                <header class="section-heading py-4">
                    <h3 class="section-title">Thêm tài khoản mới</h3>
                </header> <!-- section-heading.// -->

                <main class="row mb-5"> 
                    <form class="col-lg-6" action="add">
                        <!-- Các trường input khác -->
                        <div class="mb-3">
                            <label for="user-username" class="form-label">User Name<span class="text-danger">*</span></label>
                            <input type="text"
                                   class="form-control"
                                   id="user-username"
                                   name="name"
                                   required>
                        </div>
                        <div class="mb-3">
                            <label for="user-password" class="form-label">Password<span class="text-danger">*</span></label>
                            <input type="password"
                                   class="form-control"
                                   id="user-password"
                                   name="pass"
                                   value=""
                                   required>
                        </div>
                        <span class="text-danger">${requestScope.errorMessage2}</span> 
                        <div class="mb-3">
                            <label for="user-email" class="form-label">Email<span class="text-danger">*</span></label>
                            <input type="email"
                                   class="form-control"
                                   id="user-email"
                                   name="email"
                                   value=""
                                   required>
                        </div>
                        <span class="text-danger">${requestScope.errorMessage1}</span> 
                        <span class="text-danger">${requestScope.mess4}</span> 
                        <div class="mb-3">
                            <label for="user-fullname" class="form-label">Full Name<span class="text-danger">*</span></label>
                            <input type="text"
                                   class="form-control"
                                   id="user-fullname"
                                   name="fullname"
                                   value=""
                                   required>
                        </div>
                        <div class="mb-3">
                            <label for="user-address" class="form-label">Address<span class="text-danger">*</span></label>
                            <input type="text"
                                   class="form-control"
                                   id="user-address"
                                   name="address"
                                   value=""
                                   required>
                        </div>
                        <div class="mb-3">
                            <label for="user-phone" class="form-label">Phone number<span class="text-danger">*</span></label>
                            <input type="text"
                                   class="form-control"
                                   id="user-phone"
                                   name="phone"
                                   value=""
                                   required>
                        </div>
                        <span class="text-danger">${requestScope.errorMessage3s}</span> 
                        <div class="mb-3">
                            <label for="user-role" class="form-label">Quyền <span class="text-danger">*</span></label>
                            <select class="form-select"
                                    id="user-role"
                                    name="role"
                                    required>
                                <option disabled selected>Chọn một quyền...</option>
                                <option value="1">Quản trị viên</option>
                                <option value="2">Nhân viên</option>
                                <option value="3">Khách hàng</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Thêm</button>
                        <button type="reset" class="btn btn-warning">Mặc định</button>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/userManager">Hủy</a>
                        <h3>${requestScope.errorMessage}</h3>
                        <h3>${requestScope.mess}</h3>
                    </form>
                    
                </main>
            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <jsp:include page="_footerAdmin.jsp"/>
    </body>

</html>
