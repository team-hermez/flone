package org.hermez.member.exception;

public class MemberServiceException extends RuntimeException {
    public MemberServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberServiceException(String message) {
        super(message);
    }
}
