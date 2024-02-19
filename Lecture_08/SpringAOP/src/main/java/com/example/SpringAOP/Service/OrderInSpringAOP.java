package com.example.SpringAOP.Service;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/**
 * Порядок выполнения аспектов
 */
public class OrderInSpringAOP {
    @Aspect
    @Order(1)
    public class SecurityAspect{
        //код аспекта безопасности
    }

    @Aspect
    @Order(2)
    public class LoggingAspect{
        //Код аспекта логирования
    }

}
