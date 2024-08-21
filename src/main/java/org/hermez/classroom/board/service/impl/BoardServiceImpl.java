package org.hermez.classroom.board.service.impl;

import org.hermez.classroom.board.dto.BoardEditRequest;
import org.hermez.classroom.board.dto.BoardRegisterRequest;
import org.hermez.classroom.board.mapper.BoardMapper;
import org.hermez.classroom.board.model.Board;
import org.hermez.classroom.board.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판 서비스 구현체입니다.
 *
 * @author 김혁진
 */
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    /**
     * 생성자
     *
     * @param boardMapper 게시판 매퍼
     */
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    /**
     * 특정 강의실의 게시판 목록을 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @return 게시판 목록
     */
    @Override
    public List<Board> getBoardsByClassroomId(int classroomId) {
        return boardMapper.selectBoardsByClassroomId(classroomId);
    }

    /**
     * 새로운 게시판을 등록합니다.
     *
     * @param request 게시판 등록 요청 데이터
     */
    @Override
    public void registerBoard(BoardRegisterRequest request) {
        boardMapper.insertBoard(request);
    }

    /**
     * 게시판의 정보를 수정합니다.
     *
     * @param request 게시판 수정 요청 데이터
     */
    @Override
    public void updateBoard(BoardEditRequest request) {
        boardMapper.updateBoard(request);
    }

    /**
     * 특정 게시판의 상세 정보를 조회합니다.
     *
     * @param boardId 게시판 ID
     * @return 게시판 상세 정보
     */
    @Override
    public Board getBoardById(int boardId) {
        return boardMapper.selectBoard(boardId);
    }

    /**
     * 특정 게시판을 삭제합니다.
     *
     * @param boardId 게시판 ID
     */
    @Override
    public void deleteBoard(int boardId) {
        boardMapper.deleteBoard(boardId);
    }
}
