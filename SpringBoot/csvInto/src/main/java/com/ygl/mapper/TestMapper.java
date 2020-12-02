package com.ygl.mapper;


import com.ygl.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ygl
 * @description
 * @date 2020/11/30 14:04
 */
@Mapper
public interface TestMapper {
    int testIntoSql(List<Test> Tests);
    void testIntoOne(Test test);
}
