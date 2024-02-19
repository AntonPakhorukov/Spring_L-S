package com.example.SpringAOP.Introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class FlyerIntroduction {
    @DeclareParents(value = "com.example.SpringAOP.Introduction.Car", defaultImpl = FlyingImpl.class)
    public static Flyer flyer;
}

