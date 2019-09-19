package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.auth.easy.api.service.EasyUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EasyUserAuthenticationEndpoint {


    @Autowired
    EasyUserAuthenticationService userAuthenticationService;

    /**
     * 登录入口
     */
    @GetMapping("${easy.auth.login-uri}")
    public void login() {
        userAuthenticationService.login();
    }

    /**
     * 获取验证码图片
     */
    @GetMapping("${easy.auth.captcha-uri}")
    public void getCaptchaImage() {

    }
}
