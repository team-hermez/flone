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
    </style>
    <%@ include file="refund-payment.jsp" %>
    <script>
      function clickPay() {
        requestPay("${reserveForm.merchantUid}", "${reserveForm.title}", ${reserveForm.coursePrice},
            "${reserveForm.memberEmail}", "${reserveForm.memberName}", "${reserveForm.memberPhone}",
            "${reserveForm.courseId}")
      }
    </script>
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
                                <li>강의명: ${reserveForm.title}</li>
                                <li>가격: ${reserveForm.coursePrice} 원</li>
                            </ul>
                        </div>
                        <div class="your-order-middle">
                            <ul>
                                <li class="your-order-shipping">강사님: ${courseDetailList.instructorName}</li>
                            </ul>
                            <ul>
                                <li class="your-order-shipping">강의설명: ${courseDetailList.description}</li>
                            </ul>
                        </div>
                        <div class="your-order-bottom">
                            <ul>
                                <li class="your-order-shipping">강의시간</li>
                            </ul>
                            <ul>
                            <li>${reserveForm.startDate} - ${reserveForm.endDate}</li>
                            </ul>
                            <c:forEach items="${courseTime}" var="time">
                                <ul>
                                    <li>${time.dayOfWeek} ${time.startTime} - ${time.endTime}</li>
                                </ul>
                            </c:forEach>
                        </div>
                        <div class="Place-order mt-25">
                            <a class="btn-hover" id="pay-course" onclick="clickPay()">결제</a>
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