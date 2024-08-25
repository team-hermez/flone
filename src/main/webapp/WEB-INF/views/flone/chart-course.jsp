<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
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
                label: '강의',
                data: monthlyCourseLabelsData,
                borderColor: 'rgb(198,76,76)',
                backgroundColor: 'rgba(238,14,14,0.2)',
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

