package com.mms.exceptions;

public class NoSuchObjectException extends RuntimeException {

    public NoSuchObjectException() {
        super("Have no objects for your request");
    }

    public NoSuchObjectException(String message) {
        super(message);
    }
}
