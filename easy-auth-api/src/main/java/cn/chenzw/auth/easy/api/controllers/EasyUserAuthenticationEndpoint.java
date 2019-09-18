package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EasyUserAuthenticationEndpoint {


    @PostMapping("${easy.auth.login-uri}")
    public void login(UserAuthenticationDefinition userAuthenticationDefinition) {

    }
}
