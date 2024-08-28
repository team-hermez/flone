package org.hermez.image.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hermez.image.model.Image;

/**
 * 이미지 관련 데이터베이스 작업을 처리하는 매퍼 인터페이스입니다.
 *
 * @author 김혁진
 */
@Mapper
public interface ImageMapper {

    /**
     * 이미지 정보를 데이터베이스에 삽입합니다.
     *
     * @param image 삽입할 이미지 엔티티 객체
     */
    @Insert("INSERT INTO image (entity_id, entity_type, original_name, save_name, file_path) " +
            "VALUES (#{entityId}, #{entityType}, #{originalName}, #{saveName}, #{filePath})")
    void insertImage(Image image);

    /**
     * 주어진 엔티티 ID와 타입에 대한 저장된 이미지의 이름을 조회합니다.
     *
     * @param entityId 엔티티의 고유 ID
     * @param entityType 엔티티 타입 (예: "classroom", "user" 등)
     * @return 저장된 이미지의 이름
     */
    @Select("SELECT save_name FROM image WHERE entity_id = #{entityId} AND entity_type = #{entityType}")
    String selectImageById(@Param("entityId") int entityId, @Param("entityType") String entityType);
}
