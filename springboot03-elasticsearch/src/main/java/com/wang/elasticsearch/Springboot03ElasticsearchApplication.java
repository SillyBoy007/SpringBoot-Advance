package com.wang.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot默认支持两种技术与ES交互
 * 1.Jest（默认不生效,需要导入Jest的工具包）
 * 2.SpringData ElasticSearch  (无法实现)
 *      1)、Client 节点信息 clusterNodes:clusterName
 *      2)、ElasticsearchTemplate操作es 类似Redis，RabbitMQ
 *      3）、编写一个ElasticsearchRepository的子接口来操作es
 * 两种用法
 * 1) 编写一个ElasticsearchRepository
 */
@SpringBootApplication
public class Springboot03ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticsearchApplication.class, args);
    }

}

