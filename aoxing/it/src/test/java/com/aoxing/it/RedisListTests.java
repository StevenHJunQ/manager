package com.aoxing.it;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author hejq9
 * @date 2019-10-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RedisListTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 右压栈: 后添加的元素排在后面
     * 先进先出
     */
    @Test
    public void setValueRight() {
        redisTemplate.boundListOps("name").rightPush("张三");
        redisTemplate.boundListOps("name").rightPush("李四");
        redisTemplate.boundListOps("name").rightPush("王五");
        redisTemplate.boundListOps("name").rightPush("赵六");
    }


    /**
     * 右压栈的输入
     */
    @Test
    public void getValue() {
        // [张三, 李四, 王五,赵六]
        List<String> name = redisTemplate.boundListOps("name").range(0, 10);
        System.out.println(name);

    }

    /**
     * 左压栈: 先进后出
     */
    @Test
    public void setValueLeft() {
        redisTemplate.boundListOps("nameLeft").leftPush("关羽");
        redisTemplate.boundListOps("nameLeft").leftPush("张飞");
        redisTemplate.boundListOps("nameLeft").leftPush("诸葛亮");
        redisTemplate.boundListOps("nameLeft").leftPush("刘备");
    }

    /**
     * 左压栈的输入
     */
    @Test
    public void getValueLeft() {
        // [刘备, 诸葛亮, 张飞, 关羽]
        List<String> name = redisTemplate.boundListOps("nameLeft").range(0, 10);
        System.out.println(name);
    }

    /**
     * 按索引位置查询元素
     */
    @Test
    public void searchByIndex() {
        // 关羽
        String index = redisTemplate.boundListOps("nameLeft").index(3);
        System.out.println(index);
        //集合元素个数
        Long size = redisTemplate.boundListOps("nameLeft").size();
        System.out.println(size);
    }

    /**
     * 移除
     */
    @Test
    public void removeByIndex() {
        // 移除元素的个数   移除元素的name
        redisTemplate.boundListOps("nameLeft").remove(1, "诸葛亮");
//        redisTemplate.delete("nameLeft");//移除所有值
    }
}
