package org.hermez.course.exception;

/**
 * 강의 등록의 시간관련 예외 처리입니다.
 * @author 엄진수
 */

public class CourseRegisterTimeException extends RuntimeException {
    public CourseRegisterTimeException(String message) { super(message);}
}