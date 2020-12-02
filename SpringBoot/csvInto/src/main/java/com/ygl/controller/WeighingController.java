package com.ygl.controller;

import com.ygl.service.WeighingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 13:43
 */
@RestController
@RequestMapping("/Weighing")
public class WeighingController {
    @Autowired
    private WeighingService weighingService;

    @GetMapping("/intoSql")
    private int WeighingIntoSQL(String name) throws ParseException, InterruptedException, IOException {
        return weighingService.WeighingIntoSQL(name);
    }
}
