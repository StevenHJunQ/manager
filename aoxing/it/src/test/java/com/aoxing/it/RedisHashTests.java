package com.aoxing.it;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @author hejq9
 * @date 2019-10-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RedisHashTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 存值
     */
    @Test
    public void setValue() {
        redisTemplate.boundHashOps("hash").put("a", "唐僧");
        redisTemplate.boundHashOps("hash").put("b", "八戒");
        redisTemplate.boundHashOps("hash").put("c", "沙和尚");
        redisTemplate.boundHashOps("hash").put("d", "孙悟空");
    }

    /**
     * 获取所有的key
     */
    @Test
    public void getAllKey() {
        //获取所有的key : [a, b, c, d]
        Set<Object> key = redisTemplate.boundHashOps("hash").keys();
        System.out.println(key);
    }

    /**
     * 获取所有的值
     */
    @Test
    public void getAllValue() {
        //获取所有的值
        List<Object> values = redisTemplate.boundHashOps("hash").values();
        System.out.println(values); //[唐僧, 八戒, 沙和尚, 孙悟空]

        //根据key取值
        String str = (String) redisTemplate.boundHashOps("hash").get("c");
        System.out.println(str); // 沙和尚
    }

    /**
     * 移除某个小key的值
     */
    @Test
    public void removeKey() {
        redisTemplate.boundHashOps("hash").delete("c");
    }
}
