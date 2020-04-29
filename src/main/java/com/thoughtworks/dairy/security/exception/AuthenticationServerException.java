package com.thoughtworks.dairy.security.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class AuthenticationServerException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public AuthenticationServerException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
