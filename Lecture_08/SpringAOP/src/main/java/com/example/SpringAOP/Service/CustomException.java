package com.example.SpringAOP.Service;

/**
 * Сделано для примера кастомных исключений для примера ExceptionTransformingAspect
 */
public class CustomException extends Exception{
    public CustomException(String customMessage) {
    }

    public class SpecificException extends Exception{

    }
}
