package com.aoxing.it.service.impl;

import com.aoxing.it.bean.User;
import com.aoxing.it.dto.request.UserLoginRequest;
import com.aoxing.it.dto.request.UserLogoutRequest;
import com.aoxing.it.dto.response.BaseResponse;
import com.aoxing.it.dto.response.UserLoginResponse;
import com.aoxing.it.mapper.UserMapper;
import com.aoxing.it.service.UserService;
import com.aoxing.it.util.CommonStatic;
import com.aoxing.it.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

/**
 * @author hejq9
 * @date 2019-10-25
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        User user = new User();
        user.setName(username);
        user.setPassword(MD5Util.md5(password));
        Wrapper<User> wrapper = new QueryWrapper<>(user);
        user = userMapper.selectOne(wrapper);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            redisTemplate.boundValueOps(CommonStatic.TOKEN_PREFIX + username).set(token, Duration.ofSeconds(CommonStatic.TOKEN_DURATION));
            UserLoginResponse response = new UserLoginResponse();
            response.setCode(200);
            response.setDesc("login success");
            response.setToken(token);
            response.setNickName(user.getNickName());
            return response;
        } else {
            UserLoginResponse response = new UserLoginResponse();
            response.setCode(500);
            response.setDesc("username or password not compare!");
            return response;
        }
    }

    @Override
    public BaseResponse logout(UserLogoutRequest request) {
        String username = request.getUsername();
        redisTemplate.delete(CommonStatic.TOKEN_PREFIX + username);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setDesc("logout success");
        return response;
    }
}
