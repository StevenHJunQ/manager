package com.aoxing.it.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hejq9
 * @date 2019-10-25
 */
@Data
public class UserLogoutRequest {

    @NotBlank
    private String username;
}
