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
        <div class="w-75 p-3 mx-auto">
            <div>
                <canvas id="revenueRefundChart"></canvas>
            </div>
        </div>
        <div class="section-title text-center mt-5">
            <h2>전체 결제 리스트</h2>
        </div>
        <div class="row mt-5">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="table-content table-responsive cart-table-content ">
                    <table>
                        <thead>
                        <tr>
                            <th>주문번호</th>
                            <th>강의제목</th>
                            <th>결제금액</th>
                            <th>결제일</th>
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${reservations.contents}">
                            <tr>
                                <td class="product-name">${reservation.merchantUid}</td>
                                <td class="product-name">${reservation.merchantUid}</td>
                                <td class="product-name">${reservation.paymentAmount}</td>
                                <td class="product-name">${reservation.createdAt}</td>
                                <td class="product-name">${reservation.isBefore}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <ul>
                        <c:choose>
                            <c:when test="${reservations.currentPage > 1}">
                                <li><a class="prev" href="?page=${reservations.currentPage - 1}"><i
                                        class="fa fa-angle-double-left"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${reservations.totalPages}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum == reservations.currentPage}">
                                    <li><a class="active" href="#">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${reservations.currentPage < reservations.totalPages}">
                                <li><a class="next" href="?page=${reservations.currentPage + 1}"><i
                                        class="fa fa-angle-double-right"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="next" href="#"><i class="fa fa-angle-double-right"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>
<%@ include file="chart-payment.jsp" %>
<%@ include file="script.jsp" %>
</body>
</html>
