package com.example.demo001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Demo001Application {

    private String master;
    public static void main(String[] args) {
        //再模拟一下
        SpringApplication.run(Demo001Application.class, args);

    }

}
