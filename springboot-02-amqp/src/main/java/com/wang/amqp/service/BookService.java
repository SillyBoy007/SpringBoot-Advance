package com.wang.amqp.service;

import com.wang.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = {"wang.news"}) //监听队列wang.news，只要wang.news收到消息，立刻执行该方法,并清空队列
    public void receive(Book book){
        System.out.println("收到消息"+book);
    }
    @RabbitListener(queues = {"wang"})
    public void receive02(Message message){  //org.springframework.amqp.core.Message;
        System.out.println("消息内容"+message.getBody());
        System.out.println("消息头"+message.getMessageProperties());
    }
}
