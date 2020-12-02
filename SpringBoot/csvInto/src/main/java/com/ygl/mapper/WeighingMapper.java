package com.ygl.mapper;

import com.ygl.entity.Weighing;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 13:56
 */
@Mapper
public interface WeighingMapper {
    int WeighingInto(Weighing weighing);
}
