package com.wang.amqp;

import com.wang.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;//自动配置类已经为我们创建好了
    @Autowired
    AmqpAdmin amqpAdmin; //自动配置类已经为我们创建好了，万能的自动配置类，SpringBoot是真滴方便

    /**
     * 点对点传播(direct)
     */
    @Test
    public void contextLoads() {
        //Message需要自己构造一个,定义消息体内容和消息头
       // rabbitTemplate.send(exchange,routingKey,message);
        //object默认当成消息体,只需要传入要发送的对象,自动序列化给mq
        Map<String,Object> map = new HashMap<>();
        map.put("msg","第二次发送消息");
        map.put("data",Arrays.asList("<","0.0",">"));
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","wang.news",new Book("js",4));
    }
    @Test
    public void receive(){
        Object receive = rabbitTemplate.receiveAndConvert("wang.news"); //接收消息。
        System.out.println(receive.getClass());
        System.out.println(receive);
    }

    /**
     * 广播模式
     *
     */
    @Test
    public void sendMsgs(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("java",2));
    }

    /**
     * 发布/订阅(模糊匹配)方式
     */
    @Test
    public void topicSendMsgs(){
        rabbitTemplate.convertAndSend("exchange.topic","*.news",new Book("python",3));
    }
    /**
     * 创建Exchange
     *
     */
    @Test
    public void createExchange() {
        DirectExchange directExchange = new DirectExchange("amqpadmin.exchange",true,false);
        amqpAdmin.declareExchange(directExchange); //创建一个DirectExchange
        System.out.println("创建完成");
    }
    /**
     * 创建队列
     */
    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        System.out.println("队列创建完成");
    }

       /**
     * 创建绑定规则(banding)
     */

    @Test
    public void createBanding(){
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqpadmin.queue",null));
        System.out.println("绑定完成");
    }


    @Test
    public void testAdmin() {
        //创建交换器
        amqpAdmin.declareExchange(new DirectExchange("admin.direct"));
        //创建消息队列
        amqpAdmin.declareQueue(new Queue("admin.queue"));
        //添加绑定
        amqpAdmin.declareBinding(new Binding("admin.queue", Binding.DestinationType.QUEUE,
                "admin.direct", "admin.queue", null));

    }
}

