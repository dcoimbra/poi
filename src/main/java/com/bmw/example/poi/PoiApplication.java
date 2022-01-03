package com.bmw.example.poi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
public class PoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoiApplication.class, args);
    }
}
