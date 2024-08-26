package org.hermez.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourseManageListResponse {

    private int courseId;
    private String gradeName;
    private String courseTitle;
    private String subjectName;
    private String instructorName;
    private LocalDateTime createdAt;
    private int coursePrice;
    private int studentCount;
    private int totalRevenue;
    private LocalDate startDate;
    private LocalDate endDate;
}
