package com.example.SpringAOP.Service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AFTER THROWING ASPECT
 */

@Aspect
@Component
public class PaymentAspect {
    @AfterThrowing(pointcut = "execution (* com.example.SpringAOP.TaskService.getTaskById(..))", throwing = "exception")
    public void logPaymentError(Exception exception){
        System.out.println("Произошла ошибка: " + exception.getMessage());
    }
}
