package com.project.TodoApplication.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.DriverManager;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionsHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullPointerExceptionHanlder(NullPointerException exception){
        logger.info("NULL pointer Exception from Global Exception Handler");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //handling resource not Found Exception
    @ExceptionHandler(ResourceNotFoundException.class)                         //catching
    public ResponseEntity<ExceptionResponse> ResourceNotFoundHandler(ResourceNotFoundException ex){
        logger.error("ERROR: {}",ex.getMessage());
        //creating response to return
        ExceptionResponse response = new ExceptionResponse();

        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccess(false);

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
