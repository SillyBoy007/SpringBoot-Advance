package com.wang.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wang.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service //org.springframework.stereotype.Service
public class UserService {
    @Reference //引入注册中心的服务,按照全类名匹配,在注册中心找到对应的服务
    TicketService ticketService;
    public void  hello(){
        String ticket = ticketService.getTicket();
        System.out.println(ticket);
    }
}
