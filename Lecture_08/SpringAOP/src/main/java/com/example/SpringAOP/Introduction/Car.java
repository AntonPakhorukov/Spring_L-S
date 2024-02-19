package com.example.SpringAOP.Introduction;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Setter
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    public void drive(){
        System.out.println("Car is driving");
    }

    @Override
    public String toString() {
        return "Car:\n" +
                "id = " + id +
                "\nname = '" + name + '\'' +
                "\nage = " + age;
    }
}
