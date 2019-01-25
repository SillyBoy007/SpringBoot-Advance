package com.wang.ticket;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.服务提供者注册到注册中心
 *      1.引入dubbo和zkclient的相关依赖
 *      2.配置dubbo的扫描包和注册中心地址
 *      3.开启dubbo的注解
 *      4.使用@Service发布服务
 *
 */
@EnableDubbo  //开启dubbo注解
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}

