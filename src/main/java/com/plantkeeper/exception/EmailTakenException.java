package com.plantkeeper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Email already in use")
public class EmailTakenException extends RuntimeException{

}
