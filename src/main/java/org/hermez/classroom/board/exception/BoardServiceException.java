package org.hermez.classroom.board.exception;

/**
 * 게시판 서비스에서 발생하는 예외를 나타냅니다.
 */
public class BoardServiceException extends RuntimeException {

    /**
     * 예외 메시지를 포함한 생성자입니다.
     *
     * @param message 예외 메시지
     */
    public BoardServiceException(String message) {
        super(message);
    }

    /**
     * 예외 메시지와 원인을 포함한 생성자입니다.
     *
     * @param message 예외 메시지
     * @param cause   예외의 원인
     */
    public BoardServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
