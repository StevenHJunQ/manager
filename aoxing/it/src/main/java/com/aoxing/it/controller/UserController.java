package com.aoxing.it.controller;

import com.aoxing.it.dto.request.UserLoginRequest;
import com.aoxing.it.dto.request.UserLogoutRequest;
import com.aoxing.it.dto.response.BaseResponse;
import com.aoxing.it.dto.response.CaptchasResponse;
import com.aoxing.it.dto.response.UserLoginResponse;
import com.aoxing.it.service.UserService;
import com.aoxing.it.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author hejq9
 * @date 2019-10-22
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/logout")
    public BaseResponse logout(@Valid @RequestBody UserLogoutRequest request){
        return userService.logout(request);
    }

}
