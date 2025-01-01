package com.devinfusion.eventia.users.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super(message);
    }
}
