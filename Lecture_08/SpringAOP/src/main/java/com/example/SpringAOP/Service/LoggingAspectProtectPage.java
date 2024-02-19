package com.example.SpringAOP.Service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * BEFORE ADVICE
 */
@Aspect
@Component
public class LoggingAspectProtectPage {
    @Before("execution (* com.example.SpringAOP.TaskService.sortById(..))")
    public void logBeforeAccess(){
        System.out.println("Попытка доступа к защищенной странице!");
    }
}
