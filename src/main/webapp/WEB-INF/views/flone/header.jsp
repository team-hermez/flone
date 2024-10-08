<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html class="no-js" lang="zxx">
<head>
    <%@ include file="css.jsp" %>
</head>
<body>
<header class="header-area header-in-container clearfix">

    <div class="header-bottom sticky-bar header-res-padding">
        <div class="container">
            <div class="row">
                <div class="col-xl-2 col-lg-2 col-md-6 col-4">
                    <div class="logo">
                        <a href="/">
                            <img alt="" src="/assets/img/logo/logo-3.png" style="
                                width: 101%;
                                margin-top: -19px;
                            ">
                        </a>
                    </div>
                </div>
                <div class="col-xl-8 col-lg-8 d-none d-lg-block">
                    <div class="main-menu">
                        <nav>
                            <ul>
                                <li><a href="/">홈</a>
                                </li>
                                <li><a href="/flone/course/list.hm"> 강의 <i class="fa fa-angle-down"></i> </a>
                                    <ul class="mega-menu mega-menu-padding">
                                        <li>
                                            <ul>
                                                <li class="mega-menu-title"><a href="/flone/course/list.hm">카테고리</a>
                                                </li>
                                                <li><a href="/flone/course/list.hm?category=subject&subject=국어">국어</a>
                                                </li>
                                                <li><a href="/flone/course/list.hm?category=subject&subject=영어">영어</a>
                                                </li>
                                                <li><a href="/flone/course/list.hm?category=subject&subject=수학">수학</a>
                                                </li>
                                                <li><a href="/flone/course/list.hm?category=subject&subject=과학">과학</a>
                                                </li>
                                                <li><a href="/flone/course/list.hm?category=subject&subject=사회">사회</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.INSTRUCTOR}">
                                        <li>
                                            <a href="#">강의 등록 <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li><a href="/flone/course/list.hm">내 강의</a></li>
                                                <li><a href="/flone/course/register.hm">강의 등록</a></li>
                                            </ul>
                                        </li>
                                    </c:when>
                                </c:choose>
                                <li><a href="/flone/instructor/list.hm">강사</a></li>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.MEMBER}">
                                        <li>
                                            <a href="#">내 정보 <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li>
                                                    <a href="/flone/member/my-account.hm?memberId=${sessionScope.MEMBER.memberId}">마이페이지</a>
                                                </li>
                                                <li><a href="/flone/reservation/reserved-course-list.hm">나의 강의</a></li>
                                                <li><a href="/flone/reservation/list.hm">예약 및 결제</a></li>
                                            </ul>
                                        </li>
                                    </c:when>
                                </c:choose>

                                <li>
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.ADMIN}">
                                            <a href="/flone/admin/main.hm">관리자</a>
                                        </c:when>
                                    </c:choose>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-xl-2 col-lg-2 col-md-6 col-8">
                    <div class="header-right-wrap">
                        <div class="same-style account-satting">
                            <a class="account-satting-active" href="#"><i class="pe-7s-user-female"></i></a>
                            <div class="account-dropdown">
                                <ul>
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.MEMBER}">
                                            <li>
                                                <a href="/flone/member/my-account.hm?memberId=${sessionScope.MEMBER.memberId}">마이페이지</a>
                                            </li>
                                            <li><a href="/flone/member/logout.hm">로그아웃</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="/flone/member/login.hm">로그인</a></li>
                                            <li><a href="/flone/member/register.hm">회원가입</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mobile-menu-area">
                <div class="mobile-menu">
                    <nav id="mobile-menu-active">
                        <ul class="menu-overflow">
                            <li><a href="index.jsp">HOME</a>
                                <ul>
                                    <li><a href="#">Demo Group 01</a>
                                        <ul>
                                            <li><a href="index.jsp">Home 1 – Fashion</a></li>
                                            <li><a href="index-2.html">Home 2 – Fashion</a></li>
                                            <li><a href="index-3.html">Home 3 – Fashion</a></li>
                                            <li><a href="index-4.html">Home 4 – Fashion</a></li>
                                            <li><a href="index-5.html">Home 5 – Fashion</a></li>
                                            <li><a href="index-6.html">Home 6 – Fashion</a></li>
                                            <li><a href="index-7.html">Home 7 – Fashion</a></li>
                                            <li><a href="index-8.html">Home 8 – Minimal</a></li>
                                            <li><a href="index-9.html">Home 9 – Electronics</a></li>
                                            <li><a href="index-10.html">Home 10 – Furniture</a></li>
                                            <li><a href="index-11.html">Home 11 - showcase slider</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Demo Group 02</a>
                                        <ul>
                                            <li><a href="index-12.html">Home 12 – Plants</a></li>
                                            <li><a href="index-13.html">Home 13 – Cosmetic</a></li>
                                            <li><a href="index-14.html">Home 14 – Christmas</a></li>
                                            <li><a href="index-15.html">Home 15 – Fruit</a></li>
                                            <li><a href="index-16.html">Home 16 – Black Friday</a></li>
                                            <li><a href="index-17.html">Home 17 – Flower</a></li>
                                            <li><a href="index-18.html">Home 18 – Book</a></li>
                                            <li><a href="index-19.html">Home 19 – Fashion</a></li>
                                            <li><a href="index-20.html">Home 20 – Electronics</a></li>
                                            <li><a href="index-21.html">Home 21 – Furniture</a></li>
                                            <li><a href="index-22.html">Home 22 – Handmade</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Demo Group 03</a>
                                        <ul>
                                            <li><a href="index-23.html">Home 23 – Organic</a></li>
                                            <li><a href="index-24.html">Home 24 – Pet Food</a></li>
                                            <li><a href="index-25.html">Home 25 – Auto Parts</a></li>
                                            <li><a href="index-26.html">Home 26 – Cake Shop</a></li>
                                            <li><a href="index-27.html">Home 27 – Kids Fashion</a></li>
                                            <li><a href="index-28.html">Home 28 – Book Shop</a></li>
                                            <li><a href="index-29.html">Home 29 – Flower Shop</a></li>
                                            <li><a href="index-30.html">Home 30 – Instagram</a></li>
                                            <li><a href="index-31.html">Home 31 – Black Friday</a></li>
                                            <li><a href="index-32.html">Home 32 – Valentine Day</a></li>
                                            <li><a href="index-33.html">Home 33 – Medical Equipment</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="shop.html">Shop</a>
                                <ul>
                                    <li><a href="#">shop layout</a>
                                        <ul>
                                            <li><a href="shop.html">standard style</a></li>
                                            <li><a href="shop-filter.html">Grid filter style</a></li>
                                            <li><a href="shop-grid-2-col.html">Grid 2 column</a></li>
                                            <li><a href="shop-no-sidebar.html">Grid No sidebar</a></li>
                                            <li><a href="shop-grid-fw.html">Grid full wide </a></li>
                                            <li><a href="shop-right-sidebar.html">Grid right sidebar</a></li>
                                            <li><a href="shop-list.html">list 1 column box </a></li>
                                            <li><a href="shop-list-fw.html">list 1 column full wide </a></li>
                                            <li><a href="shop-list-fw-2col.html">list 2 column full wide</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">product details</a>
                                        <ul>
                                            <li><a href="course-detail.jsp">tab style 1</a></li>
                                            <li><a href="product-details-2.html">tab style 2</a></li>
                                            <li><a href="product-details-3.html">tab style 3</a></li>
                                            <li><a href="product-details-4.html">sticky style</a></li>
                                            <li><a href="product-details-5.html">gallery style </a></li>
                                            <li><a href="product-details-slider-box.html">Slider style</a></li>
                                            <li><a href="product-details-affiliate.html">affiliate style</a></li>
                                            <li><a href="product-details-6.html">fixed image style </a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="shop.html">Collection</a></li>
                            <li><a href="#">Pages</a>
                                <ul>
                                    <li><a href="about.html">about us</a></li>
                                    <li><a href="reservation-list.jsp">cart page</a></li>
                                    <li><a href="payment-detail.jsp">checkout </a></li>
                                    <li><a href="wishlist.html">wishlist </a></li>
                                    <li><a href="my-account.hm">마이페이지</a></li>
                                    <li><a href="login.hm">로그인 / 회원가입 </a></li>
                                    <li><a href="contact.jsp">contact us </a></li>
                                    <li><a href="404.html">404 page </a></li>
                                </ul>
                            </li>
                            <li><a href="blog.jsp">Blog</a>
                                <ul>
                                    <li><a href="blog.jsp">blog standard</a></li>
                                    <li><a href="course-list.jsp">blog no sidebar</a></li>
                                    <li><a href="blog-right-sidebar.html">blog right sidebar</a></li>
                                    <li><a href="blog-details.html">blog details 1</a></li>
                                    <li><a href="blog-details-2.html">blog details 2</a></li>
                                    <li><a href="blog-details-3.html">blog details 3</a></li>
                                </ul>
                            </li>
                            <li><a href="about.html">About us</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
