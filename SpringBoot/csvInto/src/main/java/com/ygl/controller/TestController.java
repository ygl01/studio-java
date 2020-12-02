package com.ygl.controller;


import com.ygl.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ygl
 * @description 测试csv文件导入数据库
 * @date 2020/11/30 13:53
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/testIntoSql")
    private int testIntoSql(String fileName) throws IOException, InterruptedException {
        System.out.println("姓名："+fileName);
        return testService.testIntoSql(fileName);
    }
}
