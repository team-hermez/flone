package org.hermez.instructor.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 강사 등록 요청을 위한 DTO 클래스입니다.
 *
 * @author 김다은
 */
@Getter
@Setter
public class InstructorRegisterRequest {

    /**
     * 강사 ID (새 강사 등록 시에는 0일 수 있습니다).
     */
    private int instructorId;

    /**
     * 주제 ID (강사가 가르치는 주제의 고유 식별자).
     */
    private int subjectId;

    /**
     * 회원 ID (강사와 연관된 회원의 ID).
     */
    private int memberId;

    /**
     * 강사 프로필 이미지 파일.
     *
     * 파일 업로드를 처리하기 위한 MultipartFile 타입.
     */
    private MultipartFile saveImage;

    /**
     * 강사 설명.
     *
     * 강사의 소개 및 기타 설명을 담는 필드.
     */
    private String instructorDescription;

    /**
     * 강사 직함.
     *
     * 강사의 직함이나 역할을 나타내는 필드.
     */
    private String instructorTitle;

    /**
     * 역할 ID.
     *
     * 강사의 역할 또는 권한을 나타내는 필드.
     */
    private int roleId;
}
