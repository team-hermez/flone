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
    <div class="checkout-area pt-95 pb-100">
        <div class="container">
            <div class="col-lg-12">
                <div class="your-order-area">
                    <h3>Your order</h3>
                    <div class="your-order-wrap gray-bg-4">
                        <div class="your-order-product-info">
                            <div class="your-order-top">
                                <ul>
                                    <li>강의명: ${paymentDetail.title}</li>
                                    <c:choose>
                                        <c:when test="${not empty paymentDetail.cancelAt}">
                                            <li>환불 금액: ${paymentDetail.paymentAmount} 원</li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>결제 금액: ${paymentDetail.paymentAmount} 원</li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                            <div class="your-order-middle">
                                <ul>
                                    <li><span
                                            class="order-middle-left">강사님: ${courseDetailList.instructorName}</span>
                                    </li>
                                </ul>
                                <ul>
                                    <li><span
                                            class="order-middle-left">강의설명: ${paymentDetail.description}</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="your-order-bottom">
                                <ul>
                                    <li><strong>&lt 결제정보 &gt</strong>
                                    </li>
                                </ul>
                                <ul>
                                    <li><span class="order-middle-left">결제일 : </span>
                                        <span class="order-price">${paymentDetail.createdAt}</span>
                                    </li>
                                </ul>
                                <ul>
                                    <c:if test="${not empty paymentDetail.cancelAt}">
                                        <li><span class="order-middle-left">취소일 : </span>
                                            <span class="order-price">${paymentDetail.cancelAt}</span>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                            <div class="your-order-bottom">
                                <ul>
                                    <li><strong>&lt 강의정보 &gt</strong>
                                    </li>
                                </ul>
                                <ul>
                                    <li><span class="order-middle-left">강의일자 : </span><span
                                            class="order-price">${paymentDetail.startDate}- ${paymentDetail.endDate}</span>
                                    </li>
                                </ul>
                                <ul>
                                    <li>강의시간:</li>
                                </ul>
                                <c:forEach items="${courseTimeList}" var="time">
                                    <ul>
                                        <li>${time.dayOfWeek} ${time.startTime} - ${time.endTime}</li>
                                    </ul>
                                </c:forEach>
                                <ul>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>
</div>
<%@ include file="chart-signup.jsp" %>
<%@ include file="script.jsp" %>
</body>
</html>
