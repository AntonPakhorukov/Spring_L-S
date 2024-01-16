package ru.geekbrains.myFirstProject;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public Engine() {
        System.out.println("Engine is started");
    }
    public void go(){
        System.out.println("Car is drive");
    }
}
