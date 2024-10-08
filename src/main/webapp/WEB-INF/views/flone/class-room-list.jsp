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
                <li><a href="index.html">홈</a></li>
                <li class="active">클래스룸 목록</li>
            </ul>
        </div>
    </div>
</div>

<div class="Blog-area pt-100 pb-100 blog-no-sidebar">
    <div class="container">
        <div class="section-title text-center mb-sm-5">
            <h2>강의 룸</h2>
        </div>
        <c:choose>
            <c:when test="${not empty sessionScope.INSTRUCTOR}">
                <div class="text-center mb-30">
                    <a href="classroom-register-form.hm?courseId=${courseId}" class="btn btn-primary">클래스룸 등록하기</a>
                </div>
            </c:when>
        </c:choose>

        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <c:forEach var="classroom" items="${classroomPage.contents}">
                        <div class="col-lg-4 col-md-6 col-sm-12">
                            <div class="blog-wrap-2 mb-30">
                                <div class="blog-img-2">
                                    <img src="../../../../images/${classroom.classroomImage}" width="300px"
                                         height="300px"
                                         onerror="this.onerror=null; this.src='../../../../images/image.png';">
                                </div>
                                <div class="blog-content-2">
                                    <div class="blog-meta-2">
                                        <ul>
                                            <li>${classroom.createdAt}</li>
                                            <li><a href="#">4 <i class="fa fa-comments-o"></i></a></li>
                                        </ul>
                                    </div>
                                    <h4>
                                        <a href="/flone/board/board-list.hm?classroomId=${classroom.classroomId}">${classroom.classroomName}</a>
                                    </h4>
                                    <p>${classroom.description}</p>
                                    <div class="blog-share-comment">
                                        <div class="blog-btn-2">
                                            <a href="blog-details.html">read more</a>
                                        </div>
                                    </div>

                                    <c:choose>
                                        <c:when test="${not empty sessionScope.INSTRUCTOR}">
                                            <div class="text-center mt-3">
                                                <form action="classroom-delete.hm" method="post"
                                                      onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                                    <input type="hidden" name="classroomId"
                                                           value="${classroom.classroomId}">
                                                    <input type="hidden" name="courseId" value="${courseId}">
                                                    <button type="submit" class="btn btn-danger">삭제</button>
                                                </form>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <ul>
                        <c:choose>
                            <c:when test="${classroomPage.currentPage > 1}">
                                <li><a class="prev" href="?page=${classroomPage.currentPage - 1}"><i
                                        class="fa fa-angle-double-left"></i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${classroomPage.totalPages}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum == classroomPage.currentPage}">
                                    <li><a class="active" href="#">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="?page=${pageNum}">${pageNum}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${classroomPage.currentPage < classroomPage.totalPages}">
                                <li><a class="next" href="?page=${classroomPage.currentPage + 1}"><i
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
</div>

<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>
</html>
