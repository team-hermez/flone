package org.hermez.filter.exception;

/**
 * 관리자 권한 접근 제어와 관련된 예외를 나타내는 클래스입니다.
 * 이 예외는 관리자가 아닌 사용자가 관리자 전용 페이지에 접근할 때 발생합니다.
 *
 * @author 김혁진
 */
public class AdminFilterException extends RuntimeException {

    /**
     * 예외를 생성합니다. 메시지만 포함됩니다.
     *
     * @param message 예외 메시지
     */
    public AdminFilterException(String message) {
        super(message);
    }

}
