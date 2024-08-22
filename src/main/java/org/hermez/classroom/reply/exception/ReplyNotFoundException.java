package org.hermez.classroom.reply.exception;

/**
 * 댓글이 존재하지 않을 때 발생하는 예외입니다.
 *
 * @author 김혁진
 */
public class ReplyNotFoundException extends RuntimeException {
    public ReplyNotFoundException(String message) {
        super(message);
    }
}
