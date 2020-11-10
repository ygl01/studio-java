package com.ygl.controller;

import com.ygl.service.AsynService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ygl
 * @description
 * @date 2020/11/9 16:03
 */
@RestController
public class AsyncController {
    @Autowired
    private AsynService asynService;
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        asynService.hello();
        return "ok";

    }
}
