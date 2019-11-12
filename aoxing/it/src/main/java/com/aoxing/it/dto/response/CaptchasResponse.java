package com.aoxing.it.dto.response;

import lombok.Data;

/**
 * 验证码
 * @author hejq9
 * @date 2019-10-24
 */
@Data
public class CaptchasResponse {

    private Integer status;

    private String code;
}
