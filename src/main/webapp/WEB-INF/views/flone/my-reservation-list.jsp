<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
<body>
<%@ include file="header.jsp" %>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="/flone/index.hm">홈</a>
                </li>
                <li class="active">나의 강의 목록</li>
            </ul>
        </div>
    </div>
</div>
<div class="cart-main-area pt-90 pb-100">
    <div class="container">
        <h3 class="cart-page-title">나의 강의 목록</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>주문 번호</th>
                                <th>강의 명</th>
                                <th>강의 시작일</th>
                                <th>강의 종료일</th>
                                <th>강의 상태</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${empty myReservationPage.contents}">
                                    <tr>
                                        <td colspan="5">아직 수강하고 있는 강의가 없습니다. 좋은 강의를 많이 준비했으니 둘러봐주세요
                                            :).
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="reservation"
                                               items="${myReservationPage.contents}">
                                        <tr>
                                            <td class="product-thumbnail">
                                                <a href="reservation-detail.hm?courseId=${reservation.courseId}">${reservation.merchantUid}</a>
                                            </td>
                                            <td class="product-name"><a
                                                    href="/flone/course/detail.hm?courseId=${reservation.courseId}">${reservation.title}</a>
                                            </td>
                                            <td class="product-price-cart">${reservation.startDate}</td>
                                            <td class="product-subtotal">${reservation.endDate}</td>
                                            <c:choose>
                                                <c:when test="${reservation.isBefore}">
                                                    <td>시작 전</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="product-remove">강의 중</td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper">
                                <div class="cart-shiping-update">
                                </div>
                                <div class="cart-clear">
                                    <a href="/flone/reservation/list.hm">결제 목록 가기</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pro-pagination-style text-center mt-20">
                        <div>
                            <ul>
                                <%--                        Prev Page--%>
                                <c:choose>
                                    <c:when test="${myReservationPage.currentPage > 1}">
                                        <li><a class="prev"
                                               href="?page=${myReservationPage.currentPage - 1}"><i
                                                class="fa fa-angle-double-left"></i></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="prev" href="#"><i
                                                class="fa fa-angle-double-left"></i></a></li>
                                    </c:otherwise>
                                </c:choose>
                                <%--                        Current Page--%>
                                <c:forEach begin="1" end="${myReservationPage.totalPages}"
                                           var="pageNum">
                                    <c:choose>
                                        <c:when test="${pageNum == myReservationPage.currentPage}">
                                            <li><a class="active" href="#">${pageNum}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <%--                        Next Page--%>
                                <c:choose>
                                    <c:when test="${myReservationPage.currentPage < myReservationPage.totalPages}">
                                        <li><a class="next"
                                               href="?page=${myReservationPage.currentPage + 1}"><i
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
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>