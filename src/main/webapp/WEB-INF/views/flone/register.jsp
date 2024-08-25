<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Flone - Minimal eCommerce HTML Template</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="/assets/img/favicon.png">

    <!-- CSS
    ============================================ -->
    <style>
        .login-register-wrapper .login-register-form form input {
            margin-bottom: 0 !important;
        }

        .login-register-wrapper .login-form-container {
            padding: 50px 80px 50px 80px !important;
        }

        .form-group {
            margin-bottom: 0 !important;
        }

        .form-group input {
            display: block;
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        .hide {
            display: none;
        }

        .login-register-tab-list a.active::before {
            display: none;
        }

        .error-container {
            min-height: 20px;
            margin-bottom: 5px;
        }

        .error {
            display: block;
            color: red;
            font-size: 14px;
            min-height: 20px;
            font-weight: bold;
        }

        .active {
            font-size: 25px;
            font-weight: 700;
            margin: 0 20px;
            text-transform: capitalize;
            -webkit-transition: all 0.3s ease 0s;
            transition: all 0.3s ease 0s;
        }

        h5 {
            margin-top: 20px !important;
        }

        .password-check-result {
            font-size: 14px;
            font-weight: bold;
            color: green;
        }

        .password-check-error {
            font-size: 14px;
            font-weight: bold;
            color: red;
        }
    </style>

    <%@ include file="css.jsp" %>
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
                <li class="active">회원가입</li>
            </ul>
        </div>
    </div>
</div>
<div class="login-register-area pt-100 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ms-auto me-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a class="active" href="login.hm"> 로그인 </a>
                        |
                        <a class="active" href="register.hm"> 회원가입 </a>
                    </div>
                    <div id="lg2" class="tab-pane">
                        <div class="login-form-container">
                            <div class="login-register-form">
                            <%--@elvariable id="member" type="org.hm.member.model.Member"--%>
                                <form:form action="register.hm" id="registerForm" method="post" modelAttribute="member">
                                    <fieldset>
                                        <h5>이름</h5>
                                        <form:input path="name" placeholder="이름"/>
                                        <div class="error-container">
                                            <form:errors path="name" cssClass="error"/>
                                        </div>
                                    </fieldset>

                                    <h5>비밀번호</h5>
                                    <div class="form-group">
                                        <div style="position: relative;">
                                            <form:input type="password" id="password" path="password" placeholder="비밀번호"
                                                        style="padding-right: 40px;"/>
                                            <button type="button"
                                                    onclick="toggleVisibilitySelectorPassword('#password', '#iconPassword')"
                                                    style="position: absolute; right: 10px; top: 35%; transform: translateY(-50%); background: none; border: none; cursor: pointer; padding: 0; font-size: 16px;">
                                                <i id="iconPassword" class="fa fa-eye-slash"
                                                   style="font-size: 18px; color: #6c757d;"></i>
                                            </button>
                                            <div class="error-container">
                                                <form:errors path="password" cssClass="error"/>
                                            </div>
                                        </div>
                                    </div>

                                    <h5>이메일</h5>
                                    <form:input type="email" path="email" placeholder="이메일"/>
                                    <div class="error-container">
                                        <form:errors path="email" cssClass="error"/>
                                    </div>

                                    <h5>전화번호</h5>
                                    <form:input path="phone" oninput="formatPhoneNumber(this)" placeholder="전화번호"/>
                                    <div class="error-container">
                                        <form:errors path="phone" cssClass="error"/>
                                    </div>

                                    <div style="margin-top: 20px" class="button-box">
                                        <button type="submit"><span>회원가입</span></button>
                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<%@ include file="script.jsp" %>
<script src="/resources/assets/js/toggleVisibility.js"></script>

<!-- All JS is here
============================================ -->

</body>

</html>