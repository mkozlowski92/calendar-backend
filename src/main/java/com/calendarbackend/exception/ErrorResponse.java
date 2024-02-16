package com.calendarbackend.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ErrorResponse class.
 */
@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    /**
     * ErrorResponse constructor.
     * @param timestamp - Timestamp.
     * @param status - Status.
     * @param error - Error.
     * @param path - Path.
     */
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}