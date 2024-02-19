package com.example.SpringAOP.Service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AFTER RETURNING ASPECT
 */
@Aspect
@Component
public class MessageAspect {
    @AfterReturning(pointcut = "execution (* com.example.SpringAOP.TaskService.setDescription(..))", returning = "description")
    public void logTaskDescription(String description){
        System.out.println("Описание: " + description);
    }
}
