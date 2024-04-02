package com.hexagonal.application.exception;


import com.hexagonal.application.Constants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND);
    }
}
