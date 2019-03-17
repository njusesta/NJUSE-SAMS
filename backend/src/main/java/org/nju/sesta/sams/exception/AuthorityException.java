package org.nju.sesta.sams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Authority mistake")
public class AuthorityException extends RuntimeException {
}
