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
            <h2>강사 등록 요청</h2>
        </div>
        <div class="row mt-5">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="table-content table-responsive cart-table-content">
                    <table>
                        <thead>
                        <tr>
                            <th>프로필</th>
                            <th>담당과목</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>전화번호</th>
                            <th>등록</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="instructor" items="${instructors.contents}">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="#"><img src="../../../resources/images/no_profile.png"
                                                     width="100px"></a>
                                </td>
                                <td class="product-name"><a href="#">${instructor.subjectName}</a></td>
                                <td class="product-name"><a href="#">${instructor.name}</a></td>
                                <td class="product-name"><a href="#">${instructor.email}</a></td>
                                <td class="product-name"><a href="#">${instructor.phone}</a></td>
                                <td class="product-name">
                                    <form action="/flone/admin/instructor/register-instructor.hm" method="post" style="display:inline;" onsubmit="return alert('등록이완료외었습니다');" >
                                        <input type="hidden" name="instructorId" value="${instructor.instructorId}">
                                        <button type="submit" class="btn btn-primary">
                                            등록
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <ul>
                        <c:choose>
                            <c:when test="${instructors.currentPage > 1}">
                                <li><a class="prev" href="?page=${instructors.currentPage - 1}"><i
                                        class="fa fa-angle-double-left"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${instructors.totalPages}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum == instructors.currentPage}">
                                    <li><a class="active" href="#">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${instructors.currentPage < instructors.totalPages}">
                                <li><a class="next" href="?page=${instructors.currentPage + 1}"><i
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
<%@ include file="script.jsp" %>
</body>
</html>
