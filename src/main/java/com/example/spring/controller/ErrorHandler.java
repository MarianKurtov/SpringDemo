package com.example.spring.controller;

import com.example.spring.exception.HelloException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HelloException.class})
    public ModelAndView handleHelloException(HelloException ex) {
        ModelAndView modelAndView = new ModelAndView("errorHandler");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
