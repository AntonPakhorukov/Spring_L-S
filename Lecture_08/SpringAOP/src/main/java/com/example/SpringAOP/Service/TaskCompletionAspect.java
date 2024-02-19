package com.example.SpringAOP.Service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AFTER ADVICE
 */
@Aspect
@Component
public class TaskCompletionAspect {
    @After("execution (* com.example.SpringAOP.TaskService.getTaskById(..))")
    public void logAfterTaskCompletion(){
        System.out.println("Метод успешно выполнен!");
    }
}
