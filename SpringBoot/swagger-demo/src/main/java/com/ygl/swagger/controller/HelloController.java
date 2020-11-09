package com.ygl.swagger.controller;

import com.ygl.swagger.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ygl
 * @description
 * @date 2020/11/9 10:35
 */
@RestController
public class HelloController {

    @GetMapping("/swagger/hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("/user")
    public User user(){
        return new User();
    }
}
