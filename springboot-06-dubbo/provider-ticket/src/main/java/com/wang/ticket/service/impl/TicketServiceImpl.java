package com.wang.ticket.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wang.ticket.service.TicketService;
import org.springframework.stereotype.Component;

@Component
@Service //注意这边是com.alibaba.dubbo.config.annotation.Service
//主要作用是将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《毒液:致命守护者》";
    }
}
