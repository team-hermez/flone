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
        .hide {
            display: none;
        }

        .login-register-tab-list a.active::before {
            display: none;
        }

        .active {
            font-size: 25px;
            font-weight: 700;
            margin: 0 20px;
            text-transform: capitalize;
            -webkit-transition: all 0.3s ease 0s;
            transition: all 0.3s ease 0s;
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
                    <a href="index.jsp">홈</a>
                </li>
                <li class="active">로그인</li>
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
                        <a class="active" href="/flone/member/login.hm"> 비밀번호 변경 </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div style="padding: 50px 85px 50px 85px;" class="login-form-container">
                                <div class="login-register-form">
                                    <form id="changePasswordForm" action="/flone/member/changepassword.hm" method="post">
                                        <input type="text" name="email" placeholder="이메일">
                                        <input name="phone" id="phone" type="text"
                                               value="010"
                                               placeholder="변경하실 핸드폰 번호를 입력해주세요."
                                               style="flex: 1; padding-right: 40px; border: 1px solid #ced4da; border-radius: 4px;"
                                               oninput="formatPhoneNumber(this)">
                                        <div class="col-lg-12 col-md-12">
                                            <div class="billing-info"
                                                 style="position: relative; display: flex; align-items: center;">
                                                <input id="password" name="passwordConfirm" type="password" placeholder="비밀번호"
                                                       style="flex: 1; padding-right: 40px; border: 1px solid #ced4da; border-radius: 4px;">
                                                <button type="button"
                                                        onclick="toggleVisibilitySelectorPassword('#password', '#iconConfirm')"
                                                        style="position: absolute; right: 10px; top: 30%; transform: translateY(-50%); background: none; border: none; cursor: pointer; padding: 0; font-size: 16px;">
                                                    <i id="iconConfirm" class="fa fa-eye-slash"
                                                       style="font-size: 18px; color: #6c757d;"></i>
                                                </button>
                                            </div>
                                        </div>

                                        <div class="button-box"
                                             style="position: relative; padding: 20px; padding-left: 0px; padding-top: 0px; padding-right: 0px;">
                                            <div style="padding-top: 0px; padding-bottom: 40px;"
                                                 class="login-toggle-btn">
                                            </div>
                                            <button type="submit"><span>변경</span></button>
                                        </div>
                                    </form>
                                </div>
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
<script>
    function formatPhoneNumber(input) {
        let value = input.value.replace(/\D/g, '');

        if (!value.startsWith("010")) {
            value = "010" + value.substring(3);
        }

        if (value.length > 11) {
            value = value.substring(0, 11);
        }

        if (value.length > 3 && value.length <= 7) {
            value = value.replace(/(\d{3})(\d+)/, "$1-$2");
        } else if (value.length > 7) {
            value = value.replace(/(\d{3})(\d{4})(\d+)/, "$1-$2-$3");
        }

        input.value = value;
    }
</script>


<!-- All JS is here
============================================ -->


</body>

</html>