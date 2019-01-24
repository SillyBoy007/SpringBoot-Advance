package com.wang.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 1.引入SpringSecurity
 * 2.编写SpringSecurity的配置类,该类需要继承WebSecurityConfigurerAdapter
 * 3.开启基于WebSecurity的注解(@EnableWebSecurity)
 * 4.
 *
 */

@SpringBootApplication
public class Springboot05SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05SecurityApplication.class, args);
    }

}

