<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Quản lý chương trình khuyến mãi</title>
    </head>
    <body>
        <jsp:include page="_headerAdmin.jsp"/>
        <section class="section-content">
            <div class="container">
                <header class="section-heading py-4 d-flex justify-content-between">
                    <h3 class="section-title">Quản lý chương trình khuyến mãi</h3>
                    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addPromotionModal">Thêm chương trình khuyến mãi</button>
                </header>
                <c:if test="${not empty sessionScope.error}">
                    <div style="color: red;">
                        <strong>Lỗi:</strong> ${sessionScope.error}
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.success}">
                    <div style="color: green;">
                        <strong>Thành công:</strong> ${sessionScope.success}
                    </div>
                </c:if>
                <c:remove var="error" scope="session"/>
                <c:remove var="success" scope="session"/>
                <main class="table-responsive-xl mb-5">
                    <table class="table table-bordered table-striped table-hover align-middle">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">STT</th>
                                <th scope="col">Tên chương trình</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Ngày bắt đầu</th>
                                <th scope="col">Ngày kết thúc</th>
                                <th scope="col">Giảm bao nhiêu(%)</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Ngày tạo</th>
                                <th scope="col">Ngày cập nhập</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="promotion" varStatus="loop" items="${requestScope.promotion}">
                                <tr>
                                    <th scope="row">${loop.index + 1}</th>
                                    <td>${promotion.getPromotionID()}</td>
                                    <td>${promotion.getTitle()}</td>
                                    <td>${promotion.getDescription()}</td>
                                    <td>${promotion.getStartDate()}</td>
                                    <td>${promotion.getEndDate()}</td>
                                    <td>${promotion.getDiscountPercentage()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${promotion.isIsActive()}">
                                                Còn hạn
                                            </c:when>
                                            <c:otherwise>
                                                Hết hạn
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${promotion.getCreatedAt()}</td>
                                    <td>${promotion.getUpdatedAt()}</td>
                                    <td class="text-center text-nowrap">

                                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editPromotionModal_${promotion.getPromotionID()}">Sửa</button>
                                        <form method="post">
                                            <input type="hidden" name="action" value="deletePromotion">
                                            <input type="hidden" name="promotionID" value="${promotion.getPromotionID()}">
                                            <button class="btn-sm btn-danger"
                                                    role="button"
                                                    onclick="return confirm('Bạn có muốn xóa?')">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </main>

                <c:if test="${totalPages != 0}">
                    <nav class="mt-3 mb-5">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="?page=${currentPage - 1}">
                                    Trang trước
                                </a>
                            </li>

                            <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage == i}">
                                        <li class="page-item active">
                                            <a class="page-link">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${i}">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="?page=${currentPage + 1}">
                                    Trang sau
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div> 
        </section>
        <!-- Add Promotion Modal -->
        <div class="modal fade" id="addPromotionModal" tabindex="-1" role="dialog" aria-labelledby="addPromotionModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addPromotionModalLabel">Thêm chương trình</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="promotionManager" method="post">
                            <input type="hidden" name="action" value="addPromotion">
                            <div class="form-group">
                                <label for="promotionTitle">Tên chương trình</label>
                                <input type="text" class="form-control" id="promotionTitle" name="title" placeholder="Nhập tên chương trình khuyến mãi">
                            </div>
                            <div class="form-group">
                                <label for="descriptionPromotion">Mô tả chương trình</label>
                                <input type="text" class="form-control" id="descriptionPromotion" name="description" placeholder="Nhập mô tả chương trình khuyến mãi">
                            </div>
                            <div class="form-group">
                                <label for="startDate">Ngày bắt đầu</label>
                                <input type="date" class="form-control" id="startDate" name="startDate" placeholder="Nhập ngày bắt đầu">
                            </div>
                            <div class="form-group">
                                <label for="endDate">Ngày kết thúc</label>
                                <input type="date" class="form-control" id="endDate" name="endDate" placeholder="Nhập ngày kết thúc">
                            </div>
                            <div class="form-group">
                                <label for="discountPercentage">Tỷ lệ chiết khấu (%)</label>
                                <input type="number" class="form-control" id="discountPercentage" name="discountPercentage" placeholder="Nhập tỷ lệ chiết khấu">
                            </div>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </form>
                    </div>   
                </div>
            </div>
        </div>
        <!-- Edit Promotion Modal -->
        <c:forEach var="promotion" varStatus="loop" items="${requestScope.promotion}">
            <div class="modal fade" id="editPromotionModal_${promotion.getPromotionID()}" tabindex="-1" role="dialog" aria-labelledby="editPromotionModalLabel_${promotion.getPromotionID()}" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editPromotionModalLabel_${promotion.getPromotionID()}">Sửa chương trình khuyến mãi</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="promotionManager" method="post">
                                <input type="hidden" name="action" value="updatePromotion">
                                <input type="hidden" name="promotionID" value="${promotion.getPromotionID()}">
                                <div class="form-group">
                                    <label for="editPromotionTitle_${promotion.getPromotionID()}">Tên chương trình</label>
                                    <input type="text" class="form-control" id="editPromotionTitle_${promotion.getPromotionID()}" name="title" value="${promotion.getTitle()}" placeholder="Nhập tên chương trình khuyến mãi">
                                </div>
                                <div class="form-group">
                                    <label for="editDescriptionPromotion_${promotion.getPromotionID()}">Mô tả chương trình</label>
                                    <input type="text" class="form-control" id="editDescriptionPromotion_${promotion.getPromotionID()}" name="description" value="${promotion.getDescription()}" placeholder="Nhập mô tả chương trình khuyến mãi">
                                </div>
                                <div class="form-group">
                                    <label for="editStartDate_${promotion.getPromotionID()}">Ngày bắt đầu</label>
                                    <input type="date" class="form-control" id="editStartDate_${promotion.getPromotionID()}" name="startDate" value="${promotion.getStartDate()}" placeholder="Nhập ngày bắt đầu">
                                </div>
                                <div class="form-group">
                                    <label for="editEndDate_${promotion.getPromotionID()}">Ngày kết thúc</label>
                                    <input type="date" class="form-control" id="editEndDate_${promotion.getPromotionID()}" name="endDate" value="${promotion.getEndDate()}" placeholder="Nhập ngày kết thúc">
                                </div>
                                <div class="form-group">
                                    <label for="editDiscountPercentage_${promotion.getPromotionID()}">Tỷ lệ chiết khấu (%)</label>
                                    <input type="number" class="form-control" id="editDiscountPercentage_${promotion.getPromotionID()}" name="discountPercentage" value="${promotion.getDiscountPercentage()}" placeholder="Nhập tỷ lệ chiết khấu">
                                </div>
                                <button type="submit" class="btn btn-primary">Cập nhật</button>
                            </form>
                        </div>   
                    </div>
                </div>
            </div>
        </c:forEach>

        <jsp:include page="_footerAdmin.jsp"/>

        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- DataTable JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

        <script>
                                                        function submitPromotionForm() {
                                                            document.getElementById("addPromotionForm").submit();
                                                        }
        </script>
    </body>
</html>
