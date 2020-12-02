package com.ygl.service.impl;



import com.ygl.entity.Test;
import com.ygl.mapper.TestMapper;
import com.ygl.service.TestService;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ygl
 * @description
 * @date 2020/11/30 13:58
 */
@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private TestMapper testMapper;

    @Value("${File.path}")
    private String path;

    @Override
    public int testIntoSql(String fileName) throws IOException, InterruptedException {

        while (true) {
            System.out.println("我在外面读取文件、、、");
            int b = 0;
            //先进行读取文件
            String finalPath = path + fileName;
            File file = new File(finalPath);
            CsvReader csvReader = new CsvReader();
            CsvContainer csvFile = csvReader.read(file, Charset.forName("UTF-8"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int result = 0;
            if (csvFile != null) {
                ArrayList<Test> tests = new ArrayList<>();
                for (CsvRow row : csvFile.getRows()) {
                    Date date = new Date();
                    String time = format.format(date);
                    //跳出标题行
                    if (b == 0) {
                        b++;
                        continue;
                    }
                    b++;
                    Test test = new Test();
                    String id = row.getField(0);
                    String name = row.getField(1);
                    String age = row.getField(2);
                    System.out.println("第三个值为：" + age);
                    String address = row.getField(3);
                    System.out.println("id的1值：" + id);
                    int id1 = Integer.parseInt(id);
                    System.out.println("id的2值：" + id1);
                    int age1 = Integer.parseInt(age);
                    test.setId(id1);
                    test.setName(name);
                    test.setAge(age1);
                    test.setAddress(address);
                    test.setUpdateTime(time);
                    Thread.sleep(1000);
//                    testMapper.testIntoOne(test);
                    System.out.println("我在执行循环");
                    tests.add(test);
                }


                //批量导入数据
                int batchCount = 5000;//每批次导入的最大数据
                int bat = batchCount;//计算插入的数值
                int batchLastIndex = batchCount;//每批最后一条数据下标等于批量大小
                SqlSession sqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//固定写法
                TestMapper mapper = sqlSession.getMapper(TestMapper.class);
                //批量插入   index 为 下标
                for (int index = 0; index < tests.size(); ) {
                    //如果csv读取的数量大小 小于 批量大小
                    if (tests.size() < batchLastIndex) {
                        batchLastIndex = tests.size();
                        bat = batchLastIndex - result;
                        mapper.testIntoSql(tests.subList(index, batchLastIndex));
                        result = result + bat;
                        //清楚缓存
                        sqlSession.clearCache();
                        break;
                    } else {
                        mapper.testIntoSql(tests.subList(index, batchLastIndex));
                        result = result + bat;
                        //清除缓存
                        sqlSession.clearCache();
                        index = batchLastIndex;
                        batchLastIndex = index + (batchCount - 1);
                    }
                }
                //将数据提交到数据库，否则的话只是执行，但是并没有提交数据到数据库
                sqlSession.commit();
            }
        }
    }
}
