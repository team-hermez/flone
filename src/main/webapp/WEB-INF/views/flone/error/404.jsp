<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
<body>
<%@ include file="../header.jsp" %>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.html">Home</a>
                </li>
                <li class="active">404 Page </li>
            </ul>
        </div>
    </div>
</div>
<div class="error-area pt-40 pb-100">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-xl-7 col-lg-8 text-center">
                <div class="error">
                    <h1>404</h1>
                    <h2>페이지를 찾을 수 없습니다</h2>
                    <p>죄송합니다. 찾고 계신 페이지가 존재하지 않거나, 삭제되었거나, 이름이 변경되었거나, 일시적으로 사용할 수 없습니다.</p>
                    <p>고객님의 불편을 최소화하기 위해 최선을 다하겠습니다. 필요하신 경우 고객센터로 문의해 주세요.</p>
                    <form action="#" class="searchform mb-50">
                        <input type="text" name="search" id="error_search" placeholder="Search..." class="searchform__input">
                        <button type="submit" class="searchform__submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>
                    <a href="index.html" class="error-btn">Back to home page</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
<%@ include file="../script.jsp" %>
</body>

</html>