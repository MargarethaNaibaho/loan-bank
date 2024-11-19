package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> responseStatusException(ResponseStatusException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(e.getStatusCode().value())
                .message(e.getReason())
                .build();

        return ResponseEntity
                .status(e.getStatusCode())
                .body(response);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid type")
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> badCredentialsException(BadCredentialsException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid credential")
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> accessDeniedException(AccessDeniedException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid credential")
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}
