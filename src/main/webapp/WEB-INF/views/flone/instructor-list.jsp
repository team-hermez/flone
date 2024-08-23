<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
    <style>
        .row .billing-info-wrap {
            flex-shrink: 0;
            width: 7.7%;
            bottom: 33.5px;
            max-width: 100%;
            padding-right: calc(var(--bs-gutter-x)* .5);
            padding-left: calc(var(--bs-gutter-x)* .5);
            margin-top: var(--bs-gutter-y);
        }

        .billing-info-wrap {
            position: fixed;
            bottom: 33px;
            right: 65px;
            z-index: 1000;
            max-width: 100%; /* 필요 시 max-width 추가 */
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<body>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.html">Home</a>
                </li>
                <li class="active">About us </li>
            </ul>
        </div>
    </div>
</div>

<div class="team-area pt-95 pb-70">
    <div class="container">
        <div class="section-title-2 text-center mb-60">
            <h2>강사진</h2>
            <p>완전 짱짱 강사진들</p>
        </div>
        <div class="row">
            <c:forEach var ="instructors" items="${instructors.contents}">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="team-wrapper mb-30">
                    <div class="team-img">
                        <a href="#">
                            <img src="/assets/img/team/team-1.jpg" href="/flone/instructors/detail.hm?instructorsId=${instructors.instructorId}">
                        </a>
                        <div class="team-action">
                            <a class="facebook" href="#">
                                <i class="fa fa-facebook"></i>
                            </a>
                            <a class="twitter" title="Wishlist" href="#">
                                <i class="fa fa-twitter"></i>
                            </a>
                            <a class="instagram" href="#">
                                <i class="fa fa-instagram"></i>
                            </a>
                        </div>
                    </div>
                    <div class="team-content text-center">
                        <h4>${instructors.name}</h4>
                        <span>${instructors.instructorDescription} </span>
                    </div>
                </div>
            </div>
                <div class = "billing-info-wrap">
                    <div class="checkout-account-toggle open-toggle2 mb-30">
                        <button class="btn-hover checkout-btn" type="button" onclick="location.href = '/flone/instructor/register.hm'"><span>register</span></button>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pro-pagination-style text-center mt-20">
            <ul>
                <%--                        Prev Page--%>
                <c:choose>
                    <c:when test="${instructors.currentPage > 1}">
                        <li><a class="prev" href="?page=${instructors.currentPage - 1}"><i class="fa fa-angle-double-left"></i></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                    </c:otherwise>
                </c:choose>
                <%--                        Current Page--%>
                <c:forEach begin="1" end="${instructors.totalPages}" var="pageNum">
                    <c:choose>
                        <c:when test="${pageNum == instructors.currentPage}">
                            <li><a class="active" href="#">${pageNum}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="?page=${pageNum}">${pageNum}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <%--                        Next Page--%>
                <c:choose>
                    <c:when test="${instructors.currentPage<instructors.totalPages}">
                        <li><a class="next" href="?page=${instructors.currentPage + 1}"><i class="fa fa-angle-double-right"></i></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a class="next" href="#"><i class="fa fa-angle-double-right"></i></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
<div class="brand-logo-area pb-100 about-brand-logo">
    <div class="container">
        <div class="brand-logo-active owl-carousel owl-dot-none">
            <div class="single-brand-logo">
                <img src="/assets/img/brand-logo/barnd-logo-1.png" alt="">
            </div>
            <div class="single-brand-logo">
                <img src="/assets/img/brand-logo/barnd-logo-2.png" alt="">
            </div>
            <div class="single-brand-logo">
                <img src="/assets/img/brand-logo/barnd-logo-3.png" alt="">
            </div>
            <div class="single-brand-logo">
                <img src="/assets/img/brand-logo/barnd-logo-4.png" alt="">
            </div>
            <div class="single-brand-logo">
                <img src="/assets/img/brand-logo/barnd-logo-5.png" alt="">
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>

<!-- All JS is here
============================================ -->

<%@ include file="script.jsp"%>

</body>

</html>