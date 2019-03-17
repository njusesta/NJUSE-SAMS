package org.nju.sesta.sams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "语法错误")
public class BadFormatException extends RuntimeException {
}
