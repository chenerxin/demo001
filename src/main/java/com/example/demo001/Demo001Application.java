package com.example.demo001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo001Application {

    public static void main(String[] args) {
        //再模拟一下
        SpringApplication.run(Demo001Application.class, args);//模拟冲突11111神奇

        // 这里还有冲突呢 制造冲突

        // 自己文件多了应该没问题吧
    }

}
