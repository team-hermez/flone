<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
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
</script>

