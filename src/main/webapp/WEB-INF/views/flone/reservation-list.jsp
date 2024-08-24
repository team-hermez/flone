<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx" xmlns:th="http://www.thymeleaf.org">
<head>
    <%@ include file="css.jsp" %>
    <script src="/resources/assets/js/reservation.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">Cart Page</li>
            </ul>
        </div>
    </div>
</div>
<div class="cart-main-area pt-90 pb-100">
    <div class="container">
        <h3 class="cart-page-title">강의 예약 목록</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>예약 번호</th>
                                <th>강의 명</th>
                                <th>강의 시작일</th>
                                <th>가격</th>
                                <th>결제상태</th>
                                <th>
                                    <button type="button" id="selectAllBtn">모두선택</button>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="reservation" items="${reservationPage.contents}">
                                <tr>
                                    <td class="product-thumbnail">
                                        <a href="reservation-detail.hm?courseId=${reservation.courseId}">${reservation.merchantUid}</a>
                                    </td>
                                    <td class="product-name"><a href="#">${reservation.title}</a>
                                    </td>
                                    <td class="product-price-cart">${reservation.startDate}</td>
                                    <td class="product-subtotal">${reservation.paymentAmount}</td>
                                    <td class="product-remove">
                                        <c:if test="${reservation.reservationStatusId eq 1}">
                                            <a href="#"><i class="fa fa-pencil">예약완료</i></a>
                                        </c:if>
                                        <c:if test="${reservation.reservationStatusId eq 2}">
                                            <a href="#"><i class="fa fa-pencil">예약취소</i></a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${reservation.reservationStatusId eq 1}">
                                            <input type="checkbox" class="checkList"
                                                   value="${reservation.merchantUid}">
                                        </c:if>
                                        <c:if test="${reservation.reservationStatusId eq 2}">
                                            <input type="checkbox"
                                                   value="${reservation.merchantUid}" disabled>
                                        </c:if>
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
                                </div>
                                <div class="cart-clear">
                                    <a href="#" id="printBtn">취소</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pro-pagination-style text-center mt-20">
                        <div>
                            <ul>
                                <%--                        Prev Page--%>
                                <c:choose>
                                    <c:when test="${reservationPage.currentPage > 1}">
                                        <li><a class="prev"
                                               href="?page=${reservationPage.currentPage - 1}"><i
                                                class="fa fa-angle-double-left"></i></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="prev" href="#"><i
                                                class="fa fa-angle-double-left"></i></a></li>
                                    </c:otherwise>
                                </c:choose>
                                <%--                        Current Page--%>
                                <c:forEach begin="1" end="${reservationPage.totalPages}"
                                           var="pageNum">
                                    <c:choose>
                                        <c:when test="${pageNum == reservationPage.currentPage}">
                                            <li><a class="active" href="#">${pageNum}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <%--                        Next Page--%>
                                <c:choose>
                                    <c:when test="${reservationPage.currentPage < reservationPage.totalPages}">
                                        <li><a class="next"
                                               href="?page=${reservationPage.currentPage + 1}"><i
                                                class="fa fa-angle-double-right"></i></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="next" href="#"><i
                                                class="fa fa-angle-double-right"></i></a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </form>
                <script src="/resources/assets/js/cancelpay.js"></script>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>