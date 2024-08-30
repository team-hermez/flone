package org.hermez.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InstructorManageListResponse {
    private int instructorId;
    private String email;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
    private String subjectName;
}

