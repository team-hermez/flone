<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<%@ include file="refund-payment.jsp" %>
<%@ include file="admin-sidebar.jsp" %>
<%@ include file="admin-header.jsp" %>
<div class="home-sidebar-right">
    <div class="section-title text-center mt-5">
        <h2>회원 상세</h2>
    </div>
    <div class="checkout-area pb-80 pt-100">
        <div class="container">
            <div class="row">
                <div class="ms-auto me-auto col-lg-9">
                    <div class="checkout-wrapper">
                        <div id="faq" class="panel-group">
                            <div class="panel panel-default single-my-account">
                                <div class="panel-heading my-account-title">
                                    <h3 class="panel-title"><span>1 .</span> <a data-bs-toggle="collapse"
                                                                                href="#my-account-1">회원 프로필</a></h3>
                                </div>
                                <div id="my-account-1" class="panel-collapse collapse show" data-bs-parent="#faq">
                                    <div class="panel-body">
                                        <div class="myaccount-info-wrapper">
                                            <div class="account-info-wrapper">
                                                <h3>회원 정보</h3>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-9 col-md-9">
                                                    <div class="billing-info">
                                                        <h4>이름</h4>
                                                        <p>${member.name}</p>
                                                    </div>
                                                    <div class="billing-info">
                                                        <h4>이메일 주소</h4>
                                                        <p>${member.email}</p>
                                                    </div>
                                                    <div class="billing-info">
                                                        <h4>전화</h4>
                                                        <p>${member.phone}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default single-my-account">
                                <div class="panel-heading my-account-title">
                                    <h3 class="panel-title"><span>2 .</span> <a data-bs-toggle="collapse"
                                                                                href="#my-account-3">회원
                                        휴먼 전환</a></h3>
                                </div>
                                <div id="my-account-3" class="panel-collapse collapse" data-bs-parent="#faq">
                                    <div class="panel-body">
                                        <div class="myaccount-info-wrapper">
                                            <div class="entries-wrapper">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 d-flex align-items-center justify-content-center">
                                                        <div class="entries-info text-center">
                                                            <p>비회원으로 전환합니다.</p>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6 col-md-6 d-flex align-items-center justify-content-center">
                                                        <div class="entries-edit-delete text-center">
                                                            <a class="edit" href="/flone/index.hm">홈으로</a>
                                                            <form action="/flone/member/delete.hm?memberId=${member.memberId}" method="post" style="display: inline;">
                                                                <button type="submit" class="edit" style="color: #fff;display: inline-block;line-height: 1;margin: 0 2px;padding: 12px 15px;text-transform: uppercase;font-weight: 500;background: black;: 0; height: 42px;">회원휴먼</button>                                                </form>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h3 class="cart-page-title">회원의 결제 목록</h3>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                            <form action="#">
                                <div class="table-content table-responsive cart-table-content">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>예약 번호</th>
                                            <th>강의 명</th>
                                            <th>강의 시작일</th>
                                            <th>가격</th>
                                            <th>결제상태</th>
                                            <th>
                                                <button type="button" id="selectAllBtn">모두선택</button>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="reservation" items="${reservationPage.contents}">
                                            <tr>
                                                <td class="product-thumbnail">
                                                    <a href="my-payment-detail.hm?merchantUid=${reservation.merchantUid}">${reservation.merchantUid}</a>
                                                </td>
                                                <td class="product-name"><a
                                                        href="/flone/course/detail.hm?courseId=${reservation.courseId}">${reservation.title}</a>
                                                </td>
                                                <td class="product-price-cart">${reservation.startDate}</td>
                                                <td class="product-subtotal">${reservation.paymentAmount}</td>
                                                <td class="product-remove">
                                                    <c:if test="${reservation.reservationStatusId == 1 and reservation.isBefore}">
                                                        <a href="#"><i class="fa fa-pencil">예약완료</i></a>
                                                    </c:if>
                                                    <c:if test="${reservation.reservationStatusId == 1 and reservation.isAfter}">
                                                        <a href="#"><i class="fa fa-pencil">예약확정</i></a>
                                                    </c:if>
                                                    <c:if test="${reservation.reservationStatusId == 2}">
                                                        <a href="#"><i class="fa fa-pencil">예약취소</i></a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${reservation.reservationStatusId == 1 and reservation.isBefore}">
                                                            <input type="checkbox" class="checkList"
                                                                   value="${reservation.merchantUid}">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="checkbox"
                                                                   value="${reservation.merchantUid}" disabled>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="cart-shiping-update-wrapper">
                                            <div class="cart-shiping-update">
                                            </div>
                                            <div class="cart-clear">
                                                <a href="#" id="cancel-btn">취소</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="pro-pagination-style text-center mt-20">
                                    <div>
                                        <ul>
                                            <%--                        Prev Page--%>
                                            <c:choose>
                                                <c:when test="${reservationPage.currentPage > 1}">
                                                    <li><a class="prev"
                                                           href="?page=${reservationPage.currentPage - 1}"><i
                                                            class="fa fa-angle-double-left"></i></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li><a class="prev" href="#"><i
                                                            class="fa fa-angle-double-left"></i></a></li>
                                                </c:otherwise>
                                            </c:choose>
                                            <%--                        Current Page--%>
                                            <c:forEach begin="1" end="${reservationPage.totalPages}"
                                                       var="pageNum">
                                                <c:choose>
                                                    <c:when test="${pageNum == reservationPage.currentPage}">
                                                        <li><a class="active" href="#">${pageNum}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <%--                        Next Page--%>
                                            <c:choose>
                                                <c:when test="${reservationPage.currentPage < reservationPage.totalPages}">
                                                    <li><a class="next"
                                                           href="?page=${reservationPage.currentPage + 1}"><i
                                                            class="fa fa-angle-double-right"></i></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li><a class="next" href="#"><i
                                                            class="fa fa-angle-double-right"></i></a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="refund-list-payment.jsp" %>
    <%@ include file="admin-footer.jsp" %>
</div>
<%@ include file="chart-signup.jsp" %>
<%@ include file="chart-payment-refund.jsp" %>
<%@ include file="chart-course-subject.jsp" %>
<%@ include file="chart-classroom.jsp" %>
<%@ include file="chart-course.jsp" %>
<%@ include file="script.jsp" %>
</body>
</html>
