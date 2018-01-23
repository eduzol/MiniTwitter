package com.github.eduzol.minitwitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @ExceptionHandler(value = { Exception.class , RuntimeException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "{ message : \"An Error has occured\"}";
        logger.debug("An Error has occured" , ex);
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
