package com.example.SpringAOP.Service;
/**
 * AFTER THROWING
 */

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ErrorHandlingAspect {
    /**
     * Позволяет нам ловить исключения, которые были выброшены во время выполнения кода
     * работает как блок catch
     */
    @AfterThrowing(pointcut = "execution(* com.example.SpringAOP.*.*(..))", throwing = "error")
    public void handleAllErrors(Exception error){
        System.out.println("An error occurred: " + error.getMessage());
        // можно отправлять уведомление или логировать ошибку
    }
}
