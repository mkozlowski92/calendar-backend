package com.calendarbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Rest Exception Handler.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Rest Exception Handler.
     * @param exception - Exception type.
     * @param request - Request.
     * @return - ResponseEntity with status code and error response.
     */
    @ExceptionHandler({
            AccountDoesNotExist.class,
            IncorrectCredentials.class,
            MainAccountDoesNotExist.class,
            MainAccountIsNotConnected.class,
            NotInRange.class,
            NotMainAccount.class,
            PartnerAccountDoesNotExist.class,
            PartnerAccountIsMainAccount.class,
            PartnerAlreadyTaken.class,
            PeriodSameStartAndEnd.class,
            SettingsMissing.class,
            TooShortCredentials.class,
            UserNameExists.class
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