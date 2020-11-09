package com.kuang.service;

import com.kuang.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author ygl
 * @description
 * @date 2020/11/5 9:34
 */
public interface UserService {
    List<User> userList();

    User userById(int id);

    int deleterUserById(int id);

    int updateUser(@RequestBody User user);

    int addUser(User user);
}
