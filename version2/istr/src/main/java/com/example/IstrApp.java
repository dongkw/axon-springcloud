package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan({"com.example.*"})
public class IstrApp {
    public static void main(String[] args) {
        SpringApplication.run(IstrApp.class);
    }
}
