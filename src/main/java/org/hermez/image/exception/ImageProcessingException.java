package org.hermez.image.exception;

/**
 * 이미지 처리와 관련된 예외를 나타내는 클래스입니다.
 * 이 예외는 이미지 파일의 처리 중 오류가 발생할 때 사용됩니다.
 *
 * @author 김혁진
 */
public class ImageProcessingException extends RuntimeException {

    /**
     * 예외를 생성합니다. 메시지만 포함됩니다.
     *
     * @param message 예외 메시지
     */
    public ImageProcessingException(String message) {
        super(message);
    }

    /**
     * 예외를 생성합니다. 메시지와 원인 예외를 포함합니다.
     *
     * @param message 예외 메시지
     * @param cause 원인 예외
     */
    public ImageProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
