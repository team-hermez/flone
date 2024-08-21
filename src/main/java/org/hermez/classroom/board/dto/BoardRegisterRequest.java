package org.hermez.classroom.board.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 등록 요청 데이터 전송 객체입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class BoardRegisterRequest {

    private int classroomId;
    private String boardTitle;
    private String boardContent;

    public BoardRegisterRequest() {}

    /**
     * BoardRegisterRequest 생성자 입니다.
     *
     * @param classroomId 강의실 ID
     * @param boardTitle 게시판 제목
     * @param boardContent 게시판 내용
     */
    public BoardRegisterRequest(int classroomId, String boardTitle, String boardContent) {
        this.classroomId = classroomId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
