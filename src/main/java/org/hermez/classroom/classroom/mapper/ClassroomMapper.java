package org.hermez.classroom.classroom.mapper;

import org.apache.ibatis.annotations.*;
import org.hermez.classroom.classroom.dto.ClassroomCardResponse;
import org.hermez.classroom.classroom.dto.ClassroomRegisterRequest;

import java.util.List;

/**
 * 클래스룸 매퍼 인터페이스입니다. 클래스룸 관련 데이터베이스 작업을 정의합니다.
 *
 * @author 김혁진
 */
@Mapper
public interface ClassroomMapper {

    /**
     * 특정 강의에 대한 클래스룸 카드 리스트를 가져옵니다.
     *
     * @param courseId 강의의 고유 ID
     * @param offset 결과 집합의 시작 행 번호
     * @param itemsPerPage 한 페이지에 표시할 항목 수
     * @return {@link ClassroomCardResponse} 객체의 리스트
     */
    @Select("SELECT " +
            "c.classroom_id AS classroomId, " +
            "c.classroom_name AS classroomName, " +
            "c.description AS description, " +
            "c.created_at AS createdAt, " +
            "i.save_name AS classroomImage " +
            "FROM classroom c " +
            "LEFT JOIN image i ON c.classroom_id = i.entity_id " +
            "WHERE (c.course_id = #{courseId} " +
            "AND i.entity_type = 'CLASSROOM') " +
            "ORDER BY c.created_at DESC " +
            "LIMIT #{offset}, #{itemsPerPage}")
    List<ClassroomCardResponse> selectClassroomList(@Param("courseId") int courseId, @Param("offset") int offset, @Param("itemsPerPage") int itemsPerPage);

    /**
     * 클래스룸의 영상 링크를 가져옵니다.
     *
     * @param classroomId 클래스룸의 고유 ID
     * @return 등록된 영상 링크
     */
    @Select("SELECT video_link AS videoLink FROM classroom WHERE classroom_id = #{classroomId}")
    String selectVideoLinkByClassroomId(int classroomId);

    /**
     * 특정 강의에 포함된 클래스룸의 수를 반환합니다.
     *
     * @param courseId 강의의 고유 ID
     * @return 강의에 포함된 클래스룸의 수
     */
    @Select("SELECT COUNT(*) FROM classroom WHERE course_id = #{courseId}")
    int countClassrooms(int courseId);

    /**
     * 새로운 클래스룸을 등록합니다.
     *
     * @param classroomRegisterRequest 등록할 클래스룸의 정보를 담고 있는 요청 객체
     * @return 삽입된 행의 수
     */
    @Insert("INSERT INTO classroom (course_id, classroom_name, description, created_at, video_link) " +
            "VALUES (#{courseId}, #{classroomName}, #{description}, NOW(), #{videoLink})")
    @Options(useGeneratedKeys = true, keyProperty = "classroomId")
    int insertClassroom(ClassroomRegisterRequest classroomRegisterRequest);

    /**
     * 클래스룸 ID를 기반으로 해당 클래스룸을 삭제합니다.
     *
     * @param classroomId 삭제할 클래스룸의 고유 ID
     * @return 삭제된 행의 수
     */
    @Delete("DELETE FROM classroom WHERE classroom_id = #{classroomId}")
    int deleteClassroomById(@Param("classroomId") int classroomId);

    /**
     * 전체 클래스룸의 총 개수를 반환합니다.
     *
     * @return 전체 클래스룸의 총 개수
     */
    @Select("SELECT COUNT(*) FROM classroom")
    int selectCountAll();
}