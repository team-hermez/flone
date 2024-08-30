package org.hermez.classroom.board.service.impl;

import org.hermez.classroom.board.dto.BoardEditRequest;
import org.hermez.classroom.board.dto.BoardListResponse;
import org.hermez.classroom.board.dto.BoardRegisterRequest;
import org.hermez.classroom.board.exception.BoardServiceException;
import org.hermez.classroom.board.mapper.BoardMapper;
import org.hermez.classroom.board.model.Board;
import org.hermez.classroom.board.service.BoardService;
import org.hermez.classroom.classroom.mapper.ClassroomMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시판 서비스 구현체입니다.
 *
 * 게시판과 관련된 CRUD 기능을 제공합니다.
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
     * @throws BoardServiceException 게시판 목록을 불러오는 중 오류가 발생한 경우
     */
    @Override
    public BoardListResponse getBoardsByClassroomId(int classroomId) {
        try {
            BoardListResponse boardListResponse = new BoardListResponse();
            boardListResponse.setClassroomId(classroomId);
            boardListResponse.setBoards(boardMapper.selectBoardsByClassroomId(classroomId));
            boardListResponse.setVideoLink(classroomMapper.selectVideoLinkByClassroomId(classroomId));
            return boardListResponse;
        } catch (Exception e) {
            throw new BoardServiceException("게시판 목록을 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 새로운 게시판을 등록합니다.
     *
     * @param request 게시판 등록 요청 데이터
     * @throws BoardServiceException 게시판을 등록하는 중 오류가 발생한 경우
     */
    @Override
    @Transactional
    public void registerBoard(BoardRegisterRequest request) {
        try {
            boardMapper.insertBoard(request);
        } catch (Exception e) {
            throw new BoardServiceException("게시판을 등록하는 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 게시판의 정보를 수정합니다.
     *
     * @param request 게시판 수정 요청 데이터
     * @throws BoardServiceException 게시판 정보를 수정하는 중 오류가 발생한 경우
     */
    @Override
    @Transactional
    public void updateBoard(BoardEditRequest request) {
        try {
            boardMapper.updateBoard(request);
        } catch (Exception e) {
            throw new BoardServiceException("게시판 정보를 수정하는 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 특정 게시판의 상세 정보를 조회합니다.
     *
     * @param boardId 게시판 ID
     * @return 게시판 상세 정보
     * @throws BoardServiceException 게시판 정보를 조회하는 중 오류가 발생한 경우
     */
    @Override
    public Board getBoardById(int boardId) {
        try {
            return boardMapper.selectBoard(boardId);
        } catch (Exception e) {
            throw new BoardServiceException("게시판 정보를 조회하는 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 특정 게시판을 삭제합니다.
     *
     * @param boardId 게시판 ID
     * @throws BoardServiceException 게시판을 삭제하는 중 오류가 발생한 경우
     */
    @Override
    @Transactional
    public void deleteBoard(int boardId) {
        try {
            boardMapper.deleteBoard(boardId);
        } catch (Exception e) {
            throw new BoardServiceException("게시판을 삭제하는 중 오류가 발생했습니다.", e);
        }
    }
}
