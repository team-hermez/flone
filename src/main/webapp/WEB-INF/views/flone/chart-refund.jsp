<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    const signupDataJson = '${monthlySignupChartData}';
    const signupData = JSON.parse(signupDataJson);

    const signupLabels = signupData.map(item => item.month);
    const signupCounts = signupData.map(item => item.count);

    const signupCtx = document.getElementById('monthlySignupsChart').getContext('2d');
    new Chart(signupCtx, {
        type: 'bar',
        data: {
            labels: signupLabels,
            datasets: [{
                label: '가입자',
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
            },
            plugins: {
                title: {
                    display: true,
                    text: '월별 가입자 수',
                    font: {
                        size: 20
                    }
                }
            }
        }
    });

    const revenueDataJson = '${monthlyPaymentChartData}';
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
                label: '결제',
                borderColor: 'red',
                data: revenueAmounts,
                fill: false
            }, {
                label: '환불',
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
            }, plugins: {
                title: {
                    display: true,
                    text: '월별 결제금액',
                    font: {
                        size: 20
                    }
                }
            }
        }
    });

    const courseCountData = JSON.parse('${courseCountBySubjectChartData}');

    const courseLabels = courseCountData.map(data => data.subjectName);
    const courseData = courseCountData.map(data => data.courseCount);

    const ctx = document.getElementById('courseCountChart').getContext('2d');

    new Chart(ctx, {
        type: 'polarArea',
        data: {
            labels: courseLabels,
            datasets: [{
                label: '과목별 강의 수',
                data: courseData,
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
            }, plugins: {
                title: {
                    display: true,
                    text: '과목별 강의 수',
                    font: {
                        size: 20
                    }
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
                label: '클래스룸',
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
            }, plugins: {
                title: {
                    display: true,
                    text: '월별 강의룸 개설 수',
                    font: {
                        size: 20
                    }
                }
            }
        }
    });

    const monthlyCourseCountJson = '${monthlyCourseCountChartData}';
    const monthlyCourseCountData = JSON.parse(monthlyCourseCountJson);

    const monthlyCourseLabels = monthlyCourseCountData.map(data => data.month);
    const monthlyCourseLabelsData = monthlyCourseCountData.map(data => data.count);

    const monthlyCourseCtx = document.getElementById('courseChart').getContext('2d');
    new Chart(monthlyCourseCtx, {
        type: 'line',
        data: {
            labels: monthlyCourseLabels,
            datasets: [{
                label: 'Number of Courses',
                data: monthlyCourseLabelsData,
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    beginAtZero: true
                },
                y: {
                    beginAtZero: true
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: '월별 강의 개설 수',
                    font: {
                        size: 20
                    }
                }
            }
        }
    });
</script>

