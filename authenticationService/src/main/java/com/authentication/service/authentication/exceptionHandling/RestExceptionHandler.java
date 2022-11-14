package com.authentication.service.authentication.exceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> exceptionMovieHandler(UserException err){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(err.getDefaultException().value());
        errorResponse.setMessage(err.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse,err.getDefaultException());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception err){
        System.out.println(err);
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("THE REQUEST CANNOT BE PLACED DUE TO MALFUNCTION SYNTAX.");
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
