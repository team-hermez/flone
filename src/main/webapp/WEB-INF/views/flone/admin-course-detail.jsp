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
                                            <span class="dec-price">-10%</span>
                                            <div class="img-popup-wrap">
                                                <a class="img-popup" href="/assets/img/product-details/b-large-1.jpg"><i
                                                        class="pe-7s-expand1"></i></a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="../../../../images/${courseDetail.courseImage}" alt="">
                                            <span class="dec-price">-10%</span>
                                            <div class="img-popup-wrap">
                                                <a class="img-popup"
                                                   href="../../../../images/${courseDetail.courseImage}"><i
                                                        class="pe-7s-expand1"></i></a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="shop-details-2" class="tab-pane active large-img-style">
                                    <c:choose>
                                        <c:when test="${empty courseDetail.courseImage}">
                                            <img src="/assets/img/product-details/large-2.jpg" alt="">
                                            <span class="dec-price">-10%</span>
                                            <div class="img-popup-wrap">
                                                <a class="img-popup" href="/assets/img/product-details/b-large-2.jpg"><i
                                                        class="pe-7s-expand1"></i></a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="../../../../images/${courseDetail.courseImage}" alt="">
                                            <span class="dec-price">-10%</span>
                                            <div class="img-popup-wrap">
                                                <a class="img-popup"
                                                   href="../../../../images/${courseDetail.courseImage}"><i
                                                        class="pe-7s-expand1"></i></a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <div id="shop-details-3" class="tab-pane large-img-style">
                                    <c:choose>
                                        <c:when test="${empty courseDetail.courseImage}">
                                            <img src="/assets/img/product-details/large-3.jpg" alt="">
                                            <span class="dec-price">-10%</span>
                                            <div class="img-popup-wrap">
                                                <a class="img-popup" href="/assets/img/product-details/b-large-3.jpg"><i
                                                        class="pe-7s-expand1"></i></a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="../../../../images/${courseDetail.courseImage}" alt="">
                                            <span class="dec-price">-10%</span>
                                            <div class="img-popup-wrap">
                                                <a class="img-popup"
                                                   href="../../../../images/${courseDetail.courseImage}"><i
                                                        class="pe-7s-expand1"></i></a>
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
                            <span class="old">[할인가격]1000원 (할인 가격 보류)</span>
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
                                <a href="/flone/reservation/detail.hm?courseId=${courseDetail.courseId}">강의삭제</a>
                            </div>
                        </div>
                        <div class="pro-details-meta">
                            <ul>
                                <li>
                                    <a href="/flone/course/list.hm?category=subject&subject=${courseDetail.subject}">${courseDetail.subject},</a>
                                </li>
                                <li>
                                    <a href="/flone/course/list.hm?category=instructorName&name=${courseDetail.instructorName}">${courseDetail.instructorName},</a>
                                </li>
                                <li>
                                    <a href="/flone/course/list.hm?category=grade&grade=${courseDetail.grade}">${courseDetail.grade}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>

</div>
<%@ include file="script.jsp" %>
</body>
</html>
