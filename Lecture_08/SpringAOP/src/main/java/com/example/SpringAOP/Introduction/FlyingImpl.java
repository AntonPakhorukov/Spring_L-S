package com.example.SpringAOP.Introduction;

public class FlyingImpl implements Flyer {
    @Override
    public void fly() {
        System.out.println("Car is now flying!");
    }
}
