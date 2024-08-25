package org.hermez.course.model;

/**
 * 게시판 모델 클래스입니다.
 * @author 엄진수
 */
public class Course {
    private int courseId;
    private int instructorId;
    private int coursePrice;
    private int imageId;
    private String title;
    private String createAt;
    private String updateAt;
    private String description;
    private String startDate;
    private String endDate;

    public Course(){}

    /**
     * Course 생성자 입니다.
     *
     * @param courseId
     * @param instructorId
     * @param imageId
     * @param title
     * @param createAt
     * @param updateAt
     * @param description
     * @param startDate
     * @param endDate
     */
    public Course(int courseId, int instructorId, int coursePrice,int imageId, String title, String createAt, String updateAt, String description, String startDate, String endDate) {
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.coursePrice = coursePrice;
        this.imageId = imageId;
        this.title = title;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

