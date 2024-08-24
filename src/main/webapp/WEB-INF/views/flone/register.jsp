<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
       .error{
           color:red;
           font-weight: bold;
       }

       .active{
           font-size: 25px;
           font-weight: 700;
           margin: 0 20px;
           text-transform: capitalize;
           -webkit-transition: all 0.3s ease 0s;
           transition: all 0.3s ease 0s;
       }
   </style>

    <%@ include file="css.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">회원가입 </li>
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
                        <a class="active" href = "login.hm"> 로그인 </a>
                        |
                        <a class="active" href = "register.hm"> 회원가입 </a>
                    </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <%--@elvariable id="member" type="org.hm.member.model.Member"--%>
                                    <form:form action="register.hm" method="post" modelAttribute="member">
                                        <fieldset>
                                            <form:errors path="name" cssClass="error"/>
                                            <form:input path="name" placeholder="Username"/>
                                        </fieldset>

                                        <form:errors path="password" cssClass="error"/>
                                        <form:input type="password" path="password" placeholder="Password"/>

                                        <form:errors path="passwordNot" cssClass="error"/>
                                        <form:input type="password" path="passwordNot" placeholder="Confirm Password"/>

                                        <form:errors path="email" cssClass="error"/>
                                        <form:input type="email" path="email" placeholder="Email"/>

                                        <form:errors path="phone" cssClass="error"/>
                                        <form:input path="phone" placeholder="Phone"/>

                                        <div class="button-box">
                                            <button type="submit"><span>Register</span></button>
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
</div>

<%@include file="footer.jsp"%>
<%@ include file="script.jsp"%>








<!-- All JS is here
============================================ -->

</body>

</html>