<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
</head>

<body>
<%@ include file="header.jsp"%>

<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="/index.html">Home</a>
                </li>
                <li class="active">수강 예약 강의 상세 </li>
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
                            <img alt="" src="/assets/img/blog/blog-5.jpg">
                        </div>
                        <div class="blog-details-content">
                            <div class="blog-meta-2">
                                <ul>
                                    <li>강의 날짜</li>
                                    <li><a href="#">강의 시간 <i class="fa fa-comments-o"></i></a></li>
                                </ul>
                            </div>
                            <h3>과목명</h3>
                            <h3>강의 제목</h3>
                            <p>강의 설명 </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="sidebar-style">
                    <div class="sidebar-widget mt-50">
                        <h4 class="pro-sidebar-title">강의 룸 </h4>
                        <div class="sidebar-project-wrap mt-30">
                            <div class="single-sidebar-blog">
                                <div class="sidebar-blog-img">
                                    <a href="#"><img src="/assets/img/blog/sidebar-1.jpg" alt=""></a>
                                </div>
                                <div class="sidebar-blog-content">
                                    <span>Photography</span>
                                    <h4><a href="#">1주차 - 화법과 작문(1)</a></h4>
                                </div>
                            </div>
                            <div class="single-sidebar-blog">
                                <div class="sidebar-blog-img">
                                    <a href="#"><img src="/assets/img/blog/sidebar-2.jpg" alt=""></a>
                                </div>
                                <div class="sidebar-blog-content">
                                    <span>Branding</span>
                                    <h4><a href="#">2주차 - 화법과 작문(2)</a></h4>
                                </div>
                            </div>
                            <div class="single-sidebar-blog">
                                <div class="sidebar-blog-img">
                                    <a href="#"><img src="/assets/img/blog/sidebar-3.jpg" alt=""></a>
                                </div>
                                <div class="sidebar-blog-content">
                                    <span>Design</span>
                                    <h4><a href="#">3주차 - 비문학 (1)</a></h4>
                                </div>
                            </div>
                            <div class="single-sidebar-blog">
                                <div class="sidebar-blog-img">
                                    <a href="#"><img src="/assets/img/blog/sidebar-1.jpg" alt=""></a>
                                </div>
                                <div class="sidebar-blog-content">
                                    <span>Photography</span>
                                    <h4><a href="#">4주차 - 비문학 (2)</a></h4>
                                </div>
                            </div>
                            <div class="single-sidebar-blog">
                                <div class="sidebar-blog-content">
                                    <button type="button" class="btn btn-outline-danger">강의 룸</button>
                                </div>
                            </div>
                            <div class="single-sidebar-blog">
                                <div class="sidebar-blog-content">
                                    <button type="button" class="btn btn-secondary">환불하기</button>
                                </div>
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