package com.plantkeeper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Email supplied fails regular expression check")
public class BadEmailException extends RuntimeException {}
