package org.nju.sesta.sams.exception;

public class BasicException extends RuntimeException {
    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException(String msg) {
        super(msg);
    }
}
