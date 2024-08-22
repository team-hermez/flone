package org.hermez.course.dto;

import lombok.Builder;
import lombok.Getter;
import org.hermez.course.model.Course;

import java.sql.Date;

/**
 * 전체 강의 목록 데이터 요청 DTO 입니다.
 *
 * @author 엄진수
 */
@Builder
@Getter
public class CourseListResponse {
    private int courseId;          // 강의 ID
    private Date startDate;        // 수강일
    private String title;          // 강의 제목
    private String description;    // 강의 설명
    private String instructorName; // 강사 이름

    private CourseListResponse(){}

    /**
     * CourseListResponse 생성자 입니다.
     * @param courseId
     * @param startDate
     * @param title
     * @param description
     * @param instructorName
     */
    private CourseListResponse(int courseId, Date startDate, String title, String description, String instructorName) {
        this.courseId = courseId;
        this.startDate = startDate;
        this.title = title;
        this.description = description;
        this.instructorName = instructorName;
    }

    /**
     * parameter가 Entity인 CourseListResponse 생성자 입니다.
     * @param course
     */

    private CourseListResponse(Course course) {
        this.courseId = getCourseId();
        this.startDate = getStartDate();
        this.title = getTitle();
        this.description = getDescription();
        this.instructorName = getInstructorName();
    }

    /*
      CourseListResponse Builder 입니다.

      @param courseId
     * @param startDate
     * @param title
     * @param description
     */
//    public CourseListResponse(int courseId, Date startDate, String title, String description) {
//        CourseListResponse courseListResponse = CourseListResponse.builder()
//                .courseId(courseId)
//                .startDate(startDate)
//                .title(title)
//                .description(description)
//                .build();
//    }
}