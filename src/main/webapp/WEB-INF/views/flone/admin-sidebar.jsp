<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="home-sidebar-left">
    <div class="logo">
        <a href="index.html">
            <img alt="" src="/assets/img/logo/logo.png">
        </a>
    </div>
    <div class="header-right-wrap">
        <div class="same-style header-search">
            <a class="search-active" href="#"><i class="pe-7s-search"></i></a>
            <div class="search-content">
                <form action="#">
                    <input type="text" placeholder="Search"/>
                    <button class="button-search"><i class="pe-7s-search"></i></button>
                </form>
            </div>
        </div>
        <div class="same-style account-satting">
            <a class="account-satting-active" href="#"><i class="pe-7s-user-female"></i></a>
            <div class="account-dropdown">
                <ul>
                    <li><a href="login-register.html">Login</a></li>
                    <li><a href="login-register.html">Register</a></li>
                    <li><a href="wishlist.html">Wishlist </a></li>
                    <li><a href="my-account.html">my account</a></li>
                </ul>
            </div>
        </div>
        <div class="same-style header-wishlist">
            <a href="wishlist.html"><i class="pe-7s-like"></i></a>
        </div>
        <div class="same-style cart-wrap">
            <button class="icon-cart">
                <i class="pe-7s-shopbag"></i>
                <span class="count-style">02</span>
            </button>
            <div class="shopping-cart-content">
                <ul>
                    <li class="single-shopping-cart">
                        <div class="shopping-cart-img">
                            <a href="#"><img alt="" src="/assets/img/cart/cart-1.png"></a>
                        </div>
                        <div class="shopping-cart-title">
                            <h4><a href="#">T- Shart &amp; Jeans </a></h4>
                            <h6>Qty: 02</h6>
                            <span>$260.00</span>
                        </div>
                        <div class="shopping-cart-delete">
                            <a href="#"><i class="fa fa-times-circle"></i></a>
                        </div>
                    </li>
                    <li class="single-shopping-cart">
                        <div class="shopping-cart-img">
                            <a href="#"><img alt="" src="/assets/img/cart/cart-2.png"></a>
                        </div>
                        <div class="shopping-cart-title">
                            <h4><a href="#">T- Shart &amp; Jeans </a></h4>
                            <h6>Qty: 02</h6>
                            <span>$260.00</span>
                        </div>
                        <div class="shopping-cart-delete">
                            <a href="#"><i class="fa fa-times-circle"></i></a>
                        </div>
                    </li>
                </ul>
                <div class="shopping-cart-total">
                    <h4>Shipping : <span>$20.00</span></h4>
                    <h4>Total : <span class="shop-total">$260.00</span></h4>
                </div>
                <div class="shopping-cart-btn btn-hover text-center">
                    <a class="default-btn" href="cart-page.html">view cart</a>
                    <a class="default-btn" href="checkout.html">checkout</a>
                </div>
            </div>
        </div>
    </div>
    <div class="sidebar-menu">
        <nav>
            <ul>
                <li><a href="/flone/admin/main.hm"> MAIN </a></li>
                <li><a href="/flone/admin/manage-member.hm"> 회원관리</a></li>
                <li><a href="/flone/admin/instructor/manage-instructor.hm"> 강사관리 <span><i class="fa fa-angle-right"></i></span></a>
                    <ul class="submenu">
                        <li><a href="/flone/admin/instructor/manage-instructor.hm">강사 조회</a></li>
                        <li><a href="/flone/admin/instructor/request-register.hm">강사 등록 요청</a></li>
                    </ul>
                </li>
                <li><a href="/flone/admin/manage-course.hm"> 강의관리<span><i class="fa fa-angle-right"></i></span></a>
                    <ul class="submenu">
                        <li><a href="/flone/admin/course/manage-course.hm?type=1">예약중 강의</a></li>
                        <li><a href="/flone/admin/course/manage-course.hm?type=2">진행중 강의</a></li>
                        <li><a href="/flone/admin/course/manage-course.hm?type=3">끝난 강의</a></li>
                    </ul>
                </li>
                <li><a href="/flone/admin/payment/manage-payment.hm">예약관리<span><i class="fa fa-angle-right"></i></span></a>
                    <ul class="submenu">
                        <li><a href="/flone/admin/payment/manage-payment.hm">예약 및 결제</a></li>
                        <li><a href="/flone/admin/payment/manage-refund.hm">환불 내역</a></li>
                    </ul>
                </li>
                <li><a href="contact.html"> About</a></li>
                <li><a href="contact.html"> Contact</a></li>
            </ul>
        </nav>
    </div>
    <div class="sidebar-copyright">
        <p>©2020 <a href="#">Flone</a>.All Rights Reserved.</p>
    </div>
</div>