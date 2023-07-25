package com.example.demo.nsc.exceptionHandler;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message)
    {
        super(message);
    }

}
