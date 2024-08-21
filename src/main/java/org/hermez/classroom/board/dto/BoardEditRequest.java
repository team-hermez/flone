package org.hermez.classroom.board.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 수정 요청 데이터 요청 dto 입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class BoardEditRequest {

    private int boardId;
    private int classroomId;
    private String boardTitle;
    private String boardContent;

    /**
     * BoardEditRequest 생성자 입니다.
     *
     * @param boardId 게시판 ID
     * @param classroomId 강의실 ID
     * @param boardTitle 게시판 제목
     * @param boardContent 게시판 내용
     */
    public BoardEditRequest(int boardId, int classroomId, String boardTitle, String boardContent) {
        this.boardId = boardId;
        this.classroomId = classroomId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
