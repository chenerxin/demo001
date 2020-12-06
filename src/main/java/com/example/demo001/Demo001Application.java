package com.example.demo001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo001Application {

    public static void main(String[] args) {
        //再模拟222一下
        SpringApplication.run(Demo001Application.class, args);//模1111拟冲突11111

        // 这里还有222冲突呢 制造冲突

        // 自己文件222多了应该没问题吧
    }

}
