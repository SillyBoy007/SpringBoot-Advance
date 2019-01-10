package com.wang.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 自动配置原理
 * 1、RabbitAutoConfiguration
 * 2、自动配置了连接工厂ConnectionFactory
 * 3、RabbitProperties封装了RabbitMQ的配置
 * 4、RabbitTemplate:给RabbitMQ发送和接收消息的
 * 5、AmqpAdmin系统管理组件:创建交换器等
 * 6.@EnableRabbit+@RabbitListener监听消息队列的内容
 */
@EnableRabbit//开启基于注解的RabbitMQ模式
@SpringBootApplication
public class Springboot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpApplication.class, args);
    }

}

