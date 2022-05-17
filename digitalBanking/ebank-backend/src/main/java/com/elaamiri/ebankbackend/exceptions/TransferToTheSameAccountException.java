package com.elaamiri.ebankbackend.exceptions;

public class TransferToTheSameAccountException extends Exception {
    public TransferToTheSameAccountException(String message) {
        super(message);
    }
}
