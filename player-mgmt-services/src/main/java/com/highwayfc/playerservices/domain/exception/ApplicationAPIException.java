package com.highwayfc.playerservices.domain.exception;

import org.springframework.http.HttpStatus;

public class ApplicationAPIException extends RuntimeException {
    private HttpStatus status;

    public ApplicationAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }


    public HttpStatus getStatus() {return status;}
    @Override
    public String getMessage() {return super.getMessage();}


}
