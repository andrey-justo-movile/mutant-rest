package com.justo.mutant.api.rest.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.justo.mutant.log.Log;


@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Void> handleConflict(Exception ex, WebRequest request) {
        Log.EXCEPTION.error("Something really bad happened from: {}", request, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
    }
    
    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<Void> handleThrowableConflict(Throwable ex, WebRequest request) {
        Log.EXCEPTION.error("Something really bad happened from: {}", request, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
    }
   
    
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Void> handleWrongArguments(Exception ex, WebRequest request) {
        Log.EXCEPTION.error("Wrong arguments: {}", request, ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
        
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Log.EXCEPTION.error("Request not found: {}", request, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Log.EXCEPTION.error("Something really bad happened from: {}", request, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    
}
