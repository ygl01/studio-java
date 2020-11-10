package com.ygl.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author ygl
 * @description
 * @date 2020/11/9 18:04
 */
@Service
public class SchedulingService {
    //在特定的时间执行这个方法
    /*
    cron :表达式
    //秒 分 时 日 月 周几
     */
    @Scheduled(cron = "0 14 0-23 * * 1/7")
    public void hello(){
        System.out.println("我执行喽、、、");
    }
}
