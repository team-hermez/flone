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
        <div class="section-title text-center mt-5">
            <h2>강의 리스트</h2>
        </div>
        <div class="row mt-5">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="table-content table-responsive cart-table-content">
                    <table>
                        <thead>
                        <tr>
                            <th>강의번호</th>
                            <th>등급</th>
                            <th>과목</th>
                            <th>제목</th>
                            <th>강사</th>
                            <th>가격</th>
                            <th>학생수</th>
                            <th>수익</th>
                            <th>시작일</th>
                            <th>종료일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="course" items="${courses.contents}">
                            <tr onclick="window.location.href='/flone/admin/course/manage-course-detail.hm?courseId=${course.courseId}'" style="cursor:pointer;">
                                <td class="product-name">${course.courseId}</td>
                                <td class="product-name">${course.gradeName}</td>
                                <td class="product-name">${course.subjectName}</td>
                                <td class="product-name">${course.courseTitle}</td>
                                <td class="product-name">${course.instructorName}</td>
                                <td class="product-name">${course.coursePrice}</td>
                                <td class="product-name">${course.studentCount}</td>
                                <td class="product-name">${course.totalRevenue}</td>
                                <td class="product-name">${course.startDate}</td>
                                <td class="product-name">${course.endDate}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <ul>
                        <c:choose>
                            <c:when test="${courses.currentPage > 1}">
                                <li><a class="prev" href="?page=${courses.currentPage - 1}"><i
                                        class="fa fa-angle-double-left"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${courses.totalPages}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum == courses.currentPage}">
                                    <li><a class="active" href="#">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${courses.currentPage < courses.totalPages}">
                                <li><a class="next" href="?page=${courses.currentPage + 1}"><i
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
<%@ include file="script.jsp" %>
</body>
</html>
