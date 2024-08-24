<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <%@ include file="css.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .container-fluid {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .chart-container {
            width: 45%;
            margin-bottom: 20px;
        }

        canvas {
            width: 100%;
            height: 100%;
        }
    </style>
</head>

<body>
<%@ include file="admin-sidebar.jsp" %>
<%@ include file="admin-header.jsp" %>
<div class="home-sidebar-right">
    <div class="container-fluid">
        <div class="chart-container">
            <h1> 회원 관리 </h1>
            <canvas id="monthlySignupsChart"></canvas>
        </div>
        <div class="chart-container">
            <h1> 결제 관리 </h1>
            <canvas id="revenueRefundChart"></canvas>
        </div>
        <div class="chart-container">
            <h1> 강의 관리 </h1>
            <canvas id="courseCountChart"></canvas>
        </div>
        <div class="chart-container">
            <canvas id="classroomCreationChart"></canvas>
        </div>
        <div class="chart-container">
            <canvas id="topCoursesChart"></canvas>
        </div>
    </div>
    <div class="funfact-area bg-gray-3 pt-100 pb-70">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30">
                        <div class="count-icon">
                            <i class="pe-7s-portfolio"></i>
                        </div>
                        <h2 class="count">360</h2>
                        <span>project done</span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30">
                        <div class="count-icon">
                            <i class="pe-7s-cup"></i>
                        </div>
                        <h2 class="count">690</h2>
                        <span>cups of coffee</span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30">
                        <div class="count-icon">
                            <i class="pe-7s-light"></i>
                        </div>
                        <h2 class="count">420</h2>
                        <span>branding</span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-count text-center mb-30 mrgn-none">
                        <div class="count-icon">
                            <i class="pe-7s-smile"></i>
                        </div>
                        <h2 class="count">100</h2>
                        <span>happy clients</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="admin-footer.jsp"%>
</div>

<script>
    const signupDataJson = '${statisticsJson}';
    const signupData = JSON.parse(signupDataJson);

    const signupLabels = signupData.map(item => item.month);
    const signupCounts = signupData.map(item => item.count);

    const signupCtx = document.getElementById('monthlySignupsChart').getContext('2d');
    new Chart(signupCtx, {
        type: 'bar',
        data: {
            labels: signupLabels,
            datasets: [{
                label: 'Monthly Signups',
                data: signupCounts,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const revenueDataJson = '${chartDataJson}';
    const revenueData = JSON.parse(revenueDataJson);

    const revenueLabels = revenueData.map(data => data.month);
    const revenueAmounts = revenueData.map(data => data.revenue);
    const refundAmounts = revenueData.map(data => data.refund);

    const revenueCtx = document.getElementById('revenueRefundChart').getContext('2d');
    new Chart(revenueCtx, {
        type: 'line',
        data: {
            labels: revenueLabels,
            datasets: [{
                label: 'Revenue',
                borderColor: 'red',
                data: revenueAmounts,
                fill: false
            }, {
                label: 'Refund',
                borderColor: 'blue',
                data: refundAmounts,
                fill: false
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const courseCountData = JSON.parse('${courseCountJson}');

    const labels = courseCountData.map(data => data.subjectName);
    const data = courseCountData.map(data => data.courseCount);

    const ctx = document.getElementById('courseCountChart').getContext('2d');

    new Chart(ctx, {
        type: 'polarArea',
        data: {
            labels: labels,
            datasets: [{
                label: 'Course Count by Subject',
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                r: {
                    beginAtZero: true
                }
            }
        }
    });

    const classroomCreationDataJson = '${classroomCreationData}';
    const classroomCreationData = JSON.parse(classroomCreationDataJson);

    const classroomCreationLabels = classroomCreationData.map(item => item.month);
    const classroomCreationCounts = classroomCreationData.map(item => item.count);

    const classroomCreationCtx = document.getElementById('classroomCreationChart').getContext('2d');
    new Chart(classroomCreationCtx, {
        type: 'bar',
        data: {
            labels: classroomCreationLabels,
            datasets: [{
                label: 'Monthly Classroom Creations',
                data: classroomCreationCounts,
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const topCoursesDataJson = '${topCoursesData}';
    const topCoursesData = JSON.parse(topCoursesDataJson);

    const courseLabels = topCoursesData.map(item => item.courseTitle);
    const reservationCounts = topCoursesData.map(item => item.reservationCount);

    const topCoursesCtx = document.getElementById('topCoursesChart').getContext('2d');
    new Chart(topCoursesCtx, {
        type: 'bar',
        data: {
            labels: courseLabels,
            datasets: [{
                label: 'Reservations',
                data: reservationCounts,
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgba(255, 159, 64, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>

<%@ include file="script.jsp"%>
</body>
</html>
