package com.example.lifedemo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// 帮助扫描dao，在编译后生成实现类
@MapperScan({"com.example.lifedemo01.Dao","com.example.lifedemo01.System.dao","com.example.lifedemo01.AccountBook.Trading.dao"})
public class Lifedemo01Application {
    public static void main(String[] args) {
        SpringApplication.run(Lifedemo01Application.class, args);
    }

}
