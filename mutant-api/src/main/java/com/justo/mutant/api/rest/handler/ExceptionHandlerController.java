package com.justo.mutant.api.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.justo.mutant.log.Log;


@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Void> handleWrongArguments(Exception ex, WebRequest request) {
        Log.EXCEPTION.error("Wrong arguments: {}", request, ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }  
    
}
