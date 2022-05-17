package com.elaamiri.ebankbackend.exceptions;

public class AccountNotFoundException extends RuntimeException{
    private String message;
    public AccountNotFoundException(String message){
        if (message == null) this.message = "Exception: Account Not Found !";
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
