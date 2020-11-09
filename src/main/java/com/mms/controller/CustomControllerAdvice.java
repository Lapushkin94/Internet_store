package com.mms.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(value = NullPointerException.class)
    public String nullPointerException() {
        return "exceptions/nullPointer";
    }

    @ExceptionHandler(value = NoResultException.class)
    public String noResultException() {
        return "exceptions/noResult";
    }

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler() {
        return "exceptions/unhandledExceptions";
    }

}
