package com.ygl.service;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 13:44
 */
public interface WeighingService {
    int WeighingIntoSQL(String name) throws IOException, InterruptedException, ParseException;
}
