package org.hermez.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InstructorListResponse {
    private int instructorId;
    private int memberId;
    private int subjectId;
    private String name;
    private String instructorStatus;
    private String instructorTitle;
    private String saveImage;
}
