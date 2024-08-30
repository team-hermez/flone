package org.hermez.oauth.exception;

/**
 * OAuth 서비스에서 발생할 수 있는 예외를 처리하는 사용자 정의 예외 클래스.
 *
 * 이 클래스는 OAuth 처리 중 발생하는 오류를 명확하게 전달하기 위해 사용됩니다.
 * {@link RuntimeException}을 상속받으며, 예외 메시지와 원인에 대한 정보를 포함할 수 있습니다.
 *
 * @author 김다은
 */
public class OauthServiceException extends RuntimeException {

    /**
     * 예외 메시지를 사용하여 {@code OauthServiceException}을 생성합니다.
     *
     * @param message 예외 메시지
     */
    public OauthServiceException(String message) {
        super(message);
    }

    /**
     * 예외 메시지와 원인을 사용하여 {@code OauthServiceException}을 생성합니다.
     *
     * @param message 예외 메시지
     * @param cause 예외의 원인
     */
    public OauthServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
