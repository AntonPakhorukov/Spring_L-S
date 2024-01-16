package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService helloService;
    @Autowired
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }
    @GetMapping("/test")
    public String hello() {
        return this.helloService.getGreeting();
    }
    @GetMapping("/")
    public String hello2(){
        return "Hello from port 391";
    }
}
