package com.example.SpringAOP.Service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Метод проверки за какое время выполнился определенный метод
 * AROUND ADVICE
 */
@Aspect
@Component
public class PerformanceAspect {
    /**
     * Метод проверки времени на выполнение метода
     * joinPoint - место в программе, где аспект применяется,
     * то есть это любое событие которое можем использовать для обработки
     * @param joinPoint - означает, что мы уже берем выполненный метод
     * @return
     * @throws Throwable
     */
    @Around("execution (* com.example.SpringAOP.TaskController.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{ // место в программе, где аспект применяется
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // Вызов целевого метода
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Метод " + joinPoint.getSignature().getName() + " выполнился за " + elapsedTime + " миллисекунд");
        return result; // Возвращаем результат выполнения целевого метода
    }
}
