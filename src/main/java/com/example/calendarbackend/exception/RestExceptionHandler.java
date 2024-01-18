package com.example.calendarbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            TooShortCredentials.class,
            IncorrectCredentials.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

}