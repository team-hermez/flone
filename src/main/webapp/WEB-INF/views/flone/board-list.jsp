<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.html">홈</a>
                </li>
                <li class="active">클래스 룸</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pb-80 pt-100">
    <div class="container">
        <div class="section-title text-center mb-sm-5">
            <h2>강의 룸</h2>
        </div>
        <div class="container my-5">
            <div class="card mx-auto align-content-center" style="max-width: 720px;">
                <div class="card-body text-center">
                    <h5 class="card-title">강의 링크</h5>
                    <p class="card-text">
                        <a href="${boardListResponse.videoLink}" target="_blank" class="btn btn-primary">강의 보기</a>
                    </p>
                </div>
            </div>
        </div>
        <c:choose>
        <c:when test="${not empty sessionScope.INSTRUCTOR}">
            <div class="text-center mb-30">
                <a href="board-register-form.hm?classroomId=${boardListResponse.classroomId}" class="btn btn-primary">과제 등록</a>
            </div>
        </c:when>
        </c:choose>
        <div class="row">
            <div class="ms-auto me-auto col-lg-9">
                <div class="checkout-wrapper">
                    <div id="faq" class="panel-group">
                        <c:forEach var="board" items="${boardListResponse.boards}">
                            <div class="panel panel-default single-my-account">
                                <div class="panel-heading my-account-title">
                                    <h3 class="panel-title"><a data-bs-toggle="collapse"
                                                                                href="#my-account-${board.boardId}">${board.boardTitle}</a></h3>
                                </div>
                                <div id="my-account-${board.boardId}" class="panel-collapse collapse" data-bs-parent="#faq">
                                    <div class="panel-body">
                                        <div class="myaccount-info-wrapper">
                                            <div class="account-info-wrapper">
                                                <h5>${board.boardContent}</h5>
                                            </div>
                                            <div class="billing-back-btn">
                                                <div class="billing-back">
                                                    <a href="#"><i class="fa fa-arrow-up"></i> back</a>
                                                </div>
                                                <div class="billing-btn">
                                                    <a href="/flone/board/board-detail.hm?boardId=${board.boardId}&classroomId=${classroomId}" class="btn btn-primary">자세히</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp"%>
</body>

</html>