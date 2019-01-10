package com.wang.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAMQPConfig {
    @Bean
    public MessageConverter messageConverter(){ //自动配置里，配置RabbitTemplate的时候会判断是否有自定义的MessageConvert，如果有则采用自定义的
        return new Jackson2JsonMessageConverter();
    }

}
