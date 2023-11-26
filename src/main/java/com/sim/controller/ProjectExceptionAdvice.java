package com.sim.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public Result doException(Exception e){
        System.out.println("Error");
        return new Result(404,null,"ERROR");
    }
}
