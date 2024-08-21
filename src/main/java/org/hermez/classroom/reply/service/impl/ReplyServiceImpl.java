package org.hermez.classroom.reply.service.impl;

import org.hermez.classroom.reply.dto.ReplyRegisterRequest;
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
        return replyMapper.selectRepliesByBoardId(boardId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerReply(ReplyRegisterRequest request) {
        replyMapper.insertReply(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReply(int replyId) {
        replyMapper.deleteReply(replyId);
    }
}
