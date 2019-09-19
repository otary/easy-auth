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

    /**
     * 最大登录失败次数
     */
    public static Integer MAX_LOGIN_FALIED_TIMES = 3;

    /**
     * 登录失败策略: captcha - 验证码、lock - 锁定N分钟
     */
    public static String LOGIN_FAIL_STRATEGY = "captcha";

    /**
     * 登录验证码
     */
    public static final String LOGIN_CAPTCHA_SESSION = "LOGIN_CAPTCHA_SESSION";

    public static String LOGIN_URI = "/login";

    /**
     * 登录成功后跳转的URI
     */
    public static String LOGIN_SUCCESS_REDIRECT_URI = "/index";


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

    @Value("${easy.auth.max-login-failed-times}")
    public void setMaxLoginFaliedTimes(Integer maxLoginFaliedTimes) {
        MAX_LOGIN_FALIED_TIMES = maxLoginFaliedTimes;
    }

    @Value("${easy.auth.login-fail-strategy}")
    public void setLoginFailStrategy(String loginFailStrategy) {
        LOGIN_FAIL_STRATEGY = loginFailStrategy;
    }

    @Value("${easy.auth.login-success-redirect-uri}")
    public void setLoginSuccessRedirectUri(String loginSuccessRedirectUri) {
        LOGIN_SUCCESS_REDIRECT_URI = loginSuccessRedirectUri;
    }

}
