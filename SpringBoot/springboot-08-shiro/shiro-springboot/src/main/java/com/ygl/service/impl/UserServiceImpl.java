package com.ygl.service.impl;

import com.ygl.mapper.UserMapper;
import com.ygl.pojo.User;
import com.ygl.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ygl
 * @description
 * @date 2020/11/6 16:15
 */
@Service
public class UserServiceImpl implements UserSevice {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }
}
