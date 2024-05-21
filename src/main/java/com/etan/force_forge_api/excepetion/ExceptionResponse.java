package com.etan.force_forge_api.excepetion;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {
    String error;
    String messageDescription;

    HttpStatus httpStatus;

    LocalDateTime timeStamp;

    public ExceptionResponse(String error, String messageDescription, HttpStatus httpStatus, LocalDateTime timeStamp) {
        this.error = error;
        this.messageDescription = messageDescription;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }
}
