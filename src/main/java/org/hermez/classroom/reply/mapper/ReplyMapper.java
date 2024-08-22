package org.hermez.classroom.reply.mapper;

import org.apache.ibatis.annotations.*;
import org.hermez.classroom.reply.dto.ReplyRegisterRequest;
import org.hermez.classroom.reply.model.Reply;

import java.util.List;

/**
 * 댓글 데이터베이스 작업을 처리하는 매퍼 인터페이스입니다.
 *
 * @author 김혁진
 */
@Mapper
public interface ReplyMapper {

    /**
     * 특정 게시판에 속한 모든 댓글을 조회합니다.
     *
     * @param boardId 댓글을 조회할 게시판의 ID
     * @return 해당 게시판에 속한 댓글 목록
     */
    @Select("SELECT " +
            "reply_id AS replyId, " +
            "board_id AS boardId, " +
            "reply_content AS content, " +
            "created_at AS createdAt " +
            "FROM reply " +
            "WHERE board_id = #{boardId} " +
            "ORDER BY created_at DESC")
    List<Reply> selectRepliesByBoardId(int boardId);

    /**
     * 새로운 댓글을 데이터베이스에 삽입합니다.
     *
     * @param request 댓글 등록 요청 데이터를 담고 있는 DTO
     */
    @Insert("INSERT INTO reply (board_id, reply_content, member_id, created_at) " +
            "VALUES (#{boardId}, #{content}, 1, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "replyId")
    void insertReply(ReplyRegisterRequest request);

    /**
     * 특정 댓글을 데이터베이스에서 삭제합니다.
     *
     * @param replyId 삭제할 댓글의 ID
     * @return
     */
    @Delete("DELETE FROM reply WHERE reply_id = #{replyId}")
    int deleteReply(int replyId);
}
