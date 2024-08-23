package org.hermez.course.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 강의 상세 페이지 Request DTO입니다.
 * 
 * @author 엄진수
 */
@Getter
@ToString
public class CourseDetailResponse {
    private int courseId;            // 강좌 ID
    private int instructorId;        // 강사 ID
    private String title;            // 과목명
    private Double coursePrice;      // 강좌 가격
    private String description;      // 강좌 내용
    private String startDate;        // 강좌 시작일
    private String endDate;          // 강좌 종료일
    private String instructorName;   // 강사명
    private String subject;          // 과목 (카테고리)
    private String grade;

    private CourseDetailResponse() {}

     /**
     * 상세 페이지의 생성자입니다.
     *
     * @param courseId
     * @param instructorId
     * @param title
     * @param coursePrice
     * @param description
     * @param startDate
     * @param endDate
     * @param instructorName
     * @param subject
     * @param grade
     */
    private CourseDetailResponse(int courseId,int instructorId, String title, Double coursePrice,
                                 String description, String startDate, String endDate,
                                 String instructorName, String subject, String grade) {
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.title = title;
        this.coursePrice = coursePrice;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructorName = instructorName;
        this.subject = subject;
        this.grade = grade;
    }

    /**
     * 생성자 Builder
     * @param courseId
     */
    @Builder
    public CourseDetailResponse(int courseId){
        this.courseId = courseId;
    }
}