package org.hermez.image.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 이미지 관련 데이터베이스 작업을 처리하는 매퍼 인터페이스입니다.
 *
 * <p>이 인터페이스는 MyBatis를 통해 이미지 데이터를 데이터베이스에 삽입하는 기능을 제공합니다.</p>
 *
 * @author 김혁진
 */
@Mapper
public interface ImageMapper {

    /**
     * 이미지 정보를 데이터베이스에 삽입합니다.
     *
     * @param entityId 이미지를 등록할 엔터티의 ID
     * @param entityType 엔터티의 타입 (예: 'classroom', 'user' 등)
     * @param originalName 원본 파일 이름
     * @param saveName 저장된 파일 이름
     * @param filePath 파일 경로
     */
    @Insert("INSERT INTO image (entity_id, entity_type, original_name, save_name, file_path) " +
            "VALUES (#{entityId}, #{entityType}, #{originalName}, #{saveName}, #{filePath})")
    void insertImage(@Param("entityId") int entityId,
                     @Param("entityType") String entityType,
                     @Param("originalName") String originalName,
                     @Param("saveName") String saveName,
                     @Param("filePath") String filePath);
}
