<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Quản lý doanh thu</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>

    <body>
        <c:choose>
            <c:when test="${empty sessionScope.account.getRoleID() || sessionScope.account.getRoleID() == 3 || sessionScope.account.getRoleID() == 2}">
                <jsp:include page="admin401View.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="_headerAdmin.jsp"/>

                <section class="section-content">
                    <div class="container">
                        <c:if test="${not empty sessionScope.successMessage}">
                            <div class="alert alert-success mb-0 mt-4" role="alert">
                                ${sessionScope.successMessage}
                            </div>
                        </c:if>
                        <c:if test="${not empty sessionScope.errorMessage}">
                            <div class="alert alert-danger mb-0 mt-4" role="alert">
                                ${sessionScope.errorMessage}
                            </div>
                        </c:if>
                        <c:remove var="successMessage" scope="session"/>
                        <c:remove var="errorMessage" scope="session"/>

                        <header class="section-heading py-4 d-flex justify-content-between">
                            <h3 class="section-title">Quản lý doanh thu</h3>
                        </header>

                        <!-- Form tìm kiếm theo năm -->
                        <div class="row mb-4">
                            <div class="col-md-6">
                                <form action="searchRevenust" method="post">
                                    <div class="input-group mb-3">
                                        <label for="year" class="input-group-text">Năm:</label>
                                        <input type="text" id="year" name="year" value="${requestScope.year}" class="form-control" placeholder="Nhập năm" required>
                                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                    </div>
                                </form>
                                <span>${requestScope.mess}</span>
                            </div>
                        </div>

                        <!-- Biểu đồ doanh thu -->

                        <c:if test="${not empty totalRevenue}">
                            <div class="alert alert-info mb-0 mt-4" role="alert">
                                Tổng doanh thu năm ${requestScope.year}: 
                                <fmt:formatNumber value="${totalRevenue}" type="currency"/>
                            </div>
                            <div class="mb-5">
                                <canvas id="revenueChart"></canvas>
                            </div>
                        </c:if>
                    </div>
                </section>

                <jsp:include page="_footerAdmin.jsp"/>
            </c:otherwise>
        </c:choose>

        <script>
            document.addEventListener('DOMContentLoaded', (event) => {
                const ctx = document.getElementById('revenueChart').getContext('2d');

                // Generate revenue data for 12 months with default value 0
                const revenueData = {
                    labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                    datasets: [{
                            label: 'Doanh thu',
                            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                };

                // Create an array to hold the revenue objects for debugging
                const revenueList = [];

                // Update revenue data with actual values from requestScope
            <c:forEach var="c" items="${reven}">
                revenueList.push({
                    month: ${c.month},
                    year: ${c.year},
                    totalRevenue: ${c.totalRevenue}
                });
                revenueData.datasets[0].data[${c.month - 1}] = ${c.totalRevenue};
            </c:forEach>;

                // Log the revenueList to the console for debugging
                console.log('Revenue data from server:', revenueList);

                const revenueChart = new Chart(ctx, {
                    type: 'line', // Use 'line' for line chart
                    data: revenueData,
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            });
        </script>
    </body>

</html>
