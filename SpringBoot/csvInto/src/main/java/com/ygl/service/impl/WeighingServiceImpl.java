package com.ygl.service.impl;

import com.ygl.entity.WaterFilm;
import com.ygl.entity.Weighing;
import com.ygl.mapper.WeighingMapper;
import com.ygl.service.WeighingService;
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
 * @date 2020/12/1 13:46
 */
@Service
public class WeighingServiceImpl implements WeighingService {

    @Autowired
    private WeighingMapper weighingMapper;

    @Value("${File.path}")
    private String path;

    @Override
    public int WeighingIntoSQL(String name) throws IOException, InterruptedException, ParseException {

        while (true){
            String finalPath = path + name;
            File file = new File(finalPath);
            CsvReader csvReader = new CsvReader();
            CsvContainer csvFile = csvReader.read(file, Charset.forName("UTF-8"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (csvFile != null){
                for (CsvRow row : csvFile.getRows()){
                    Weighing weighing = new Weighing();
                    String id = row.getField(0);//int
                    String code = row.getField(1);//String
                    String vehicleWeight = row.getField(2);//float
                    String axleNumber = row.getField(3);//int
                    String vehicleSpeed = row.getField(4); //float
                    String chedaoId = row.getField(5); //int
                    String modelNumber = row.getField(6); //int
                    weighing.setCode(code);
                    float vehicleWeight1 = Float.parseFloat(vehicleWeight);
                    weighing.setVehicleWeight(vehicleWeight1);
                    int axleNumber1 = Integer.parseInt(axleNumber);
                    weighing.setAxleNumber(axleNumber1);
                    float vehicleSpeed1 = Float.parseFloat(vehicleSpeed);
                    weighing.setVehicleSpeed(vehicleSpeed1);
                    int chedaoId1 = Integer.parseInt(chedaoId);
                    weighing.setChedaoId(chedaoId1);
                    int modelNumber1 = Integer.parseInt(modelNumber);
                    weighing.setModelNumber(modelNumber1);
                    Date createTime = new Date();
                    String s = df.format(createTime);
                    Date date = df.parse(s);
                    weighing.setCreateTime(date);
                    //没插入一条线程休眠一秒
                    Thread.sleep(2000);
                    weighingMapper.WeighingInto(weighing);
                }
            }
        }
    }
}
