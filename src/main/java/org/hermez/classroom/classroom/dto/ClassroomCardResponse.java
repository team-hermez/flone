package org.hermez.classroom.classroom.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 클래스룸 카드 리스폰 응답 DTO 입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class ClassroomCardResponse {

    private int classroomId;
    private String classroomName;
    private String description;
    private LocalDateTime createdAt;
    private String classroomImage;

    public ClassroomCardResponse(int classroomId, String classroomName, String description, Timestamp createdAt, String classroomImage) {
        this.classroomId = classroomId;
        this.classroomName = classroomName;
        this.description = description;
        this.createdAt = createdAt.toLocalDateTime();
        this.classroomImage = classroomImage;
    }

}
