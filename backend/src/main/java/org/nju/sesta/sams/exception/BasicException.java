package org.nju.sesta.sams.exception;

import org.springframework.http.HttpStatus;

public class BasicException extends RuntimeException {
    private HttpStatus status;

    public BasicException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public BasicException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
