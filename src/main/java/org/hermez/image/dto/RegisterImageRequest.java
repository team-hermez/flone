package org.hermez.image.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 이미지 등록 요청을 위한 DTO 클래스입니다.
 *
 * <p>이 클래스는 이미지와 관련된 엔터티의 ID, 타입, 그리고 실제 파일 데이터를 포함합니다.</p>
 *
 * @author 김혁진
 */
@Getter
@Setter
public class RegisterImageRequest {

    private int entityId;
    private String entityType;
    private MultipartFile multipartFile;
}
