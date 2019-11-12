package com.aoxing.it;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author hejq9
 * @date 2019-10-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RedisSetTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void setSetValue(){
        redisTemplate.boundSetOps("nameset").add("曹操");
        redisTemplate.boundSetOps("nameset").add("刘备");
        redisTemplate.boundSetOps("nameset").add("关羽");
    }

    @Test
    public void getSetValue(){
        //如果没有 nameset 返回  []
        Set<String> members = redisTemplate.boundSetOps("nameset").members();
        // 没有循序,和存入的循序无关
        // [关羽, 刘备, 曹操]
        System.out.println(members);
    }

    @Test
    public void removeSetValue(){
        //移除一个
        redisTemplate.boundSetOps("nameset").remove("刘备");

        //全部移除
        redisTemplate.delete("nameset");

    }
}
