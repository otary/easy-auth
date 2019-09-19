package cn.chenzw.auth.easy.api.service;

import cn.chenzw.auth.easy.api.support.condition.MissingUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Conditional(MissingUserRegistrationService.class)
@Service(UserRegistrationConstants.DEFAULT_USER_REGISTRATION_SERVICE)
public class EasyUserRegistrationService {

    @Autowired
    EasyUserRegistration easyUserRegistration;

    public void checkUserName() {
        if (!easyUserRegistration.checkUserNameFormat()) {
            throw new UserRegistioinException("用户名非法");
        }

        if (easyUserRegistration.checkUserNameExists()) {
            throw new UserRegistioinException("该用户名已被占用");
        }
    }

    public void checkPhoneNo() {
        if (!easyUserRegistration.checkPhoneNoFormat()) {
            throw new UserRegistioinException("该手机号非法");
        }

        if (easyUserRegistration.checkPhoneNoExists()) {
            throw new UserRegistioinException("该手机号已被占用");
        }
    }

    public void checkPwd() {
        if (!easyUserRegistration.checkPwdFormat()) {
            throw new UserRegistioinException("密码非法");
        }

        if (!easyUserRegistration.confirmPwd()) {
            throw new UserRegistioinException("密码不匹配");
        }
    }


    public void sendSms() {
        easyUserRegistration.sendSMS();
    }


    public void checkSmsCode() {
        if (!easyUserRegistration.checkSmsCode()) {
            throw new UserRegistioinException("短信验证码不正确");
        }
    }

    public void register() {
        checkUserName();
        checkPhoneNo();
        checkPwd();
        checkSmsCode();

        easyUserRegistration.saveUserInfo();
    }

}
