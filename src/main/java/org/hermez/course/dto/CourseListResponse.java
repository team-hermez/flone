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
    private int courseId;
    private Date startDate;
    private String title;
    private String description;
    private String instructorName;
    private String courseImage;

    private CourseListResponse(){}

    /**
     * CourseListResponse 생성자 입니다.
     * @param courseId          강의 고유 코드
     * @param startDate         강의 시작일
     * @param title             강의 제목
     * @param description       강의 설명
     * @param instructorName    강사 이름
     * @param courseImage       강의 사진
     */
    private CourseListResponse(int courseId, Date startDate, String title, String description, String instructorName, String courseImage) {
        this.courseId = courseId;
        this.startDate = startDate;
        this.title = title;
        this.description = description;
        this.instructorName = instructorName;
        this.courseImage = courseImage;
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
        this.courseImage = getCourseImage();
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