package com.ygl;

import com.ygl.pojo.User;
import com.ygl.service.impl.UserServiceImpl;
import com.ygl.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {
    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        User user = userService.queryByName("王五");
        System.out.println("哈哈:" + user);
    }

    @Test
    void test2() {

        // 获取盐值
        String salt = PasswordUtils.getSalt();
        System.out.println("盐值：" + salt);
        // 获取加密后的密码

        String password = PasswordUtils.getMd5("123456", "ygl", salt);
        System.out.println("password：" + password);
        System.out.println("密码：" + password);
    }

}
