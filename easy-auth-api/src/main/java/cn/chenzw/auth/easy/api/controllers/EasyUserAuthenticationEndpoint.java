package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.auth.easy.api.service.EasyUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EasyUserAuthenticationEndpoint {


    @Autowired
    EasyUserAuthenticationService userAuthenticationService;

    @GetMapping("${easy.auth.login-uri}")
    public void login() {
        userAuthenticationService.login();
    }
}
