package org.hermez.course.model;

import lombok.Getter;
import lombok.Setter;

/**
 *  강의 시간에 관련된
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

    public CourseTime(int courseId, String dayOfWeek, String startTime, String endTime) {
        this.courseId = courseId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
