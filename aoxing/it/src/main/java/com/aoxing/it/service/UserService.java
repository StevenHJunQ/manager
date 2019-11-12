package com.aoxing.it.service;

import com.aoxing.it.dto.request.UserLoginRequest;
import com.aoxing.it.dto.request.UserLogoutRequest;
import com.aoxing.it.dto.response.BaseResponse;
import com.aoxing.it.dto.response.UserLoginResponse;

/**
 * @author hejq9
 * @date 2019-10-22
 */
public interface UserService {

    UserLoginResponse login(UserLoginRequest request);

    BaseResponse logout(UserLogoutRequest request);
}
