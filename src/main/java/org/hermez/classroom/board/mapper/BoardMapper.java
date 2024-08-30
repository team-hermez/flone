package org.hermez.classroom.board.mapper;

import org.apache.ibatis.annotations.*;
import org.hermez.classroom.board.dto.BoardEditRequest;
import org.hermez.classroom.board.dto.BoardRegisterRequest;
import org.hermez.classroom.board.model.Board;

import java.util.List;

/**
 * 게시판 관련 데이터베이스 작업을 처리하는 Mapper 인터페이스입니다.
 *
 * @author 김혁진
 */
@Mapper
public interface BoardMapper {

    /**
     * 특정 강의실의 게시판 목록을 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @return 게시판 목록
     */
    @Select("SELECT BOARD_ID as boardId, CLASSROOM_ID as classroomId, BOARD_TITLE as boardTitle, " +
            "BOARD_CONTENT as boardContent, CREATED_AT as createdAt " +
            "FROM board WHERE classroom_id = #{classroomId} " +
            "ORDER BY BOARD_ID DESC")
    List<Board> selectBoardsByClassroomId(@Param("classroomId") int classroomId);

    /**
     * 새로운 게시판을 등록합니다.
     *
     * @param request 게시판 등록 요청 데이터
     */
    @Insert("INSERT INTO board (classroom_id, board_title, board_content, created_at) " +
            "VALUES (#{classroomId}, #{boardTitle}, #{boardContent}, NOW())")
    void insertBoard(BoardRegisterRequest request);

    /**
     * 게시판의 정보를 수정합니다.
     *
     * @param request 게시판 수정 요청 데이터
     */
    @Update("UPDATE board SET board_title = #{boardTitle}, board_content = #{boardContent} " +
            "WHERE board_id = #{boardId}")
    void updateBoard(BoardEditRequest request);

    /**
     * 특정 게시판의 상세 정보를 조회합니다.
     *
     * @param boardId 게시판 ID
     * @return 게시판 상세 정보
     */
    @Select("SELECT BOARD_ID as boardId, CLASSROOM_ID as classroomId, BOARD_TITLE as boardTitle" +
            ", BOARD_CONTENT as boardContent, CREATED_AT as createdAt " +
            "FROM board WHERE board_id = #{boardId}")
    Board selectBoard(int boardId);

    /**
     * 특정 게시판을 삭제합니다.
     *
     * @param boardId 게시판 ID
     */
    @Delete("DELETE FROM board WHERE board_id = #{boardId}")
    void deleteBoard(int boardId);
}
