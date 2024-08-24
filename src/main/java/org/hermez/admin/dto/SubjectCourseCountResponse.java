package org.hermez.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCourseCountResponse {
    private String subjectName;
    private int courseCount;
}