package com.ygl.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author ygl
 * @description 密码加密的处理工具类
 * @date 2020/11/6 16:47
 */
public class PasswordUtils {
    /**
     * 迭代次数
     */
    private static final int ITERATIONS = 6;
    /**
     * 盐值长度
     */
    private static final int SALT_NUMBER = 6;

    /**
     * 构造方法
     */
    public PasswordUtils() {
        throw new AssertionError();
    }

    /**
     * 证书凭证
     *
     * @param username 登录名
     * @param salt      盐值
     * @return
     */
    public static String getCredentialsSalt(String username, String salt) {
        return username + salt;
    }

    /**
     * 获得密码盐值
     *
     * @return
     */
    public static String getSalt() {
        return RandomUtils.getString(SALT_NUMBER);
    }

    /**
     * 字符串加密函数MD5实现
     *
     * @param password  密码
     * @param username 用户名
     * @param salt      盐值
     * @return
     */
    public static String getMd5Password(String password, String username, String salt) {
        return new Md5Hash(password, getCredentialsSalt(username, salt), ITERATIONS).toString();
    }


    public static String getMd5(String password, String username, String salt) {
        return new Md5Hash(password, getCredentialsSalt(username, salt), ITERATIONS).toString();
    }
}
