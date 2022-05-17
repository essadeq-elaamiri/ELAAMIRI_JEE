package com.elaamiri.ebankbackend.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    private String message;
    public CustomerNotFoundException(String message){
        if (message == null) this.message = "Exception: Customer Not Found !";
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
