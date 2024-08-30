<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
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
                <li class="active">강사 등록</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pt-95 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="billing-info-wrap">
                    <div class="section-title text-center mb-5">
                        <h2>강사 등록 신청서</h2>
                    </div>
                    <form action="regist.hm" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
                        <div class="row justify-content-center">
                            <div class="col-lg-8 col-md-10">
                                <div class="billing-info mb-3">
                                    <label>이미지 등록</label>
                                    <input type="file" id="saveImage" name="saveImage" accept=".jpg, .jpeg, .png"
                                           required/>
                                </div>
                            </div>
                            <div class="col-lg-8 col-md-10">
                                <div class="billing-info mb-3">
                                    <label>강사 소개</label>
                                    <input type="text" name="instructorTitle" class="form-control">
                                </div>
                            </div>
                            <div class="col-lg-8 col-md-10">
                                <div class="mb-3">
                                    <label>강의 설명</label>
                                    <textarea name="instructorDescription" class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="col-lg-8 col-md-10">
                                <div class="billing-select mb-3">
                                    <label>전공 과목</label>
                                    <select name="subjectId" class="form-select">
                                        <option value="1">국어</option>
                                        <option value="2">영어</option>
                                        <option value="3">수학</option>
                                        <option value="4">사회</option>
                                        <option value="5">과학</option>
                                    </select>
                                </div>
                            </div>
                            <div class="checkout-account-toggle open-toggle2 mb-30 text-center">
                                <button class="btn-hover checkout-btn" type="submit">제출하기</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>

</body>
</html>