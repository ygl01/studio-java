package com.ygl.service;

import com.ygl.pojo.User;

/**
 * @author ygl
 * @description
 * @date 2020/11/6 16:14
 */
public interface UserSevice {
    public User queryByName(String name);
}
