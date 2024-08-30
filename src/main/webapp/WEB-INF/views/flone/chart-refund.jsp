<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    const revenueDataJson = '${chartDataJson}';
    const revenueData = JSON.parse(revenueDataJson);

    const revenueLabels = revenueData.map(data => data.month);
    const refundAmounts = revenueData.map(data => data.refund);

    const revenueCtx = document.getElementById('revenueRefundChart').getContext('2d');
    new Chart(revenueCtx, {
        type: 'line',
        data: {
            labels: revenueLabels,
            datasets: [{
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
            }, plugins: {
                title: {
                    display: true,
                    text: '월별 환불금액',
                    font: {
                        size: 20
                    }
                }
            }
        }
    });
</script>
