package org.hermez.course.model;

import lombok.Getter;
import lombok.Setter;

/**
 *  강의 시간과 관련된 클래스입니다.
 * @author 엄진수
 */

@Setter
@Getter

public class CourseTime {
    private int courseId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    public CourseTime() {
    }

    /**
     * CourseTime 생성자입니다.
     * @param courseId
     * @param dayOfWeek
     * @param startTime
     * @param endTime
     */
    public CourseTime(int courseId, String dayOfWeek, String startTime, String endTime) {
        this.courseId = courseId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
