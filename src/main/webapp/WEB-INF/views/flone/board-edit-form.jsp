<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
<div class="Blog-area pt-100 pb-100">
    <div class="container">
        <div class="row flex-row-reverse">
            <div class="col-lg-9">
                <div class="blog-details-wrapper ml-20">
                    <div class="blog-reply-wrapper mt-50">
                        <h4 class="blog-dec-title">수업 게시글 수정</h4>
                        <form class="blog-form" action="/board-edit.hm" method="post">
                            <input type="hidden" name="boardId" value="${board.boardId}" />
                            <input type="hidden" name="classroomId" value="${board.classroomId}" />
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="leave-form">
                                        <input type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle}" placeholder="제목" required />
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="text-leave">
                                        <textarea id="boardContent" name="boardContent" placeholder="내용" value="${board.boardContent}" required></textarea>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="leave-form">
                                        <input type="submit" value="수정완료" />
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
