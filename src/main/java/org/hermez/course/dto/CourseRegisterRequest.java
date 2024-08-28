package org.hermez.course.dto;

import lombok.Getter;
import lombok.Setter;
import org.hermez.course.model.CourseTime;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile imageFile;
    private int coursePrice;
    private Date startDate;
    private Date endDate;
    private List<CourseTime> courseTimes;

    CourseRegisterRequest(){}

    /**
     * CourseRegisterRequest 생성자입니다.
     * @param courseId
     * @param title
     * @param instructorId
     * @param gradeId
     * @param description
     * @param imageFile
     * @param coursePrice
     * @param startDate
     * @param endDate
     * @param courseTimes
     */

    /**
     * CourseRegisterRequest 생성자입니다.
     * @param courseId
     * @param title
     * @param instructorId
     * @param gradeId
     * @param description
     * @param imageFile
     * @param coursePrice
     * @param startDate
     * @param endDate
     * @param courseTimes
     */
    public CourseRegisterRequest(int courseId, String title, int instructorId, int gradeId,String description,MultipartFile imageFile, int coursePrice, Date startDate, Date endDate, List<CourseTime> courseTimes) {
        this.courseId = courseId;
        this.title = title;
        this.instructorId = instructorId;
        this.gradeId = gradeId;
        this.description = description;
        this.imageFile = imageFile;
        this.coursePrice = coursePrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTimes = courseTimes;
    }

    /**
     * CourseRegisterRequest 생성자입니다.
     * @param courseId
     * @param courseTimes
     */
    public CourseRegisterRequest(int courseId, List<CourseTime> courseTimes) {
        this.courseId = courseId;
        this.courseTimes = courseTimes;
    }
}
