package org.hermez.course.dto;

import lombok.Getter;
import lombok.Setter;
import org.hermez.course.model.CourseTime;

import java.sql.Date;
import java.util.List;

/**
 * 강의 등록 Request DTO입니다.
 * 
 * @author 엄진수
 */
@Getter
@Setter
public class CourseRegisterRequest {
    private int courseId;
    private String title;
    private int instructorId;
    private int gradeId;
    private String description;
    private Double coursePrice;
    private Date startDate;
    private Date endDate;
    private List<CourseTime> courseTimes;

    CourseRegisterRequest(){}

    public CourseRegisterRequest(int courseId, String title, int instructorId, int gradeId,String description, Double coursePrice, Date startDate, Date endDate, List<CourseTime> courseTimes) {
        this.courseId = courseId;
        this.title = title;
        this.instructorId = instructorId;
        this.gradeId = gradeId;
        this.description = description;
        this.coursePrice = coursePrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTimes = courseTimes;
    }

    public CourseRegisterRequest(int courseId, List<CourseTime> courseTimes) {
        this.courseId = courseId;
        this.courseTimes = courseTimes;
    }
}
