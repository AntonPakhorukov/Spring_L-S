package com.example.SpringAOP.Service;

import com.example.SpringAOP.Task;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ChangeReturningValueAspect {
//    @AfterReturning(pointcut = "execution (* com.example.SpringAOP.TaskController.getName(..))", returning = "result")
//    public void changeName(JoinPoint joinPoint, String result){
//        result = "Измененное имя";
//    }
    @AfterReturning(pointcut = "execution (* com.example.SpringAOP.TaskController.updateTaskByStatus(..))", returning = "result")
    public void changeTask(JoinPoint joinPoint, Task result){
        result.setStatus(Task.Status.Done);
        result.setDescription("Подмена описания");
    }
}
