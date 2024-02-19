package com.example.SpringAOP.Service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionTransformingAspect {
    @AfterThrowing(pointcut = "execcution (* com.example.SpringAOP.*.*.(..))", throwing = "ex")
    public void transformExceptions(Exception ex) throws CustomException {
        if (ex instanceof CustomException.SpecificException) {
            throw new CustomException("Custom message");
        }
    }
}
