package com.kuang.controller;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ygl
 * @description
 * @date 2020/11/4 21:00
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("userList")
    public List<User> userList(){
        return userService.userList();
    }

    @GetMapping("/userById")
    public User userById(int id){
        return userService.userById(id);
    }

    @DeleteMapping("deleterUserById")
    public int deleterUserById(int id){
       return userService.deleterUserById(id);
    }

    @PutMapping("updateUser")
    public int updateUser(@RequestBody User user){
        System.out.println("user值："+user);
        return userService.updateUser(user);
    }

    @PostMapping("/addUser")
    public int addUser(@RequestBody User user){
        System.out.println("user值："+user);
        return userService.addUser(user);
    }

}
