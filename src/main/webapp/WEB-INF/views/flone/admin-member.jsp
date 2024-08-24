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
    <h1> 회원 관리 </h1>
    <canvas id="monthlySignupsChart"></canvas>
    <div class="container">
        <h3 class="cart-page-title">회원 조회</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
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
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper">
                                <div class="cart-shiping-update">
                                    <a href="#">Continue Shopping</a>
                                </div>
                                <div class="cart-clear">
                                    <button>Update Shopping Cart</button>
                                    <a href="#">Clear Shopping Cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>

<script>
    const signupDataJson = '${statisticsJson}';
    const signupData = JSON.parse(signupDataJson);

    const signupLabels = signupData.map(item => item.month);
    const signupCounts = signupData.map(item => item.count);

    const signupCtx = document.getElementById('monthlySignupsChart').getContext('2d');
    new Chart(signupCtx, {
        type: 'bar',
        data: {
            labels: signupLabels,
            datasets: [{
                label: 'Monthly Signups',
                data: signupCounts,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
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
