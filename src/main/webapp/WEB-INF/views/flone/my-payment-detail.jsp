<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <style>
      #reservation-course-img {
        width: 100%;
        height: 100%;
      }

      li {
        list-style: none;
      }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="/flone/index.hm">홈</a>
                </li>
                <li class="active">결제 상세</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pt-95 pb-100">
    <div class="container">
        <div class="col-lg-12">
            <div class="your-order-area">
                <h3>Your order</h3>
                <div class="your-order-wrap gray-bg-4">
                    <div class="your-order-product-info">
                        <div class="your-order-top">
                            <ul>
                                <li>강의명: ${myPaymentDetail.title}</li>
                                <c:choose>
                                    <c:when test="${not empty myPaymentDetail.cancelAt}">
                                        <li>환불 금액: ${myPaymentDetail.paymentAmount} 원</li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>결제 금액: ${myPaymentDetail.paymentAmount} 원</li>
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
                                        class="order-middle-left">강의설명: ${myPaymentDetail.description}</span>
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
                                    <span class="order-price">${myPaymentDetail.createdAt}</span>
                                </li>
                            </ul>
                            <ul>
                                <c:if test="${not empty myPaymentDetail.cancelAt}">
                                    <li><span class="order-middle-left">취소일 : </span>
                                        <span class="order-price">${myPaymentDetail.cancelAt}</span>
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
                                        class="order-price">${myPaymentDetail.startDate}- ${myPaymentDetail.endDate}</span>
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
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>