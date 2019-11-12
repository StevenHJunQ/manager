package com.aoxing.it;

import com.aoxing.it.bean.Form;
import com.aoxing.it.bean.User;
import com.aoxing.it.mapper.FormMapper;
import com.aoxing.it.mapper.UserMapper;
import com.aoxing.it.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FormMapper formMapper;

    @Test
    void addFormTest() {
        for (int i = 0; i < 100; i++) {
            Form form = new Form();
            form.setDelivery(1);
            form.setOptions("guangdong/guangzhou/tianhe");
            form.setType("步步高/小天才");
            form.setDate1("2019-10-01");
            form.setDate2("2019-10-26T06:55:06.000Z");
            form.setDescription("文本框");
            form.setRegion("bbk");
            form.setResource("小天才");
            form.setName("表单名称");
            int result = formMapper.insert(form);
            System.out.println(result);
        }
    }

    @Test
    void selectFormTest() {
        Page<Form> page = new Page<>(1, 10);
        Form form = new Form();
        QueryWrapper<Form> wrapper = new QueryWrapper<>(form);
        IPage<Form> iPage = formMapper.selectPage(page, wrapper);
        System.out.println("current:" + iPage.getCurrent());
        System.out.println("size:" + iPage.getSize());
        System.out.println("total:" + iPage.getTotal());
        System.out.println(iPage.getRecords());
    }

    @Test
    void contextLoads() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到，开始执行");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000);
    }

    @Test
    void userInsertTest() {
        User user = new User();
        user.setName("root");
        user.setPassword(MD5Util.md5("root"));
        user.setNickName("管理员");
        System.out.println(user);
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    void userLoginTest() {
        User user = new User();
        user.setName("m13560928");
        user.setPassword(MD5Util.md5("admin123"));
        Wrapper<User> wrapper = new QueryWrapper<>(user);
        user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
}
