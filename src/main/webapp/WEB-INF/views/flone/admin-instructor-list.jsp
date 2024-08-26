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
    <div class="team-area pt-95 pb-70">
        <div class="container">
            <div class="section-title-2 text-center mb-60">
                <h2>강사진</h2>
            </div>
            <div class="row">
                <c:forEach var="instructors" items="${instructors.contents}">
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="team-wrapper mb-30">
                            <div class="team-img">
                                <a href="/flone/instructor/detail.hm?instructorId=${instructors.instructorId}">
                                    <img src="../../../../images/${instructors.saveImage}"
                                         href="/flone/instructor/detail.hm?instructorId=${instructors.instructorId}"
                                         onerror="this.onerror=null; this.src='../../../../images/image.png';">
                                </a>
                            </div>
                            <div class="team-content text-center">
                                <h4>${instructors.name}</h4>
                                <span>${instructors.instructorTitle} </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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
                        <c:when test="${instructors.currentPage<instructors.totalPages}">
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
    <%@ include file="admin-footer.jsp" %>
</div>

<%@ include file="script.jsp" %>
</body>
</html>
