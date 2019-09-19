package cn.chenzw.auth.easy.api.service;

import cn.chenzw.auth.easy.core.core.EasyUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EasyUserAuthenticationService {


    @Autowired
    EasyUserAuthentication easyUserAuthentication;

    public void login() {
        easyUserAuthentication.login();
    }

}
