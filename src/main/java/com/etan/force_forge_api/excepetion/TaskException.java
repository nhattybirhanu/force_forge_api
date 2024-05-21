package com.etan.force_forge_api.excepetion;

public class TaskException extends RuntimeException{

    ExceptionResponse exceptionResponse;


    public TaskException(ExceptionResponse exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }
}
