package com.thoughtworks.dairy.security.controller;

import com.thoughtworks.dairy.security.exception.AuthenticationServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationServerException.class)
    public final ResponseEntity<Object> handleAllExceptions(AuthenticationServerException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),ex.getHttpStatus());
    }
}
