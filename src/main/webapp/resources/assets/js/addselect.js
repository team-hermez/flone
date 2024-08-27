document.getElementById('add-select').addEventListener('click', function() {
    const container = document.getElementById('course-container');
    if (container) {
        const index = container.getElementsByClassName('course-time').length+1;
        const newCourseTime = document.createElement('div');
        newCourseTime.className = 'course-time'
        newCourseTime.innerHTML = `
        <div class="billing-select mb-20">
            <label>강의 요일</label>
            <select name="courseTimes[${index}].dayOfWeek">
                <option id="monday" value="monday">월요일</option>
                <option id="tuesday" value="tuesday">화요일</option>
                <option id="wednesday" value="wednesday">수요일</option>
                <option id="thursday" value="thursday">목요일</option>
                <option id="friday" value="friday">금요일</option>
            </select>
        </div>
        <div class="billing-select mb-20">
            <label>시작 시간</label>
            <select name="courseTimes[${index}].startTime">
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
            <label>종료 시간</label>
            <select name="courseTimes[${index}].endTime">
                <option id="endTime10:00" value="09:50">오전 09:50</option>
                <option id="endTime11:00" value="10:50">오전 10:50</option>
                <option id="endTime12:00" value="11:50">오후 11:50</option>
                <option id="endTime14:00" value="13:50">오후 13:50</option>
                <option id="endTime15:00" value="14:50">오후 14:50</option>
                <option id="endTime16:00" value="15:50">오후 15:50</option>
                <option id="endTime17:00" value="16:50">오후 16:50</option>
                <option id="endTime18:00" value="17:50">오후 17:50</option>
            </select>
        </div>
    `;
        container.appendChild(newCourseTime);
    }else{
        console.error('Container element not found');
    }
});

document.getElementById('remove-select').addEventListener('click',
function(){
    const container = document.getElementById('course-container');
    if(container && container.children.length > 1){
        container.removeChild(container.lastElementChild);
    } else{
        console.error('No element to remove');
    }
});