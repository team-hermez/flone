<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<%@ include file="admin-sidebar.jsp" %>
<%@ include file="admin-header.jsp" %>
<div class="home-sidebar-right">
    <div class="container-fluid mt-5">
        <div class="section-title text-center">
            <h2>관리자</h2>
        </div>
        <div class="row mt-5">
            <div class="col-md-5 card">
                <div class="chart-container">
                    <canvas id="monthlySignupsChart"></canvas>
                </div>
            </div>

            <div class="col-md-2">
                <div class="row mb-3">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <bold>누적 가입자 수</bold>
                                </h5>
                                <p class="card-text"> +${adminMainResponse.totalSignUpCount}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <bold>이번달 가입자 수</bold>
                                </h5>
                                <p class="card-text"> +${adminMainResponse.monthlySignUpCount}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <bold>일일 가입자 수</bold>
                                </h5>
                                <p class="card-text"> +${adminMainResponse.dailySignUpCount}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-5">
                <div class="card">
                    <div class="chart-container">
                        <canvas id="revenueRefundChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-3 card">
                <div class="chart-container">
                    <canvas id="courseCountChart"></canvas>
                </div>
            </div>
            <div class="col-md-5 card">
                <div class="chart-container">
                    <canvas id="classroomCreationChart"></canvas>
                </div>
            </div>
            <div class="col-md-4 card">
                <div class="chart-container">
                    <canvas id="courseChart"></canvas>
                </div>
            </div>
        </div>
    </div>
    <div class="funfact-area pt-100 pb-70">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30 mrgn-none">
                        <div class="count-icon">
                            <i class="pe-7s-smile"></i>
                        </div>
                        <h2 class="count">${adminMainResponse.totalSignUpCount}</h2>
                        <span>전체 가입자</span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30">
                        <div class="count-icon">
                            <i class="pe-7s-light"></i>
                        </div>
                        <h2 class="count">420</h2>
                        <span>전체 강사</span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30">
                        <div class="count-icon">
                            <i class="pe-7s-video"></i>
                        </div>
                        <h2 class="count">690</h2>
                        <span>전체 강의</span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30">
                        <div class="count-icon">
                            <i class="pe-7s-portfolio"></i>
                        </div>
                        <h2 class="count">${adminMainResponse.totalClassroomCount}</h2>
                        <span>전체 클래스룸</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp" %>
</div>
<%@ include file="chart-signup.jsp" %>
<%@ include file="chart-payment-refund.jsp" %>
<%@ include file="chart-course-subject.jsp" %>
<%@ include file="chart-classroom.jsp" %>
<%@ include file="chart-course.jsp" %>
<%@ include file="script.jsp" %>
</body>
</html>
