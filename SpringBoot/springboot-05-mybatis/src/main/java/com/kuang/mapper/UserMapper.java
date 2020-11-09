package com.kuang.mapper;

import com.kuang.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author ygl
 * @description
 * @date 2020/11/4 19:50
 */

//Mapper 也可以使用类似MapperScan
@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserMapper {

    List<User> userList();

    User userById(int id);

    int deleterUserById(int id);

    int updateUser(@RequestBody User user);

    int addUser(User user);

}
