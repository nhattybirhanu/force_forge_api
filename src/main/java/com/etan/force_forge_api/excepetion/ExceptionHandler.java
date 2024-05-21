package com.etan.force_forge_api.excepetion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            errors.add(objectError.getDefaultMessage());
        });
    return ResponseEntity.badRequest().body(errors);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TaskException.class)
    public ResponseEntity<ExceptionResponse> handleTaskException(TaskException taskException){
        return ResponseEntity.status(taskException.exceptionResponse.httpStatus).body(taskException.exceptionResponse);
    }
}
