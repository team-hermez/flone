package org.hermez.image.service;

import org.hermez.image.dto.RegisterImageRequest;
import java.io.IOException;

/**
 * 이미지 서비스 인터페이스입니다.
 *
 * <p>이 인터페이스는 이미지를 저장하는 기능을 제공합니다.</p>
 *
 * @author 김혁진
 */
public interface ImageService {

    /**
     * 이미지를 저장소에 저장하고 관련 정보를 데이터베이스에 기록합니다.
     *
     * @param registerImageRequest 이미지 등록 요청 객체
     * @throws IOException 이미지 저장 중 오류가 발생한 경우
     */
    void saveImage(RegisterImageRequest registerImageRequest) throws IOException;

    /**
     * 주어진 엔티티 ID와 타입에 대한 저장된 이미지의 이름을 조회합니다.
     *
     * @param entityId 엔티티의 고유 ID
     * @param entityType 엔티티 타입 (예: "classroom", "user" 등)
     * @return 저장된 이미지의 이름
     */
    String getSaveImage(int entityId, String entityType);
}
