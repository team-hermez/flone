package org.hermez.classroom.reply.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 댓글 엔티티 클래스입니다.
 *
 * @author 김혁진
 */
@Getter
@Setter
public class Reply {

    private int replyId;
    private int boardId;
    private int memberId;
    private String content;
    private LocalDateTime createdAt;

    /**
     * 댓글 필드 생성자입니다.
     *
     * @param replyId    댓글의 고유 ID
     * @param boardId    댓글이 속한 게시판의 ID
     * @param content    댓글의 내용
     * @param createdAt  댓글이 작성된 일시
     */
    public Reply(int replyId, int boardId, String content, LocalDateTime createdAt) {
        this.replyId = replyId;
        this.boardId = boardId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
