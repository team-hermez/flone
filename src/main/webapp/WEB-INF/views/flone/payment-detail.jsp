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
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">Checkout</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pt-95 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-lg-7">
                <div class="billing-info-wrap">
                    <h3>Billing Details</h3>
                    <div class="row">
                        <div class="your-order-wrap gray-bg-4">
                            <div id="img-div">
                                <img id="reservation-course-img"
                                     src="https://www.sputnik.kr/article_img/202203/article_1648201686.jpg"
                                     alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="your-order-area">
                    <h3>Your order</h3>
                    <div class="your-order-wrap gray-bg-4">
                        <div class="your-order-product-info">
                            <div class="your-order-top">
                                <ul>
                                    <li>강의명</li>
                                    <li>가격</li>
                                </ul>
                            </div>
                            <div class="your-order-middle">
                                <ul>
                                    <li><span class="order-middle-left"></span> <span
                                            class="order-price"></span></li>
                                    <li><span class="order-middle-left">${reserveForm.title}</span>
                                        <span class="order-price">${reserveForm.coursePrice} 원 </span>
                                    </li>
                                </ul>
                            </div>
                            <div class="your-order-bottom">
                                <ul>
                                    <li>${reserveForm.startDate} ~ ${reserveForm.endDate}</li>
                                </ul>
                            </div>
                            <div class="product-details-content ml-70">
                                <ul>
                                    <li class="your-order-shipping">강의시간</li>
                                    <li>${reserveForm.instructorName}</li>
                                    <c:forEach items="${courseTime}" var="time">
                                        <li>${time.dayOfWeek}</li>
                                        <li>${time.startTime} - ${time.endTime}</li>
                                    </c:forEach>
                                    <li>${courseDetail.startDate} - ${courseDetail.endDate}</li>
                                </ul>
                            </div>
                            <div class="payment-method">
                                <div class="payment-accordion element-mrg">
                                    <div class="panel-group" id="accordion">
                                        <div class="panel payment-accordion">
                                            <div class="panel-heading" id="method-one">
                                                <h4 class="panel-title">
                                                    <a data-bs-toggle="collapse" href="#method1">
                                                        강의내용
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="method1" class="panel-collapse collapse show"
                                                 data-bs-parent="#accordion">
                                                <div class="panel-body">
                                                    <p>${reserveForm.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
</div>
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>