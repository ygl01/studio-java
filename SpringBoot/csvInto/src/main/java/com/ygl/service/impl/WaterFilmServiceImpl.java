package com.ygl.service.impl;

import com.ygl.entity.WaterFilm;
import com.ygl.mapper.WaterFilmMapper;
import com.ygl.service.WaterFilmService;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ygl
 * @description
 * @date 2020/12/1 10:09
 */
@Service
public class WaterFilmServiceImpl implements WaterFilmService {

    @Autowired
    private WaterFilmMapper waterFilmMapper;

    @Value("${File.path}")
    private String path;

    @Override
    public int WaterFilmIntoSQL(String name) throws IOException, ParseException, InterruptedException {
        while (true){
            String finalPath = path + name;
            File file = new File(finalPath);
            CsvReader csvReader = new CsvReader();
            CsvContainer csvFile = csvReader.read(file, Charset.forName("UTF-8"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (csvFile != null){
                for (CsvRow row : csvFile.getRows()){
                    WaterFilm waterFilm = new WaterFilm();
                    String id = row.getField(0);//int
                    String code = row.getField(1);//String
//                    String createTime = row.getField(2);//date
                    String waterDepth = row.getField(3);//float
                    String riskGrade = row.getField(4); //int
                    int id1 = Integer.parseInt(id);
                    int riskGrade1 = Integer.parseInt(riskGrade);
                    float waterDepth1 = Float.parseFloat(waterDepth);
                    Date createTime = new Date();
                    String s = df.format(createTime);
                    Date date = df.parse(s);

                    System.out.println("时间："+date);
                    waterFilm.setId(id1);
                    waterFilm.setCode(code);
                    waterFilm.setCreateTime(date);
                    waterFilm.setRiskGrade(riskGrade1);
                    waterFilm.setWaterDepth(waterDepth1);
                    System.out.println("获取时间："+waterFilm.getCreateTime());
                    //没插入一条线程休眠一秒
                    Thread.sleep(2000);
                    waterFilmMapper.WaterFilmIntoSQL(waterFilm);
                }
            }
        }
    }
}
