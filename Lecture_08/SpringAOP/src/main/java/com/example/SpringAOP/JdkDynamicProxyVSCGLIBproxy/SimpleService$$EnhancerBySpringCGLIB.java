package com.example.SpringAOP.JdkDynamicProxyVSCGLIBproxy;

public class SimpleService$$EnhancerBySpringCGLIB extends SimpleService{
    @Override
    public void doSomething() {
        // логика аспекта
        super.doSomething(); // вызов оригинального метода
    }
}
