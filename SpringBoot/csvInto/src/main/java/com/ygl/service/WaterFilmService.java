package com.ygl.service;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 10:08
 */
public interface WaterFilmService {
    int WaterFilmIntoSQL(String name) throws IOException, ParseException, InterruptedException;
}
