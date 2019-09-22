package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.auth.easy.api.service.EasyUserAuthenticationService;
import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.support.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(EasyAuthenticationConstants.DEFAULT_API_MAPPING)
public class EasyUserAuthenticationEndpoint {

    @Autowired
    EasyUserAuthenticationService userAuthenticationService;

    /**
     * 登录入口
     */
    @PostMapping("${easy.auth.login-uri}")
    public HttpResult login() {
        userAuthenticationService.login();
        return HttpResult.ok();
    }

    /**
     * 获取验证码图片
     */
    @GetMapping("${easy.auth.captcha-uri}")
    public void getCaptchaImage() throws IOException {
        userAuthenticationService.getCaptchaImage();
    }

    /**
     * 检查是否开启验证码验证
     */
    @GetMapping("/checkCaptchaOn")
    public HttpResult checkCaptchaOn() {
        return userAuthenticationService.checkCaptchaOn();
    }
}
