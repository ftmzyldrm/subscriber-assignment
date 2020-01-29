package com.assignment.subscriber.subscriberassignment.exception.handler;

import com.assignment.subscriber.subscriberassignment.exception.SubscriberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = SubscriberNotFoundException.class)
    public String handleBaseException(SubscriberNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){return e.getMessage();}
}