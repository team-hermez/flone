<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
</head>

<body>
<%@ include file="header.jsp" %>

<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="/flone/index.hm">홈</a>
                </li>
                <li class="active">수강 예약 강의 상세</li>
            </ul>
        </div>
    </div>
</div>
<div class="Blog-area pt-100 pb-100">
    <div class="container">
        <div class="row flex-row-reverse">
            <div class="col-lg-9">
                <div class="blog-details-wrapper ml-20">
                    <div class="blog-details-top">
                        <div class="blog-details-img">
                            <img alt="" src="../../../../images/${courseDetailList.courseImage}">
                        </div>
                        <div class="blog-details-content">
                            <div class="blog-meta-2">
                                <ul>
                                    <li>강의 날짜</li>
                                    <li>${courseDetailList.startDate}
                                        - ${courseDetailList.endDate}</li>
                                </ul>
                                <ul>
                                    <li><a href="#">강의 시간 <i class="fa fa-comments-o"></i></a></li>
                                </ul>
                                <c:forEach items="${courseTimeList}" var="time">
                                    <ul>
                                        <li>${time.dayOfWeek}</li>
                                        <li>${time.startTime} - ${time.endTime}</li>
                                    </ul>
                                </c:forEach>
                            </div>
                            <h3>${courseDetailList.title}</h3>
                            <p>${courseDetailList.description}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="sidebar-style">
                    <div class="sidebar-widget mt-50">
                        <h4 class="pro-sidebar-title">강의 룸 </h4>
                        <div class="sidebar-project-wrap mt-30">
                            <c:forEach var="classroom" items="${classrooms.contents}" varStatus="status">
                                <c:if test="${status.index < 4}">
                                    <div class="single-sidebar-blog">
                                        <div class="sidebar-blog-img">
                                            <a href="/flone/board/board-list.hm?classroomId=${classroom.classroomId}">
                                                <img src="../../../../images/${classroom.classroomImage}" width="200px"
                                                     height="80px"
                                                     onerror="this.onerror=null; this.src='../../../../images/image.png';"></a>
                                        </div>
                                        <div class="sidebar-blog-content">
                                            <span>Photography</span>
                                            <h4>
                                                <a href="/flone/board/board-list.hm?classroomId=${classroom.classroomId}">${classroom.classroomName}</a>
                                            </h4>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                            <div class="single-sidebar-blog">
                                <form action="/flone/classroom/classroom-list.hm" method="get" style="display:inline;">
                                    <input type="hidden" name="courseId" value="${courseDetailList.courseId}">
                                    <div class="sidebar-blog-content">
                                        <button type="submit" class="btn btn-outline-danger">나의 강의 룸</button>
                                    </div>
                                </form>
                            </div>
                            <div class="single-sidebar-blog">
                                <form action="reserved-course-list.hm" method="get">
                                    <div class="sidebar-blog-content">
                                        <button type="submit" class="btn btn-secondary">나의 수강 목록</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<%@ include file="script.jsp" %>
</body>

</html>