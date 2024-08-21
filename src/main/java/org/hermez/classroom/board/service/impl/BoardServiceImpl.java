package org.hermez.classroom.board.service.impl;

import org.hermez.classroom.board.dto.BoardEditRequest;
import org.hermez.classroom.board.dto.BoardListResponse;
import org.hermez.classroom.board.dto.BoardRegisterRequest;
import org.hermez.classroom.board.mapper.BoardMapper;
import org.hermez.classroom.board.model.Board;
import org.hermez.classroom.board.service.BoardService;
import org.hermez.classroom.classroom.mapper.ClassroomMapper;
import org.springframework.stereotype.Service;

/**
 * 게시판 서비스 구현체입니다.
 *
 * @author 김혁진
 */
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final ClassroomMapper classroomMapper;

    /**
     * 생성자
     *
     * @param boardMapper 게시판 매퍼
     * @param classroomMapper 클래스룸 매퍼
     */
    public BoardServiceImpl(BoardMapper boardMapper, ClassroomMapper classroomMapper) {
        this.boardMapper = boardMapper;
        this.classroomMapper = classroomMapper;
    }

    /**
     * 특정 강의실의 게시판 목록을 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @return 게시판 목록
     */
    @Override
    public BoardListResponse getBoardsByClassroomId(int classroomId) {
        BoardListResponse boardListResponse = new BoardListResponse();
        boardListResponse.setClassroomId(classroomId);
        boardListResponse.setBoards(boardMapper.selectBoardsByClassroomId(classroomId));
        boardListResponse.setVideoLink(classroomMapper.selectVideoLinkByClassroomId(classroomId));
        return boardListResponse;
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
