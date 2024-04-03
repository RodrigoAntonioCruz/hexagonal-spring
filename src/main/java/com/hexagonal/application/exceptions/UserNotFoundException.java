package com.hexagonal.application.exceptions;


import com.hexagonal.application.utils.Constants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND);
    }
}
