package com.aoxing.it.controller;

import com.aoxing.it.dto.response.CaptchasResponse;
import com.aoxing.it.util.ValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hejq9
 * @date 2019-10-24
 */
@CrossOrigin
@RestController
public class CommandController {

    /**
     * 验证码
     * @return
     */
    @PostMapping("/captchas")
    public CaptchasResponse captchas() {
        CaptchasResponse response = new CaptchasResponse();
        response.setStatus(1);
        ValidateCodeUtil.Validate v = ValidateCodeUtil.getRandomCode();
        response.setCode("data:image/png;base64," + v.getBase64Str());
        return response;
    }

    @GetMapping("/check")
    public void check() {

    }

}
