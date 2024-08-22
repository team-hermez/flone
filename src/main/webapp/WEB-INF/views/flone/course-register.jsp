<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp"%>
</head>

<body>
<%@ include file="header.jsp" %>
<div class="breadcrumb-area pt-35 pb-35 bg-gray-3">
    <div class="container">
        <div class="breadcrumb-content text-center">
            <ul>
                <li>
                    <a href="index.jsp">Home</a>
                </li>
                <li class="active">Register</li>
            </ul>
        </div>
    </div>
</div>
<div class="checkout-area pt-95 pb-100">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="billing-info-wrap">
                    <h1>강의 등록</h1>
                    <form action="regist.hm" method="post" accept-charset="UTF-8">
                        <div class="row">
                            <div class="col-lg-6 col-md-6">
                                <div class="billing-info mb-20">
                                    <label>강의명</label>
                                    <input type="text" name="title">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                <div class="billing-info mb-20">
                                    <label>강의대상</label>
                                    <select name="gradeId">
                                        <option value="1">초등</option>
                                        <option value="2">중등</option>
                                        <option value="3">고등</option>
                                        <option value="4">성인</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                <div class="billing-info mb-20">
                                    <label>강의금액</label>
                                    <input type="number" name="coursePrice">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-20">
                                    <label>강의 설명</label>
                                    <textarea name="description"></textarea>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-select mb-20">
                                    <label>강의 요일</label>
                                    <select name="courseTimes[0].dayOfWeek">
                                        <option id="monday" value="monday">월요일</option>
                                        <option id="tuesday" value="tuesday">화요일</option>
                                        <option id="wednesday" value="wednesday">수요일</option>
                                        <option id="thursday" value="thursday">목요일</option>
                                        <option id="friday" value="friday">금요일</option>
                                    </select>
                                </div>
                                <%--                        <div class="col-lg-12">--%>
                                <div class="billing-select mb-20">
                                    <label>시작시간</label>
                                    <select name="courseTimes[0].startTime">
                                        <option id="start09:00" value="09:00">오전 09:00</option>
                                        <option id="start10:00" value="10:00">오전 10:00</option>
                                        <option id="start11:00" value="11:00">오전 11:00</option>
                                        <option id="start13:00" value="13:00">오후 13:00</option>
                                        <option id="start14:00" value="14:00">오후 14:00</option>
                                        <option id="start15:00" value="15:00">오후 15:00</option>
                                        <option id="start16:00" value="16:00">오후 16:00</option>
                                        <option id="start17:00" value="17:00">오후 17:00</option>
                                    </select>
                                </div>
                                <div class="billing-select mb-20">
                                    <label>종료시간</label>
                                    <select name="courseTimes[0].endTime">
                                        <option id="endTime10:00" value="10:00">오전 10:00</option>
                                        <option id="endTime11:00" value="11:00">오전 11:00</option>
                                        <option id="endTime12:00" value="12:00">오후 12:00</option>
                                        <option id="endTime14:00" value="14:00">오후 14:00</option>
                                        <option id="endTime15:00" value="15:00">오후 15:00</option>
                                        <option id="endTime16:00" value="16:00">오후 16:00</option>
                                        <option id="endTime17:00" value="17:00">오후 17:00</option>
                                        <option id="endTime18:00" value="18:00">오후 18:00</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-select mb-20">
                                    <label>강의 요일</label>
                                    <select name="courseTimes[1].dayOfWeek">
                                        <option value="monday">월요일</option>
                                        <option value="tuesday">화요일</option>
                                        <option value="wednesday">수요일</option>
                                        <option value="thursday">목요일</option>
                                        <option value="friday">금요일</option>
                                    </select>
                                </div>
                                <%--                        <div class="col-lg-12">--%>
                                <div class="billing-select mb-20">
                                    <label>시작시간</label>
                                    <select name="courseTimes[1].startTime">
                                        <option value="09:00">오전 09:00</option>
                                        <option value="10:00">오전 10:00</option>
                                        <option value="11:00">오전 11:00</option>
                                        <option value="13:00">오후 13:00</option>
                                        <option value="14:00">오후 14:00</option>
                                        <option value="15:00">오후 15:00</option>
                                        <option value="16:00">오후 16:00</option>
                                        <option value="17:00">오후 17:00</option>
                                    </select>
                                </div>
                                <div class="billing-select mb-20">
                                    <label>종료시간</label>
                                    <select name="courseTimes[1].endTime">
                                        <option value="10:00">오전 10:00</option>
                                        <option value="11:00">오전 11:00</option>
                                        <option value="12:00">오후 12:00</option>
                                        <option value="14:00">오후 14:00</option>
                                        <option value="15:00">오후 15:00</option>
                                        <option value="16:00">오후 16:00</option>
                                        <option value="17:00">오후 17:00</option>
                                        <option value="18:00">오후 18:00</option>
                                    </select>
                                </div>
                            </div>
                            <%--                        <div class="col-lg-12">--%>
                            <%--                            <div class="billing-info mt-15">--%>
                            <%--&lt;%&ndash;                                <label>이미지 등록</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                                <input type="file">&ndash;%&gt;--%>
                            <%--                                <label for="formFile" class="form-label">사진 등록</label>--%>
                            <%--                                <input class="form-control" type="file" id="formFile">--%>
                            <%--                            </div>--%>
                            <%--                        </div>--%>
                            <div class="col-lg-6 col-md-6">
                                <div class="billing-info mb-20">
                                    <label>수강 시작일</label>
                                    <input type="date" name="startDate">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                <div class="billing-info mb-20">
                                    <label>수강 종료일</label>
                                    <input type="date" name="endDate">
                                </div>
                            </div>
                        </div>
                        <div class="checkout-account-toggle open-toggle2 mb-30">
                            <button class="btn-hover checkout-btn" type="submit">register</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
<%@ include file="css.jsp" %>
</body>
</html>