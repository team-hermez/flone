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
    private int courseId;
    private int instructorId;
    private String title;
    private int coursePrice;
    private String description;
    private String startDate;
    private String endDate;
    private String instructorName;
    private String subject;
    private String grade;
    private String courseImage;

    private CourseDetailResponse() {}

     /**
     * 상세 페이지의 생성자입니다.
     *
     * @param courseId          강의 고유 코드
     * @param instructorId      강사 고유 코드
     * @param title             강의 제목
     * @param coursePrice       강의 가격
     * @param description       강의 설명
     * @param startDate         강의 시작일
     * @param endDate           강의 종료일
     * @param instructorName    강사 이름
     * @param subject           과목
     * @param grade             교과등급
     * @param courseImage       강의 이미지                          
     */
    private CourseDetailResponse(int courseId,int instructorId, String title, int coursePrice,
                                 String description, String startDate, String endDate,
                                 String instructorName, String subject, String grade, String courseImage) {
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
        this.courseImage = courseImage;
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