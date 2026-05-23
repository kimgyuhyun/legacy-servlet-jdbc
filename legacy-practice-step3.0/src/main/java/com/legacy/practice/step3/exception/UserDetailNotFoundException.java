package com.legacy.practice.step3.exception;

public class UserDetailNotFoundException extends RuntimeException{
    public UserDetailNotFoundException(Long id) {
        super("user detail not found. userId=" + id);
    }
}
