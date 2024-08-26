<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<%@ include file="admin-sidebar.jsp" %>
<%@ include file="admin-header.jsp" %>
<div class="home-sidebar-right">
    <div class="container mt-5">
        <div class="section-title text-center">
            <h2> 예약 및 결제 관리 </h2>
        </div>
        <canvas id="revenueRefundChart"></canvas>
        <div class="section-title text-center mt-5">
            <h2>전체 결제 리스트</h2>
        </div>
        <div class="row mt-5">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="table-content table-responsive cart-table-content ">
                    <table>
                        <thead>
                        <tr>
                            <th>프로필</th>
                            <th>회원번호</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>전화번호</th>
                            <th>가입일</th>
                            <th>삭제</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="member" items="${members}">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="#"><img src="../../../resources/images/no_profile.png"
                                                     width="100px"></a>
                                </td>
                                <td class="product-name"><a href="#">${member.memberId}</a></td>
                                <td class="product-name"><a href="#">${member.name}</a></td>
                                <td class="product-name"><a href="#">${member.email}</a></td>
                                <td class="product-name"><a href="#">${member.phone}</a></td>
                                <td class="product-name">${member.createdAt}</td>
                                <td class="product-remove">
                                    <a href="#"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>

<script>
    const revenueDataJson = '${chartDataJson}';
    const revenueData = JSON.parse(revenueDataJson);

    const revenueLabels = revenueData.map(data => data.month);
    const revenueAmounts = revenueData.map(data => data.revenue);

    const revenueCtx = document.getElementById('revenueRefundChart').getContext('2d');
    new Chart(revenueCtx, {
        type: 'line',
        data: {
            labels: revenueLabels,
            datasets: [{
                label: 'Revenue',
                borderColor: 'red',
                data: revenueAmounts,
                fill: false
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>

<%@ include file="script.jsp" %>
</body>
</html>
