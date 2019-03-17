package org.nju.sesta.sams.advice;

import org.nju.sesta.sams.exception.ErrorDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //=ControllerAdvice+ResponseBody. CA注解说明该类全局处理抛出的异常
public class GlobalAdvice {
    @ExceptionHandler(Exception.class)
    public ErrorDetail entryNotFoundHandler(Exception ex) {
        return new ErrorDetail("exception", ex.getMessage());
    }
}
