package org.hermez.classroom.reply.service;

import org.hermez.classroom.reply.dto.ReplyRegisterRequest;
import org.hermez.classroom.reply.model.Reply;

import java.util.List;

/**
 * 댓글 서비스의 인터페이스입니다.
 *
 * @author 김혁진
 */
public interface ReplyService {

    /**
     * 특정 게시판의 댓글 목록을 가져옵니다.
     *
     * @param boardId 댓글을 가져올 게시판의 ID
     * @return 해당 게시판에 속한 댓글 목록
     */
    List<Reply> getRepliesByBoardId(int boardId);

    /**
     * 새로운 댓글을 등록합니다.
     *
     * @param request 댓글 등록 요청을 담은 DTO
     */
    void registerReply(ReplyRegisterRequest request);

    /**
     * 특정 댓글을 삭제합니다.
     *
     * @param replyId 삭제할 댓글의 ID
     */
    void deleteReply(int replyId);
}
