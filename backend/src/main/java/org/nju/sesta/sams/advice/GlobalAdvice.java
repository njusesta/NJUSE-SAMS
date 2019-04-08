package org.nju.sesta.sams.advice;

import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice //=ControllerAdvice+ResponseBody. CA注解说明该类全局处理抛出的异常
public class GlobalAdvice {
    /**
     * 处理未预料到的异常(即BasicException之外的异常)
     *
     * @param ex 未预料的的异常
     * @return 用ResponseEntity封装的状态码与异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();//打印异常信息供debug使用
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetail("exception", ex.getMessage()));
    }

    /**
     * 处理各处抛出的BasicException
     *
     * @param ex 封装了状态码与异常信息的BasicException
     * @return 向前端返回相关信息
     */
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<?> handleBasicException(BasicException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
