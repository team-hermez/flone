<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
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
</script>

