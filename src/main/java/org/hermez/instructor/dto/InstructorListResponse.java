package org.hermez.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InstructorListResponse {
    private int instructorId;
    private int memberId;
    private int subjectId;
    private String name;
    private String instructorStatus;
    private String instructorTitle;
}
