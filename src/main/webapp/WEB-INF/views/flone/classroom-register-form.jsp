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
                <li>
                    <a href="index.html">Home</a>
                </li>
                <li class="active">Blog Details</li>
            </ul>
        </div>
    </div>
</div>
<div class="Blog-area pt-100 pb-100">
    <div class="container">
        <div class="row flex-row-reverse">
            <div class="col-lg-9">
                <div class="blog-details-wrapper ml-20">
                    <div class="blog-reply-wrapper mt-50">
                        <h4 class="blog-dec-title">강의 클래스룸 등록 </h4>
                        <form class="blog-form" action="/flone/classroom/classroom-register.hm" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="leave-form">
                                        <label>클래스룸 제목</label>
                                        <input type="text" id="classroomName" name="classroomName" placeholder="제목" required />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="leave-form">
                                        <label>영상 링크</label>
                                        <input type="text" id="videoLink" name="videoLink" placeholder="url"  />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="text-leave">
                                        <label>클래스룸 내용</label>
                                        <textarea id="description" name="description" placeholder="내용" required></textarea>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <div class="leave-form">
                                        <input type="hidden" id="courseId" name="courseId" value="${courseId}" />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="leave-form">
                                        <label>이미지 파일 (선택사항)</label>
                                        <input type="file" id="imageFile" name="imageFile" accept=".jpg, .jpeg, .png"/>
                                        <input type="submit" value="등록하기" />
                                    </div>
                                </div>
                            </div>
                        </form>

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
