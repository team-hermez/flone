package org.hermez.member.exception;

/**
 * 회원 서비스 예외 DTO 입니다.
 *
 * 이 클래스는 회원 서비스에서 발생하는 예외를 처리하기 위해 사용됩니다.
 * {@link RuntimeException}을 상속하여 런타임 예외로 처리됩니다.
 *
 * <p>이 예외는 오류 메시지와 원인 예외를 포함할 수 있습니다. 이를 통해
 * 회원 서비스에서 발생한 문제에 대한 구체적인 정보를 제공할 수 있습니다.</p>
 *
 * @author 김다은
 */
public class MemberServiceException extends RuntimeException {

    /**
     * 생성자 - 예외 메시지와 원인 예외를 포함합니다.
     *
     * @param message 예외의 상세 메시지
     * @param cause   원인 예외 (null 허용)
     */
    public MemberServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 생성자 - 예외 메시지만 포함합니다.
     *
     * @param message 예외의 상세 메시지
     */
    public MemberServiceException(String message) {
        super(message);
    }
}
