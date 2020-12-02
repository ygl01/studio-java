package com.ygl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygl.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
//        redisTemplate  操作不同的数据类型，api和我们的指令是一样的
        // opsForValue()  操作字符串  类似String
        //opsForList()  操作list   类似list
        //opsForSet()
        //opsForHash()
        //opsForZSet()
        //opsForGeo()
        //除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD
        //获取redis的连接对象
        //获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();
        //操作字符串
        redisTemplate.opsForValue().set("myKey","我是闫高岭");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }
    @Test
    public void test() throws JsonProcessingException {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        //这里是为了真实环境中传对象
        User user = new User("闫高岭", 18);
//        String jsonUser = new ObjectMapper().writeValueAsString(user);
//        redisTemplate.opsForValue().set("user",jsonUser);
        redisTemplate.opsForValue().set("user",user);
        Object o = redisTemplate.opsForValue().get("user");
        System.out.println(o);
    }

}
