package com.aoxing.it.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hejq9
 * @date 2019-10-22
 */
@Data
public class UserLoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
