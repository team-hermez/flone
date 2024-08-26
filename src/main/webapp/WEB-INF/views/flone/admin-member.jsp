<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<%@ include file="admin-sidebar.jsp" %>
<%@ include file="admin-header.jsp" %>
<div class="home-sidebar-right">
    <div class="container mt-5">
        <div class="section-title text-center">
            <h2>회원 관리</h2>
        </div>
        <div class="w-75 p-3 mx-auto">
            <div>
                <canvas id="monthlySignupsChart"></canvas>
            </div>
        </div>
        <div class="section-title text-center mt-5">
            <h2>전체 회원 리스트</h2>
        </div>
        <div class="row mt-5">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="table-content table-responsive cart-table-content">
                    <table>
                        <thead>
                        <tr>
                            <th>프로필</th>
                            <th>회원번호</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>전화번호</th>
                            <th>가입일</th>
                            <th>삭제</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="member" items="${memberPage.contents}">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="#"><img src="../../../resources/images/no_profile.png"
                                                     width="100px"></a>
                                </td>
                                <td class="product-name"><a href="#">${member.memberId}</a></td>
                                <td class="product-name"><a href="#">${member.name}</a></td>
                                <td class="product-name"><a href="#">${member.email}</a></td>
                                <td class="product-name"><a href="#">${member.phone}</a></td>
                                <td class="product-name">${member.createdAt}</td>
                                <td class="product-remove">
                                    <a href="#"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <ul>
                        <c:choose>
                            <c:when test="${memberPage.currentPage > 1}">
                                <li><a class="prev" href="?page=${memberPage.currentPage - 1}"><i
                                        class="fa fa-angle-double-left"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${memberPage.totalPages}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum == memberPage.currentPage}">
                                    <li><a class="active" href="#">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${memberPage.currentPage < memberPage.totalPages}">
                                <li><a class="next" href="?page=${memberPage.currentPage + 1}"><i
                                        class="fa fa-angle-double-right"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="next" href="#"><i class="fa fa-angle-double-right"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>
</div>
<%@ include file="chart-signup.jsp" %>
<%@ include file="script.jsp" %>
</body>
</html>
