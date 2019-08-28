package cn.chenzw.auth.easy.api.controllers;

import cn.chenzw.user.registration.constants.UserRegistrationConstants;
import cn.chenzw.user.registration.spring.service.UserRegistrationService;
import cn.chenzw.user.registration.spring.support.condition.MissingUserRegistrationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Conditional(MissingUserRegistrationController.class)
@RestController(UserRegistrationConstants.DEFAULT_USER_REGISTRATION_CONTROLLER)
@RequestMapping(UserRegistrationConstants.DEFALUT_USER_REGISTRATION_URL_MAPPGING)
public class EasyUserRegistrationEndpoint {

    @Autowired
    UserRegistrationService userRegistrationService;

    @GetMapping("/username/check")
    public void checkUserName() {
        userRegistrationService.checkUserName();
    }

    @GetMapping("/phoneno/check")
    public void checkPhoneNo() {
        userRegistrationService.checkPhoneNo();
    }


    @GetMapping("/pwd/check")
    public void checkPwd() {
        userRegistrationService.checkPwd();
    }

    @PostMapping("/sms-send")
    public void smsSend() {
        userRegistrationService.sendSms();
    }

    @GetMapping("/sms-code/check")
    public void checkSmsCode() {
        userRegistrationService.checkSmsCode();
    }

    @PostMapping("/register")
    public void register() {
        userRegistrationService.register();
    }

}
