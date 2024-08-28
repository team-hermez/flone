package org.hermez.image.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 이미지 정보를 나타내는 모델 클래스입니다.
 *
 * <p>이 클래스는 이미지의 메타데이터를 저장합니다. 메타데이터에는 이미지의 ID, 타입, 원본 파일명, 저장된 파일명, 파일 경로가 포함됩니다.</p>
 *
 * @author 김혁진
 */
@Getter
@Setter
public class Image {

    private int entityId;
    private String entityType;
    private String originalName;
    private String saveName;
    private String filePath;

    /**
     * 기본 생성자입니다.
     *
     * @param entityId 이미지가 연관된 엔티티의 ID
     * @param entityType 이미지가 연관된 엔티티의 타입
     */
    public Image(int entityId, String entityType) {
        this.entityId = entityId;
        this.entityType = entityType;
    }

    /**
     * 모든 필드를 초기화하는 생성자입니다.
     *
     * @param entityId 이미지가 연관된 엔티티의 ID
     * @param entityType 이미지가 연관된 엔티티의 타입
     * @param originalName 원본 파일명
     * @param saveName 저장된 파일명
     * @param filePath 파일 경로
     */
    public Image(int entityId, String entityType, String originalName, String saveName, String filePath) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.originalName = originalName;
        this.saveName = saveName;
        this.filePath = filePath;
    }
}
