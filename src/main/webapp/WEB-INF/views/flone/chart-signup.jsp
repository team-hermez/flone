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
                    beginAtZero: true,
                    ticks:{
                        stepSize: 1
                    }
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
</script>

