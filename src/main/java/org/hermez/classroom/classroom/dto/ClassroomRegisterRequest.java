package org.hermez.classroom.classroom.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 클래스룸 등록 요청 dto 입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class ClassroomRegisterRequest {
    private int classroomId;
    private int courseId;
    private String classroomName;
    private String description;
    private MultipartFile imageFile;

    public ClassroomRegisterRequest() {}

    public ClassroomRegisterRequest(int courseId, String classroomName, String description, MultipartFile imageFile) {
        this.courseId = courseId;
        this.classroomName = classroomName;
        this.description = description;
        this.imageFile = this.imageFile;
    }
}