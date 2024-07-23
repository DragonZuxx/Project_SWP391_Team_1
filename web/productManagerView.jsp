<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Quản lý sản phẩm</title>
        <!-- Include Choices.js CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/choices.js/public/assets/styles/choices.min.css">

        <!-- Include Choices.js JS -->
        <script src="https://cdn.jsdelivr.net/npm/choices.js/public/assets/scripts/choices.min.js"></script>
    </head>

    <body>
        <jsp:include page="_headerAdmin.jsp"/>

        <section class="section-content">
            <div class="container">
                <c:if test="${param.success ne null}">
                    <div class="alert alert-success mb-0 mt-4" role="alert">
                        Thành công
                    </div>
                </c:if>
                <c:if test="${param.fail ne null}">
                    <div class="alert alert-danger mb-0 mt-4" role="alert">
                        Thất bại
                    </div>
                </c:if>
                <c:remove var="successMessage" scope="session"/>
                <c:remove var="errorMessage" scope="session"/>

                <header class="section-heading py-4 d-flex justify-content-between">
                    <h3 class="section-title">Quản lý sản phẩm</h3>
                    <!--                    <a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/admin/productManager/create"
                                           role="button"
                                           style="height: fit-content;">
                                            Thêm sản phẩm
                                        </a>-->
                    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addBookModal">Thêm sản phẩm</button>

                </header> <!-- section-heading.// -->

                <!-- Search Form -->
                <div class="row">
                    <form id="searchForm" class="form-inline mb-4 w-50" action="" method="get">
                        <input type="text" class="form-control mr-2 mb-3" name="name" placeholder="Tìm kiếm theo tên" value="${name}">
                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                    </form>
                </div>

                <main class="table-responsive-xl mb-5">
                    <table class="table table-bordered table-striped table-hover align-middle">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">ID</th>
                                <th scope="col">Hình</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Giá bán</th>
                                <th scope="col">Tồn kho</th>
                                <th scope="col" style="width: 225px;">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" varStatus="loop" items="${requestScope.products}">
                                <tr>
                                    <th scope="row">${loop.index + 1}</th>
                                    <td>${product.bookID}</td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${empty product.coverImage}">
                                                <img width="38" src="${pageContext.request.contextPath}/img/280px.png"
                                                     alt="280px.png">
                                            </c:when>
                                            <c:otherwise>
                                                <img width="38" src="${product.coverImage}"
                                                     alt="${product.coverImage}">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/product?id=${product.bookID}" target="_blank">${product.title}</a>
                                    </td>
                                    <td><fmt:formatNumber pattern="#,##0" value="${product.price}"/>₫</td>
                                    <td>${product.stock}</td>
                                    <td class="text-center text-nowrap">
                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#bookInfoModal_${product.getBookID()}">Xem</button>
                                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editBookModal_${product.getBookID()}">Sửa</button>
                                        <form method="post">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="bookID" value="${product.getBookID()}">
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
                                <a class="page-link" href="?page=${currentPage - 1}&name=${name}">
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
                                            <a class="page-link" href="?page=${i}&name=${name}">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="?page=${currentPage + 1}&name=${name}">
                                    Trang sau
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <c:forEach var="book" items="${products}">

            <!-- Edit Book Modal -->
            <div class="modal fade" id="editBookModal_${book.getBookID()}" tabindex="-1" role="dialog" aria-labelledby="editBookModalLabel_${book.getBookID()}" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editBookModalLabel_${book.getBookID()}">Edit Book</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- Edit Book Form -->
                            <form action="productManager" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="bookID" value="${book.getBookID()}">
                                <div class="form-group">
                                    <label for="title">Tiêu đề</label>
                                    <input type="text" class="form-control" id="title" name="title" value="${book.getTitle()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="publisher">Nhà xuất bản</label>
                                    <input type="text" class="form-control" id="publisher" name="publisher" value="${book.getPublisher()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="publicationDate">Ngày xuất bản</label>
                                    <input type="text" class="form-control" id="publicationDate" name="publicationDate" value="${book.getPublicationDate()}">
                                </div>
                                <div class="form-group">
                                    <label for="isbn">ISBN</label>
                                    <input type="text" class="form-control" id="isbn" name="isbn" value="${book.getISBN()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="price">Giá bán</label>
                                    <input type="number" class="form-control" id="price" name="price" value="${book.getPrice()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="stock">Tồn kho</label>
                                    <input type="number" class="form-control" id="stock" name="stock" value="${book.getStock()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="soldQuantity">Số lượng đã bán</label>
                                    <input type="number" class="form-control" id="soldQuantity" name="soldQuantity" value="${book.getSoldQuantity()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="coverImage">Hình bìa</label><br>
                                    <input type="file" class="form-control" id="coverImageFile_${book.getBookID()}" accept="image/*" onchange="uploadImage(this, ${book.getBookID()})"><br>
                                    <input type="hidden" class="form-control" id="coverImage_${book.getBookID()}" name="coverImage" value="${book.getCoverImage()}">
                                    <div class="mt-2" id="imagePreview_${book.getBookID()}"></div>
                                </div>
                                <div class="form-group">
                                    <label>Tác giả</label>
                                    <select class="authors form-control" name="authors" multiple required>
                                        <c:forEach var="author" items="${authorList}">
                                            <option value="${author.authorID}" ${fn:contains(book.authors, ",".concat(author.authorID).concat(",")) ? 'selected' : ''}>${author.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Thể loại</label>
                                    <select class="categories form-control" name="categories" multiple>
                                        <c:forEach var="cate" items="${cateList}">
                                            <option value="${cate.categoryID}" ${fn:contains(book.cates, ",".concat(cate.categoryID).concat(",")) ? 'selected' : ''}>${cate.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="isAvailable">Trạng thái</label>
                                    <select class="form-control" id="isAvailable" name="isAvailable">
                                        <option value="true" ${book.getIsAvailable() ? "selected" : ""}>Hoạt động</option>
                                        <option value="false" ${!book.getIsAvailable() ? "selected" : ""}>Không hoạt động</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Save Changes</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Book Info Modal -->
            <div class="modal fade" id="bookInfoModal_${book.getBookID()}" tabindex="-1" role="dialog" aria-labelledby="bookInfoModalLabel_${book.getBookID()}" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="bookInfoModalLabel_${book.getBookID()}">Book Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <img class="w-100 mb-3" src="${book.getCoverImage()}">
                            <p><strong>ID:</strong> ${book.getBookID()}</p>
                            <p><strong>Tiêu đề:</strong> ${book.getTitle()}</p>
                            <p><strong>Nhà xuất bản:</strong> ${book.getPublisher()}</p>
                            <p><strong>Ngày xuất bản:</strong> ${book.getPublicationDate()}</p>
                            <p><strong>ISBN:</strong> ${book.getISBN()}</p>
                            <p><strong>Giá bán:</strong> ${book.getPrice()}</p>
                            <p><strong>Tồn kho:</strong> ${book.getStock()}</p>
                            <p><strong>Số lượng đã bán:</strong> ${book.getSoldQuantity()}</p>
                            <p><strong>Trạng thái:</strong> ${book.getIsAvailable() ? 'Available' : 'Unavailable'}</p>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>

        <!-- Add Book Modal -->
        <div class="modal fade" id="addBookModal" tabindex="-1" role="dialog" aria-labelledby="addBookModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addBookModalLabel">Add Book</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="productManager" method="post">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="title">Tiêu đề</label>
                                <input type="text" class="form-control" id="title" name="title" required>
                            </div>
                            <div class="form-group">
                                <label for="publisher">Nhà xuất bản</label>
                                <input type="text" class="form-control" id="publisher" name="publisher" required>
                            </div>
                            <div class="form-group">
                                <label for="publicationDate">Ngày xuất bản</label>
                                <input type="text" class="form-control" id="publicationDate" name="publicationDate" required>
                            </div>
                            <div class="form-group">
                                <label for="isbn">ISBN</label>
                                <input type="text" class="form-control" id="isbn" name="isbn" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Giá bán</label>
                                <input type="number" class="form-control" id="price" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">Tồn kho</label>
                                <input type="number" class="form-control" id="stock" name="stock" required>
                            </div>
                            <div class="form-group">
                                <label for="soldQuantity">Số lượng đã bán</label>
                                <input type="number" class="form-control" id="soldQuantity" name="soldQuantity" required>
                            </div>
                            <div class="form-group">
                                <label for="coverImage">Hình bìa</label>
                                <input type="file" class="form-control" id="coverImageFile_0" accept="image/*" onchange="uploadImage(this, 0)">
                                <input type="hidden" class="form-control" id="coverImage_0" name="coverImage" value="">
                                <div class="mt-2" id="imagePreview_0"></div>
                            </div>
                            <div class="form-group">
                                <label>Tác giả</label>
                                <select class="authors form-control" name="authors" multiple>
                                    <c:forEach var="author" items="${authorList}">
                                        <option value="${author.authorID}">${author.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Thể loại</label>
                                <select class="categories form-control" name="categories" multiple>
                                    <c:forEach var="cate" items="${cateList}">
                                        <option value="${cate.categoryID}">${cate.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="isAvailable">Trạng thái</label>
                                <select class="form-control" id="isAvailable" name="isAvailable">
                                    <option value="true">Hoạt động</option>
                                    <option value="false">Không hoạt động</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Book</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="_footerAdmin.jsp"/>

        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- DataTable JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

        <script>
                                    async function uploadImage(input, bookID) {

                                        previewImage(input, bookID)

                                        const apiKey = '82b2f0b4bad5a0f8c43de992fe433a3e'; // Replace with your ImgBB API Key
                                        const file = input.files[0];
                                        if (bookID == 0 && !file) {
                                            alert('Please select a file');
                                            return;
                                        }

                                        const formData = new FormData();
                                        formData.append('image', file);

                                        try {
                                            const response = await fetch('https://api.imgbb.com/1/upload?key=' + apiKey, {
                                                method: 'POST',
                                                body: formData
                                            });
                                            const data = await response.json();

                                            if (data.success) {
                                                const imageUrl = data.data.url;
                                                document.getElementById(coverImage_ + bookID).value = imageUrl;

//                                                const previewContainer = document.getElementById(imagePreview_ + bookID);
//                                                previewContainer.innerHTML = <img src=" + imageUrl + " class="img-fluid" alt="Cover Image">;
                                            } else {
                                                alert('Upload failed: ' + data.error.message);
                                            }
                                        } catch (error) {
                                            alert('Error uploading file');
                                            console.error('Error:', error);
                                        }
                                    }

                                    function previewImage(input, bookID) {
                                    const file = input.files[0];
                                            if (!file) {
                                    return; // No file selected, do nothing
                                    }

                                    const reader = new FileReader();
                                            reader.onload = function (e) {
                                            const imagePreview = document.getElementById(imagePreview_ + bookID);
                                                    imagePreview.innerHTML = <img src=" + e.target.result + " class="img-fluid" alt="Cover Image">;
                                    };
                                    reader.readAsDataURL(file);
                                    }
        </script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                        var selects = document.querySelectorAll('.authors');
                        selects.forEach(function (select) {
                        new Choices(select, {
                        removeItemButton: true,
                                placeholder: true,
                                placeholderValue: 'Select authors',
                                searchPlaceholderValue: 'Search authors',
                        });
                        });
                        selects = document.querySelectorAll('.categories');
                        selects.forEach(function (select) {
                        new Choices(select, {
                        removeItemButton: true,
                                placeholder: true,
                                placeholderValue: 'Select categories',
                                searchPlaceholderValue: 'Search categories',
                        });
                        });
            });
            </script>



        </body>

</html>