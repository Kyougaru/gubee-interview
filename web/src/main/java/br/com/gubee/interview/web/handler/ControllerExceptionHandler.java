package br.com.gubee.interview.web.handler;

import br.com.gubee.interview.common.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.common.exceptions.StandardError;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e) {
        String error = "Method argument not valid";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getFieldError().getField() + ": " + e.getFieldError().getDefaultMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DbActionExecutionException.class)
    public ResponseEntity<StandardError> dbActionExecution(MethodArgumentNotValidException e) {
        String error = "Database action execution error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}
