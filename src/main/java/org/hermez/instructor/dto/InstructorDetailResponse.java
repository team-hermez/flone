package org.hermez.instructor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorDetailResponse {
    private int instructorId;
    private int memberId;
    private int subjectId;
    private String subjectName;
    private String name;
    private String instructorDescription;
    private String instructorStatus;
    private String instructorTitle;
}
