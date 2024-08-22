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
                <li class="active"> 게시글 </li>
            </ul>
        </div>
    </div>
</div>
<div class="welcome-area pt-100 pb-95">
    <div class="container">
        <div>
            <a href="/flone/board/board-edit-form.hm?boardId=${board.boardId}" class="btn btn-secondary">수정</a>
            <form action="/flone/board/board-delete.hm" method="post" style="display:inline;">
                <input type="hidden" name="boardId" value="${board.boardId}">
                <input type="hidden" name="classroomId" value="${board.classroomId}">

                <button type="submit" class="btn btn-dark" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
            </form>
        </div>
        <div class="welcome-content text-center">
            <h5>게시글</h5>
            <h1>${board.boardTitle}</h1>
            <p>${board.boardContent}</p>
        </div>
    </div>
</div>
<div class="description-review-area pb-90">
    <div class="container">
        <div class="description-review-wrapper">
            <div class="description-review-topbar nav">
                <a data-bs-toggle="tab" href="#">-</a>
                <a class="active" data-bs-toggle="tab" href="#des-details3">질문</a>
                <a data-bs-toggle="tab" href="#des-details2">-</a>
            </div>
            <div class="tab-content description-review-bottom">
                <div id="des-details2" class="tab-pane">
                    <div class="product-description-wrapper">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit, sed do eiusmod tempor incididunt</p>
                        <p>ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commo consequat. Duis aute irure dolor in reprehend in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt </p>
                    </div>
                </div>
                <div id="des-details1" class="tab-pane ">
                    <div class="product-anotherinfo-wrapper">
                        <ul>
                            <li><span>Weight</span> 400 g</li>
                            <li><span>Dimensions</span>10 x 10 x 15 cm </li>
                            <li><span>Materials</span> 60% cotton, 40% polyester</li>
                            <li><span>Other Info</span> American heirloom jean shorts pug seitan letterpress</li>
                        </ul>
                    </div>
                </div>
                <div id="des-details3" class="tab-pane active">
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="review-wrapper">
                                <c:forEach var="reply" items="${replies}">
                                    <div class="single-review">
                                        <div class="review-img">
                                            <img src="assets/img/testimonial/1.jpg" alt="">
                                        </div>
                                        <div class="review-content">
                                            <div class="review-top-wrap">
                                                <div class="review-left">
                                                    <div class="review-name">
                                                        <h4>김혁진</h4>
                                                    </div>
                                                    <div class="review-rating">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </div>
                                                </div>
                                                <div class="review-left">
                                                    <a href="#">삭제</a>
                                                </div>
                                            </div>
                                            <div class="review-bottom">
                                                <p>${reply.content}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="ratting-form-wrapper pl-50">
                                <h2 class="mb-4"> 질문 </h2>
                                <div class="ratting-form">
                                    <form action="/flone/reply/reply-register.hm" method="post">
                                        <input type="hidden" name="boardId" value="${board.boardId}">
                                        <input type="hidden" name="classroomId" value="${board.classroomId}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="rating-form-style form-submit">
                                                    <textarea name="content" placeholder="입력" required></textarea>
                                                    <input type="submit" value="전송">
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
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
<%@ include file="script.jsp"%>

</body>

</html>