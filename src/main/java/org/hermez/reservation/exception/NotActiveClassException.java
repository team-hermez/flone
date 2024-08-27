package org.hermez.reservation.exception;
/**
 * 수강 시작일이 지난 강의에 대해 발생하는 예외입니다.
 *
 * 이 예외는 사용자가 수강을 시도할 때 강의의 시작일이 이미 지나서 활성화되지 않은 강의에 대해 발생합니다.
 * 예를 들어, 사용자가 시작일이 지난 강의를 수강하려고 할 때 이 예외가 발생할 수 있습니다.
 *
 * <p>예를 들어, 다음과 같은 메시지를 사용할 수 있습니다:
 * <pre>
 * throw new NotActiveClassException("수강 시작일이 지난 강의입니다.");
 * </pre>
 *
 * @see RuntimeException
 *
 * @author 허상범
 */
public class NotActiveClassException extends RuntimeException {

  /**
   * 지정된 메시지로 {@code NotActiveClassException}을 생성합니다.
   *
   * @param message 예외에 대한 상세 설명을 포함하는 메시지.
   *                예를 들어, "수강 시작일이 지난 강의입니다."
   */
  public NotActiveClassException(String message) {
    super(message);
  }

}
