package com.kuang.service.impl;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ygl
 * @description
 * @date 2020/11/5 9:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }

    @Override
    public User userById(int id) {
        return userMapper.userById(id);
    }

    @Override
    public int deleterUserById(int id) {
        return userMapper.deleterUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
