package com.darkotrajkovski.wpaud1.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Invalid user");
    }
}
