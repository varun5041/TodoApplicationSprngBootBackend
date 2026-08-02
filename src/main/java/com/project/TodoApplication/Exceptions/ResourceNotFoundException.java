package com.project.TodoApplication.Exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{
    String message;
    HttpStatus status;

    public ResourceNotFoundException(HttpStatus status, String message) {
        super(message); //call RuntimeException Constructor //give the message to Runtime also
        //RunTime Passes it to Exception
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
