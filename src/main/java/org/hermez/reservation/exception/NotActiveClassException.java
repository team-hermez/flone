package org.hermez.reservation.exception;

public class NotActiveClassException extends RuntimeException {

  public NotActiveClassException() {
    super();
  }

  public NotActiveClassException(String message) {
    super(message);
  }

  public NotActiveClassException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotActiveClassException(Throwable cause) {
    super(cause);
  }
}
