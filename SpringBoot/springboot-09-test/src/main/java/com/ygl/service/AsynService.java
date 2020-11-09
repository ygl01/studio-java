package com.ygl.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ygl
 * @description
 * @date 2020/11/9 16:00
 */
@Service
public class AsynService{
    //异步任务 注解
    @Async
    public void hello() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("数据正在处理、、、");
    }
}
