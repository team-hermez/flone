<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
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
            <c:forEach items="${instructors}" var ="instructors">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="team-wrapper mb-30">
                    <div class="team-img">
                        <a href="#">
                            <img src="/assets/img/team/team-1.jpg" alt="">
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
                        <h4>${instructors.instructorId}</h4>
                        <span>${instructors.instructorDescription} </span>
                    </div>
                </div>
            </div>
            </c:forEach>
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