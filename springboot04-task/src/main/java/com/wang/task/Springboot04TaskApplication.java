package com.wang.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling //开启定时任务注解
@SpringBootApplication
@EnableAsync //开启异步注解
public class Springboot04TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot04TaskApplication.class, args);
    }

}

