package com.ygl.controller;

import com.ygl.service.WaterFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 10:06
 */
@RestController
@RequestMapping("/waterFilm")
public class WaterFilmController {

    @Autowired
    private WaterFilmService waterFilmService;

    @GetMapping("/intoSql")
    private int WaterFilmIntoSQL(String name) throws ParseException, InterruptedException, IOException {
        return waterFilmService.WaterFilmIntoSQL(name);
    }
}
