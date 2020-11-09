package com.ygl.swagger.pojo;

import io.swagger.annotations.ApiModel;

/**
 * @author ygl
 * @description
 * @date 2020/11/9 15:10
 */
@ApiModel("实体类model")
public class User {
    public String name;
    public String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
