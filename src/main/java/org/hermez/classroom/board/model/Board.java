package org.hermez.classroom.board.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 게시판 모델 클래스입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class Board {

    private int boardId;
    private int classroomId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createdAt;

    public Board() {}

    /**
     * Board 생성자 입니다.
     *
     * @param boardId 게시판 ID
     * @param classroomId 강의실 ID
     * @param boardTitle 게시판 제목
     * @param boardContent 게시판 내용
     * @param createdAt 게시판 생성일시
     */
    public Board(int boardId, int classroomId, String boardTitle, String boardContent, LocalDateTime createdAt) {
        this.boardId = boardId;
        this.classroomId = classroomId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.createdAt = createdAt;
    }
}
