<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <a href="../index.hm">Home</a>
                </li>
                <li class="active">Blog No sidebar </li>
            </ul>
        </div>
    </div>
</div>
<div class="Blog-area pt-100 pb-100 blog-no-sidebar">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="row">

                    <c:forEach var="course" items="${courses}">

                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <div class="blog-wrap-2 mb-30">
                            <div class="blog-img-2">
                                <a href="/flone/course/detail.hm?courseId=${course.courseId}"><img src="/assets/img/blog/blog-9.jpg" alt=""></a>
                            </div>
                            <div class="blog-content-2">
                                <div class="blog-meta-2">
                                    <ul>
                                        <li> ${course.startDate}</li>
                                        <li><a href="#">${course.instructorName}<i class="fa fa-comments-o"></i></a></li>
                                    </ul>
                                </div>
                                <h4><a href="/flone/course/detail.hm?courseId=${course.courseId}">${course.title}</a></h4>
                                <p> ${course.description}</p>
                                <div class="blog-share-comment">
                                    <div class="blog-btn-2">
                                        <a href="/blog-details.html">read more</a>
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
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="pro-pagination-style text-center mt-20">
                    <ul>
                        <li><a class="prev" href="#"><i class="fa fa-angle-double-left"></i></a></li>
                        <li><a class="active" href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a class="next" href="#"><i class="fa fa-angle-double-right"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<%@ include file="script.jsp"%>

</body>

</html>