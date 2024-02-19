package com.example.SpringAOP.Introduction;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.setName("Land Rover Sport");
        car.setAge(2022);
        System.out.println(car);
        car.drive();
    }
}
