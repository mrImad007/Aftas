package com.example.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class SbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SbApplication.class, args);
    }

}
