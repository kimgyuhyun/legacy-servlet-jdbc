package com.legacy.practice.step3.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("user not found. id=" + id);
    }
}
