package org.hermez.instructor.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InstructorRegisterRequest {
    private int instructorId;
    private int subjectId;
    private int memberId;
    private MultipartFile saveImage;
    private String instructorDescription;
    private String instructorTitle;
    private int roleId;

}
