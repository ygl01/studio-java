package com.ygl.mapper;

import com.ygl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ygl
 * @description
 * @date 2020/11/6 16:09
 */
@Repository
@Mapper
public interface UserMapper {
    public User queryByName(String name);
}
