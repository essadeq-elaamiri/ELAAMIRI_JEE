package com.elaamiri.ebankbackend.exceptions;

public class OperationFailedException extends RuntimeException{
    private String message;
    public OperationFailedException(String message){
        if (message == null) this.message = "Exception: Operation Failed !";
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
