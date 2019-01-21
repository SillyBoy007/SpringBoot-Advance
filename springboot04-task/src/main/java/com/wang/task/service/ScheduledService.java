package com.wang.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/*@Service
public class ScheduledService {
    *//**
     * second,minute,hour,day of month,month,day of week
     * 0 * * * * MON-FRI
     *//*
    //@Scheduled(cron = "0 * * * * MON-SAT")   //每分钟的整秒的时候执行该方法
    //@Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")  //每分钟0,1,3,4秒的的时候执行该方法
    //@Scheduled(cron = "0-4 * * * * MON-SAT")  //每分钟0,1,3,4秒的的时候执行该方法
    @Scheduled(cron = "0/4 * * * * MON-SAT")  //每四秒执行一次 （/设定步长）
    public void say(){
        System.out.println("Hello...");
    }
}*/
