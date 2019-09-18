package cn.chenzw.auth.easy.core.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 登录认证常量
 *
 * @author chenzw
 */
@Configuration
@PropertySource(value = {"classpath:"}, ignoreResourceNotFound = true)
public class AuthenticationConstants {

    private AuthenticationConstants() {
    }


    public static String USER_NAME_IDENTIFIER = "userName";
    public static String PASSWORD_IDENTIFIER = "pwd";
    public static String CAPTCHA_IDENTIFIER = "captcha";

    public static String LOGIN_URI = "/login";

    @Value("${easy.auth.username-identifier}")
    public void setUserNameIdentifier(String userNameIdentifier) {
        USER_NAME_IDENTIFIER = userNameIdentifier;
    }

    @Value("${easy.auth.password-identifier}")
    public void setPasswordIdentifier(String passwordIdentifier) {
        PASSWORD_IDENTIFIER = passwordIdentifier;
    }

    @Value("${easy.auth.captcha-identifier}")
    public void setCaptchaIdentifier(String captchaIdentifier) {
        CAPTCHA_IDENTIFIER = captchaIdentifier;
    }

    @Value("${easy.auth.login-uri}")
    public void setLoginUri(String loginUri) {
        LOGIN_URI = loginUri;
    }
}
