package org.hermez.oauth.exception;


public class OauthServiceException extends RuntimeException {
    public OauthServiceException(String message) {
        super(message);
    }
    public OauthServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
