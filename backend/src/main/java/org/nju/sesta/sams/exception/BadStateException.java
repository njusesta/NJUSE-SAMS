package org.nju.sesta.sams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Current state is not excepted")
public class BadStateException extends RuntimeException {
}
