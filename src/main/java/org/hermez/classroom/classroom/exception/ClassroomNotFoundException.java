package org.hermez.classroom.classroom.exception;

/**
 * 강의실이 존재하지 않을 때 발생하는 예외입니다.
 *
 * @Author 김혁진
 */
public class ClassroomNotFoundException extends RuntimeException {
    public ClassroomNotFoundException(String message) {
        super(message);
    }
}
