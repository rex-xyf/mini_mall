package com.sim.controller;

import com.sim.exception.BusinessException;
import com.sim.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public Result doSystemException(SystemException e){
        return new Result(e.getCode(),null,e.getMessage());
    }

    @ExceptionHandler
    public Result doBusinessException(BusinessException e){
        System.out.println("Error");
        return new Result(e.getCode(),null,e.getMessage());
    }

    @ExceptionHandler
    public Result doException(Exception e){
        System.out.println("Error");
        return new Result(Code.UNKNOWN_ERR,null,"ERROR");
    }
}
