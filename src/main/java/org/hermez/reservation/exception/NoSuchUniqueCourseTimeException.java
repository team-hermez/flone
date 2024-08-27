package org.hermez.reservation.exception;

/**
 * 특정 유니크 강의 시간 정보가 존재하지 않을 때 발생하는 예외입니다.
 *
 * 이 예외는 유니크한 강의 시간 정보를 찾을 수 없거나, 이미 수강 중인 강의와 관련된 상황에서 발생합니다.
 * 예를 들어, 사용자가 이미 수강 중인 강의에 대해 추가로 수강을 시도할 때 이 예외가 발생할 수 있습니다.
 *
 * <p>예를 들어, 다음과 같은 메시지를 사용할 수 있습니다:
 * <pre>
 * throw new NoSuchUniqueCourseTimeException("이미 수강 중인 강의입니다.");
 * </pre>
 *
 * @see RuntimeException
 *
 * @author 허상범
 */
public class NoSuchUniqueCourseTimeException extends
    RuntimeException {

  /**
   * 지정된 메시지로 {@code NoSuchUniqueCourseTimeException}을 생성합니다.
   *
   * @param message 예외에 대한 상세 설명을 포함하는 메시지.
   *                예를 들어, "이미 수강 중인 강의입니다."
   */
  public NoSuchUniqueCourseTimeException(String message) {
    super(message);
  }

  /**
   * 지정된 원인으로 {@code NoSuchUniqueCourseTimeException}을 생성합니다.
   *
   * @param cause 이 예외의 원인이 되는 다른 예외.
   */
  public NoSuchUniqueCourseTimeException(Throwable cause) {
    super(cause);
  }
}
