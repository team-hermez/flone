package org.hermez.classroom.board.service;

import org.hermez.classroom.board.dto.BoardEditRequest;
import org.hermez.classroom.board.dto.BoardRegisterRequest;
import org.hermez.classroom.board.model.Board;

import java.util.List;

/**
 * 게시판 서비스 인터페이스입니다.
 *
 * @author 김혁진
 */
public interface BoardService {

    /**
     * 특정 강의실의 게시판 목록을 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @return 게시판 목록
     */
    List<Board> getBoardsByClassroomId(int classroomId);

    /**
     * 새로운 게시판을 등록합니다.
     *
     * @param request 게시판 등록 요청 데이터
     */
    void registerBoard(BoardRegisterRequest request);

    /**
     * 게시판의 정보를 수정합니다.
     *
     * @param request 게시판 수정 요청 데이터
     */
    void updateBoard(BoardEditRequest request);

    /**
     * 특정 게시판의 상세 정보를 조회합니다.
     *
     * @param boardId 게시판 ID
     * @return 게시판 상세 정보
     */
    Board getBoardById(int boardId);

    /**
     * 특정 게시판을 삭제합니다.
     *
     * @param boardId 게시판 ID
     */
    void deleteBoard(int boardId);
}
