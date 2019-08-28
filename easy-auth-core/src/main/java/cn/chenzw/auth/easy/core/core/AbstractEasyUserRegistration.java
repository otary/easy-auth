package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.UserRegistrationConstants;
import cn.chenzw.auth.easy.core.definition.UserRegistrationInfoDefinition;
import cn.chenzw.auth.easy.core.exception.UserRegistioinException;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;


/**
 * @author chenzw
 */
public abstract class AbstractEasyUserRegistration implements EasyUserRegistration {

    private ThreadLocal<UserRegistrationInfoDefinition> userRegistrationInfoDefinitionTL = new ThreadLocal<>();

    protected UserRegistrationInfoDefinition getUserRegistrationInfoDefinition() {
        if (userRegistrationInfoDefinitionTL.get() == null) {
            userRegistrationInfoDefinitionTL.set(new UserRegistrationInfoDefinition());
        }
        return userRegistrationInfoDefinitionTL.get();
    }


    public abstract boolean checkUserNameFormat(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract boolean checkUserNameExists(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract boolean checkPhoneNoFormat(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract boolean checkPhoneNoExists(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract boolean checkPwdFormat(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract boolean postValidate(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract void sendSMS(UserRegistrationInfoDefinition userRegistrationInfoDefinition);

    public abstract void saveUserInfo(UserRegistrationInfoDefinition userRegistrationInfoDefinition);


    @Override
    public boolean checkUserNameFormat() {
        return checkUserNameFormat(getUserRegistrationInfoDefinition());
    }


    @Override
    public boolean checkPhoneNoFormat() {
        return checkPhoneNoFormat(getUserRegistrationInfoDefinition());
    }

    @Override
    public boolean checkPwdFormat() {
        return checkPwdFormat(getUserRegistrationInfoDefinition());
    }

    @Override
    public boolean confirmPwd() {
        UserRegistrationInfoDefinition userRegistrationInfoDefinition = getUserRegistrationInfoDefinition();
        String[] pwds = userRegistrationInfoDefinition.getPwds();

        if (pwds.length < 2) {
            return false;
        }

        if (!pwds[0].equals(pwds[1])) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserNameExists() {
        return checkUserNameExists(getUserRegistrationInfoDefinition());
    }

    @Override
    public boolean checkPhoneNoExists() {
        return checkPhoneNoExists(getUserRegistrationInfoDefinition());
    }

    @Override
    public boolean postValidate() {
        return postValidate(getUserRegistrationInfoDefinition());
    }

    @Override
    public void sendSMS() {
        UserRegistrationInfoDefinition userRegistrationInfoDefinition = getUserRegistrationInfoDefinition();
        HttpSession httpSession = userRegistrationInfoDefinition.getRequest().getSession(true);
        Object oLastSendTime = httpSession
                .getAttribute(UserRegistrationConstants.SMS_CODE_LIAST_SEND_TIME_SESSION_IDENTIFIER);
        if (oLastSendTime != null) {
            Date lastSendTime = (Date) oLastSendTime;
            if (Calendar.getInstance().getTime().getTime() - lastSendTime.getTime()
                    < UserRegistrationConstants.SMS_DELIVERY_INTERVAL) {
                throw new UserRegistioinException("验证码发送频繁");
            }
        }

        httpSession.setAttribute(UserRegistrationConstants.SMS_CODE_LIAST_SEND_TIME_SESSION_IDENTIFIER,
                Calendar.getInstance().getTime());

        // 生成4位数随机验证码
        httpSession.setAttribute(UserRegistrationConstants.SMS_CODE_SESSION_IDENTIFIER,
                RandomStringUtils.randomNumeric(4));
        sendSMS(userRegistrationInfoDefinition);
    }

    @Override
    public void saveUserInfo() {
        saveUserInfo(getUserRegistrationInfoDefinition());
    }

    @Override
    public boolean checkSmsCode() {
        UserRegistrationInfoDefinition userRegistrationInfoDefinition = getUserRegistrationInfoDefinition();

        if (userRegistrationInfoDefinition.getSmsCode().equals(userRegistrationInfoDefinition.getRandonSmsCode())) {
            return true;
        }
        return false;
    }
}
