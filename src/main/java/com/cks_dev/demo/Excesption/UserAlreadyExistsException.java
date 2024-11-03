package com.cks_dev.demo.Excesption;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
