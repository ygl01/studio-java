package com.ygl.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 10:03
 */
@Data
public class WaterFilm {
    private int id;
    private String code;
    private Date createTime;
    private float waterDepth;
    private int riskGrade;
}
