package com.authentication.service.authentication.exceptionHandling;


import org.springframework.http.HttpStatus;

public class UserException extends Exception{

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    private HttpStatus defaultException = HttpStatus.NOT_FOUND;// not found

    public UserException(){}

    public UserException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public HttpStatus getDefaultException(){return defaultException;}

    public UserException setDefaultException(HttpStatus httpStatus){
        this.defaultException = httpStatus;
        return this;
    }




}
