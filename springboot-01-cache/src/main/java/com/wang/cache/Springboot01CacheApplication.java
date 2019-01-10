package com.wang.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境:
 * 1.创建department和employee表
 * 2.创建javabean封装数据
 * 3.整合mybatis操作数据库
 *      1).配置数据源
 *      2).使用注解版的mybatis
 *          1).使用@MapperScan指定需要扫描的mapper接口所在的包
 *
 *二、缓存
 *     1、开启基于注解的缓存
 *     2、标注缓存注解即可
 *
 */
@MapperScan("com.wang.cache.mapper")
@SpringBootApplication
@EnableCaching //开启基于注解的缓存
public class Springboot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01CacheApplication.class, args);
    }

}

