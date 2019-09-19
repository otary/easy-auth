package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EasyUserAuthenticationEndpoint {


    @PostMapping("${easy.auth.login-uri}")
    public void login(UserAuthenticationDefinition userAuthenticationDefinition) {

        // 校验是否已登录失败超过次数


        // 登录失败超次数，则使用相应的策略

        // 验证码策略
        // 检测用户名、密码和验证码


        // 检测用户名和密码

        // 通过，则登入
        // 清空登录失败次数


        // 不通过
        // 登录失败次数+1

    }
}
