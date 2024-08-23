package org.hermez.instructor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRegisterRequest {
    private int subjectId;
    private int memberId;

    private String instructorDescription; // 국어교사입니다 국어 빡고수
    private String instructorTitle; // 저와 함께 성장해 나가는 모습 기대해주세요!
    private int instructorStatus; // 1

    private int roleId;




}
