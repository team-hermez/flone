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
                <li><a href="index.html">Home</a></li>
                <li class="active">강의 룸</li>
            </ul>
        </div>
    </div>
</div>
<div class="Blog-area pt-100 pb-100 blog-no-sidebar">
    <div class="container">
        <div class="section-title text-center mb-sm-5">
            <h2>강의 룸</h2>
        </div>
        <div class="text-center mb-30">
            <a href="classroom-register-form.hm?courseId=${courseId}" class="btn btn-primary">클래스룸 등록하기</a>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <c:forEach var="classroom" items="${classrooms}">
                        <div class="col-lg-4 col-md-6 col-sm-12">
                            <div class="blog-wrap-2 mb-30">
                                <div class="blog-img-2">
                                    <img src="../../../../images/${classroom.classroomImage}" width="300px" height="300px"
                                         onerror="this.onerror=null; this.src='../../../../images/image.png';">
                                </div>
                                <div class="blog-content-2">
                                    <div class="blog-meta-2">
                                        <ul>
                                            <li>${classroom.createdAt}</li>
                                            <li><a href="#">4 <i class="fa fa-comments-o"></i></a></li>
                                        </ul>
                                    </div>
                                    <h4><a href="/flone/board/board-list.hm?classroomId=${classroom.classroomId}">${classroom.classroomName}</a></h4>
                                    <p>${classroom.description}</p>
                                    <div class="blog-share-comment">
                                        <div class="blog-btn-2">
                                            <a href="blog-details.html">read more</a>
                                        </div>
                                        <div class="blog-share">
                                            <span>share :</span>
                                            <div class="share-social">
                                                <ul>
                                                    <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                                                    <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                                                    <li><a class="instagram" href="#"><i class="fa fa-instagram"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Delete Button -->
                                    <div class="text-center mt-3">
                                        <form action="classroom-delete.hm" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                            <input type="hidden" name="classroomId" value="${classroom.classroomId}">
                                            <input type="hidden" name="courseId" value="${courseId}">
                                            <button type="submit" class="btn btn-danger">삭제</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <div>
                        <!-- Previous Page Link -->
                        <c:choose>
                            <c:when test="${currentPage > 1}">
                                <a href="?courseId=${courseId}&page=${currentPage - 1}&size=${pageSize}">Previous</a>
                            </c:when>
                            <c:otherwise>
                                <span>Previous</span>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach begin="1" end="${totalPages}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum == currentPage}">
                                    <span>${pageNum}</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="?courseId=${courseId}&page=${pageNum}">${pageNum}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- Next Page Link -->
                        <c:choose>
                            <c:when test="${currentPage < totalPages}">
                                <a href="?courseId=${courseId}&page=${currentPage + 1}">Next</a>
                            </c:when>
                            <c:otherwise>
                                <span>Next</span>
                            </c:otherwise>
                        </c:choose>
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
