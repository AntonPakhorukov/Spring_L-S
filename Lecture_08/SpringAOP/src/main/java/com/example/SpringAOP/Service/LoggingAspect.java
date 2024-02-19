package com.example.SpringAOP.Service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // Аннотация говорить spring, что этот класс является аспектом
@Component
public class LoggingAspect {
    /**
     * Метод логирования вызова методов
     * @Before - аспект должен быть вызван перед вызовом метода
     * execution - указываем, какие именно методы и классы нужно перехватывать
     * .* - говорить о том, что все классы находящиеся в этом пакете будут учтены
     * .*(..) - все методы, находящиеся в этих классах будут учтены
     * @param joinPoint - содержит информацию о точке внедрения
     */
    @Before("execution(* com.example.SpringAOP.TaskService.*(..))")
    public void logBeforeMethodCall(JoinPoint joinPoint){
        System.out.println("Метод "+ joinPoint.getSignature().getName() + " быд вызван");
    }
}
