package com.ygl.mapper;

import com.ygl.entity.WaterFilm;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 10:40
 */
@Mapper
public interface WaterFilmMapper {
    int WaterFilmIntoSQL(WaterFilm waterFilm);
}
