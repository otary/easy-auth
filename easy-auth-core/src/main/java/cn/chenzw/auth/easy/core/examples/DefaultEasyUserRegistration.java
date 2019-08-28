package cn.chenzw.auth.easy.core.examples;

import cn.chenzw.auth.easy.core.core.AbstractEasyUserRegistration;
import cn.chenzw.auth.easy.core.definition.UserRegistrationInfoDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认注册类
 *
 * @author chenzw
 */
public class DefaultEasyUserRegistration extends AbstractEasyUserRegistration {


    private static final Logger logger = LoggerFactory.getLogger(DefaultEasyUserRegistration.class);

    @Override
    public boolean checkUserNameExists(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        return false;
    }

    @Override
    public boolean checkUserNameFormat(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        return true;
    }


    @Override
    public boolean checkPhoneNoExists(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        return false;
    }

    @Override
    public boolean checkPwdFormat(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        return true;
    }

    @Override
    public boolean checkPhoneNoFormat(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        return true;
    }


    @Override
    public boolean postValidate(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        return false;
    }

    @Override
    public void sendSMS(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        logger.warn("No SMS sended, please override method AbstractEasyUserRegistration.sendSMS()");
    }

    @Override
    public void saveUserInfo(UserRegistrationInfoDefinition userRegistrationInfoDefinition) {
        logger.warn("please overrid method AbstractEasyUserRegistration.saveUserInfo()");
    }

}
