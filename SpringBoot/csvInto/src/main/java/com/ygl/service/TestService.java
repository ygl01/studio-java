package com.ygl.service;

import java.io.IOException;

/**
 * @author ygl
 * @description
 * @date 2020/11/30 13:57
 */
public interface TestService {
    int testIntoSql(String fileName) throws IOException, InterruptedException;
}
