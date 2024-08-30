package org.hermez.classroom.reply.service.impl;

import org.hermez.classroom.reply.dto.ReplyRegisterRequest;
import org.hermez.classroom.reply.exception.ReplyNotFoundException;
import org.hermez.classroom.reply.mapper.ReplyMapper;
import org.hermez.classroom.reply.model.Reply;
import org.hermez.classroom.reply.service.ReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 댓글 서비스의 구현 클래스입니다.
 *
 * @author 김혁진
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    /**
     * ReplyServiceImpl의 생성자입니다.
     *
     * @param replyMapper 댓글 관련 데이터베이스 작업을 처리하는 매퍼
     */
    public ReplyServiceImpl(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reply> getRepliesByBoardId(int boardId) {
        List<Reply> replies = replyMapper.selectRepliesByBoardId(boardId);
        return replies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerReply(ReplyRegisterRequest request) {
        try {
            replyMapper.insertReply(request);
        } catch (Exception e) {
            throw new RuntimeException("댓글 등록 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReply(int replyId) {
        int deletedRows = replyMapper.deleteReply(replyId);
        if (deletedRows == 0) {
            throw new ReplyNotFoundException("삭제할 댓글이 존재하지 않습니다. Reply ID: " + replyId);
        }
    }
}
