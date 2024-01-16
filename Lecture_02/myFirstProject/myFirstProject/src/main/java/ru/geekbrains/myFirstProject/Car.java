package ru.geekbrains.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Car {
    //Вариант 3 с Autowired
    @Autowired
    Engine engine;
    public void start(){
        engine.go();
    }

// Вариант 1 - создаем конструктор с Engine
//    public Car(Engine engine) {
//        this.engine = engine;
//        engine.go();
//    }

    // Вариант 2 с setter
//    public Car() {
//    }
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//        engine.go();
//    }
}
