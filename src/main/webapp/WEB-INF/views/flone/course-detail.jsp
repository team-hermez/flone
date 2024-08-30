<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
</head>

<body>
<%@ include file="header.jsp" %>

<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="../index.hm">홈</a>
                </li>
                <li class="active">${courseDetail.title}</li>
            </ul>
        </div>
    </div>
</div>
<div class="shop-area pt-100 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6">
                <div class="product-details">
                    <div class="product-details-img">
                        <div class="tab-content jump"> 
                            <div id="shop-details-1" class="tab-pane large-img-style">
                                <c:choose>
                                    <c:when test="${empty courseDetail.courseImage}">
                                        <img src="/assets/img/product-details/large-1.jpg" alt="">
                                        <div class="img-popup-wrap">
                                            <a class="img-popup" href="/assets/img/product-details/b-large-1.jpg"><i class="pe-7s-expand1"></i></a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="../../../../images/${courseDetail.courseImage}" alt="">
                                        <span class="dec-price">-10%</span>
                                        <div class="img-popup-wrap">
                                            <a class="img-popup" href="../../../../images/${courseDetail.courseImage}"><i class="pe-7s-expand1"></i></a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div id="shop-details-2" class="tab-pane active large-img-style">
                                <c:choose>
                                    <c:when test="${empty courseDetail.courseImage}">
                                        <img src="/assets/img/product-details/large-2.jpg" alt="">
                                        <div class="img-popup-wrap">
                                            <a class="img-popup" href="/assets/img/product-details/b-large-2.jpg"><i class="pe-7s-expand1"></i></a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="../../../../images/${courseDetail.courseImage}" alt="">
                                        <div class="img-popup-wrap">
                                            <a class="img-popup" href="../../../../images/${courseDetail.courseImage}"><i class="pe-7s-expand1"></i></a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div id="shop-details-3" class="tab-pane large-img-style">
                                <c:choose>
                                    <c:when test="${empty courseDetail.courseImage}">
                                        <img src="/assets/img/product-details/large-3.jpg" alt="">
                                        <div class="img-popup-wrap">
                                            <a class="img-popup" href="/assets/img/product-details/b-large-3.jpg"><i class="pe-7s-expand1"></i></a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="../../../../images/${courseDetail.courseImage}" alt="">
                                        <div class="img-popup-wrap">
                                            <a class="img-popup" href="../../../../images/${courseDetail.courseImage}"><i class="pe-7s-expand1"></i></a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <div class="product-details-content ml-70">
                    <h2>${courseDetail.title}</h2>
                    <div class="product-details-price">
                        <span>${courseDetail.coursePrice}원</span>
                    </div>
                    <p>${courseDetail.description}</p>
                    <div class="pro-details-list">
                        <ul>
                            <li>${courseDetail.instructorName}</li>
                            <c:forEach items="${courseTime}" var="time">
                            <li>${time.dayOfWeek}</li>
                            <li>${time.startTime} - ${time.endTime}</li>
                            </c:forEach>
                            <li>${courseDetail.startDate} - ${courseDetail.endDate}</li>
                        </ul>
                    </div>

                    <div class="pro-details-quality">
                        <div class="pro-details-cart btn-hover">
                            <a href="/flone/reservation/detail.hm?courseId=${courseDetail.courseId}">예약하기</a>
                        </div>
                        <div class="pro-details-wishlist">
                            <a href="#"><i class="fa fa-heart-o"></i></a>
                        </div>
                        <div class="pro-details-compare">
                            <a href="#"><i class="pe-7s-shuffle"></i></a>
                        </div>
                    </div>
                    <div class="pro-details-meta">
                        <span>Categories :</span>
                        <ul>
                            <li><a href="/flone/course/list.hm?category=subject&subject=${courseDetail.subject}">${courseDetail.subject},</a></li>
                            <li><a href="/flone/course/list.hm?category=instructorName&name=${courseDetail.instructorName}">${courseDetail.instructorName},</a></li>
                            <li><a href="/flone/course/list.hm?category=grade&grade=${courseDetail.grade}">${courseDetail.grade}</a></li>
                        </ul>
                    </div>
                    <div class="pro-details-social">
                        <ul>
                            <li><a href="#">공유하기</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="related-product-area pb-95">
    <div class="container">
        <div class="section-title text-center mb-50">
            <h2>${courseDetail.instructorName}님의 다른 강의</h2>
        </div>
        <div class="related-product-active owl-carousel owl-dot-none">
            <c:choose>
                <c:when test="${empty courseByInstructor}">
                    교사의 다른 강의가 없습니다
                </c:when>
                <c:otherwise>
                    <c:forEach var="anotherCourse" items="${courseByInstructor}">
                        <div class="product-wrap">
                            <div class="product-img">
                                <a href="/flone/course/detail.hm?courseId=${anotherCourse.courseId}">
                                    <c:choose>
                                        <c:when test="${empty anotherCourse.courseImage}">
                                            <img class="default-img" src="/assets/img/product/pro-1.jpg" alt="">
                                            <img class="hover-img" src="/assets/img/product/pro-1-1.jpg" alt="">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="default-img" src="../../../../images/${anotherCourse.courseImage}" alt="">
                                            <img class="hover-img" src="../../../../images/${anotherCourse.courseImage}" alt="">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>

                            <div class="product-content text-center">
                                <h3>
                                    <a href="/flone/course/detail.hm?courseId=${anotherCourse.courseId}">${anotherCourse.title}</a>
                                </h3>
                                <div class="product-rating"></div>
                                <div class="product-price">
                                    <span>${anotherCourse.coursePrice}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<%@ include file="script.jsp"%>

</body>

</html>