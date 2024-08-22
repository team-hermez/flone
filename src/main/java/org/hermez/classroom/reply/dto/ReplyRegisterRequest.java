package org.hermez.classroom.reply.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 댓글 등록 요청을 위한 DTO 클래스입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class ReplyRegisterRequest {

    private int classroomId;
    private int boardId;
    private String content;
    private String author;

    /**
     * ReplyRegisterRequest 모든 필드를 초기화하는 생성자입니다.
     *
     * @param classroomId 겟시판이 속한 클래스룸의 ID
     * @param boardId 댓글이 속한 게시판의 ID
     * @param content 댓글의 내용
     * @param author  댓글 작성자의 이름
     */
    public ReplyRegisterRequest(int classroomId, int boardId, String content, String author) {
        this.classroomId = classroomId;
        this.boardId = boardId;
        this.content = content;
        this.author = author;
    }

}
