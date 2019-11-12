package com.aoxing.it;

import com.alibaba.fastjson.JSON;
import com.aoxing.it.bean.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hejq9
 * @date 2019-10-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RedisKVTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void setValue() {
        //存值
        redisTemplate.boundValueOps("name").set("张三");
    }

    @Test
    public void getValue() {
        String name = redisTemplate.boundValueOps("name").get();
        //如果没有 name 则会返回 null
        System.out.println(name);
    }

    @Test
    public void deleteValue() {
        //移除值
        redisTemplate.delete("name");
    }

    @Test
    public void setObj(){
        User user = new User();
        user.setNickName("steven");
        user.setName("m13560928");
        String json = JSON.toJSONString(user);
        redisTemplate.boundValueOps("user_steven").set(json);
    }

    @Test
    public void getObj(){
        String json = redisTemplate.boundValueOps("user_steven").get();
        User user = JSON.parseObject(json, User.class);
        System.out.println(user);
    }
}
