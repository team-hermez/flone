package org.hermez.reservation.exception;

public class NoSuchUniqueCourseTimeException extends
    RuntimeException {

  public NoSuchUniqueCourseTimeException() {
    super();
  }

  public NoSuchUniqueCourseTimeException(String message) {
    super(message);
  }

  public NoSuchUniqueCourseTimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoSuchUniqueCourseTimeException(Throwable cause) {
    super(cause);
  }
}
