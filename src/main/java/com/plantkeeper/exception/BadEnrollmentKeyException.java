package com.plantkeeper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Enrollment Key provided does not match")
public class BadEnrollmentKeyException extends RuntimeException {

}
