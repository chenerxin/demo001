package com.example.demo001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo001Application {
    private String dev1;
    public static void main(String[] args) {
        //再模拟一下
        SpringApplication.run(Demo001Application.class, args);//模拟冲突11111神奇

        // 1111222222222这里还有冲突呢 33333333

        // 自己文件多了dev应该没问题吧
    }

}
