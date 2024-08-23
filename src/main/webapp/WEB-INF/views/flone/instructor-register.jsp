<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <a href="../index.hm">Home</a>
                </li>
                <li class="active">Register</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pt-95 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="billing-info-wrap">
                    <h1>강의 등록</h1>
                    <form action="regist.hm" method="post" accept-charset="UTF-8">
                        <div class="row">
                            <div class="col-lg-6 col-md-6">
                                <div class="billing-info mb-20">
                                    <label>타이틀</label>
                                    <input type="text" name="instructorTitle">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-20">
                                    <label>강의 설명</label>
                                    <textarea name="instructorDescription"></textarea>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-select mb-20">
                                    <label>과목</label>
                                    <select name="subjectId">
                                        <option value="1">국어</option>
                                        <option value="2">영어</option>
                                        <option value="3">수학</option>
                                        <option value="4">사회</option>
                                        <option value="5">과학</option>
                                        <option value="6">자바</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="checkout-account-toggle open-toggle2 mb-30">
                            <button class="btn-hover checkout-btn" type="submit">register</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
<%@ include file="script.jsp"%>

</body>
</html>