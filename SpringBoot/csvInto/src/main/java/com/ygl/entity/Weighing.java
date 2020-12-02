package com.ygl.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 13:38
 */
@Data
public class Weighing {
    private int id;
    private String code;
    private float vehicleWeight;
    private int axleNumber;
    private float vehicleSpeed;
    private int chedaoId;
    private int modelNumber;
    private Date createTime;
}
