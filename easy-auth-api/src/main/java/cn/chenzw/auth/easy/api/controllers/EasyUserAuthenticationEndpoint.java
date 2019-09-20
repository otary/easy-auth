package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.auth.easy.api.service.EasyUserAuthenticationService;
import cn.chenzw.auth.easy.core.support.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class EasyUserAuthenticationEndpoint {


    @Autowired
    EasyUserAuthenticationService userAuthenticationService;

    /**
     * 登录入口
     */
    @PostMapping("${easy.auth.login-uri}")
    public HttpResult login() {
        userAuthenticationService.login();
        return new HttpResult(HttpStatus.OK.value(), "");
    }

    /**
     * 获取验证码图片
     */
    @GetMapping("${easy.auth.captcha-uri}")
    public void getCaptchaImage() throws IOException {
        userAuthenticationService.getCaptchaImage();
    }
}
