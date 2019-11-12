package com.aoxing.it.dto.response;

import lombok.Data;

/**
 * @author hejq9
 * @date 2019-10-22
 */
@Data
public class UserLoginResponse extends BaseResponse {

    private String nickName;

    private String token;
}
