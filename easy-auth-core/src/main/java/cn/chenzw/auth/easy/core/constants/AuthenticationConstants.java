package cn.chenzw.auth.easy.core.constants;

import cn.chenzw.auth.easy.core.definition.EasyUserDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录认证常量
 *
 * @author chenzw
 */
@Configuration
@PropertySource(value = {"classpath:easy-auth.properties", "classpath*:application.properties"}, ignoreResourceNotFound = true)
public class AuthenticationConstants {

    private static final String DEFAULT_USER_NAME_IDENTIFIER = "userName";
    private static final String DEFAULT_PASSWORD_IDENTIFIER = "pwd";
    private static final String DEFAULT_CAPTCHA_IDENTIFIER = "captcha";
    private static final String DEFAULT_REMEMBER_ME_IDENTIFIER = "rememberMe";
    private static final String DEFAULT_MAX_LOGIN_FALIED_TIMES = "3";
    private static final String DEFAULT_LOGIN_FAIL_STRATEGY = "captcha";
    private static final String DEFAULT_LOGIN_URI = "/login";
    private static final String DEFAULT_LOGIN_SUCCESS_REDIRECT_URI = "/index";
    private static final String DEFAULT_CAPTCHA_URI = "/getCaptchaImage";

    /**
     * 默认用户名和密码
     */
    private static final String DEFAULT_USER = "admin@@admin123";
    /**
     * 登录验证码会话Key
     */
    public static final String LOGIN_CAPTCHA_SESSION = "LOGIN_CAPTCHA_SESSION";


    /**
     * 参数标识符
     */
    public static String USER_NAME_IDENTIFIER;
    public static String PASSWORD_IDENTIFIER;
    public static String CAPTCHA_IDENTIFIER;
    public static String REMEMBER_ME_IDENTIFIER;

    /**
     * 最大登录失败次数
     */
    public static Integer MAX_LOGIN_FALIED_TIMES;

    /**
     * 登录失败策略: captcha - 验证码、lock - 锁定N分钟
     */
    public static String LOGIN_FAIL_STRATEGY;

    /**
     * 登录的URI
     */
    public static String LOGIN_URI;

    /**
     * 登录成功后跳转的URI
     */
    public static String LOGIN_SUCCESS_REDIRECT_URI;

    /**
     * 验证码URI
     */
    public static String CAPTCHA_URI;

    /**
     * 自定义内存帐号信息
     */
    public static List<EasyUserDefinition> MEMOERY_USERS = new ArrayList<>();


    @Value("${easy.auth.username-identifier:" + DEFAULT_USER_NAME_IDENTIFIER + "}")
    public void setUserNameIdentifier(String userNameIdentifier) {
        USER_NAME_IDENTIFIER = userNameIdentifier;
    }

    @Value("${easy.auth.password-identifier:" + DEFAULT_PASSWORD_IDENTIFIER + "}")
    public void setPasswordIdentifier(String passwordIdentifier) {
        PASSWORD_IDENTIFIER = passwordIdentifier;
    }

    @Value("${easy.auth.captcha-identifier:" + DEFAULT_CAPTCHA_IDENTIFIER + "}")
    public void setCaptchaIdentifier(String captchaIdentifier) {
        CAPTCHA_IDENTIFIER = captchaIdentifier;
    }

    @Value("${easy.auth.remember-me-identifier:" + DEFAULT_REMEMBER_ME_IDENTIFIER + "}")
    public void setRememberMeIdentifier(String rememberMeIdentifier) {
        REMEMBER_ME_IDENTIFIER = rememberMeIdentifier;
    }

    @Value("${easy.auth.login-uri:" + DEFAULT_LOGIN_URI + "}")
    public void setLoginUri(String loginUri) {
        LOGIN_URI = loginUri;
    }

    @Value("${easy.auth.max-login-failed-times:" + DEFAULT_MAX_LOGIN_FALIED_TIMES + "}")
    public void setMaxLoginFaliedTimes(Integer maxLoginFaliedTimes) {
        MAX_LOGIN_FALIED_TIMES = maxLoginFaliedTimes;
    }

    @Value("${easy.auth.login-fail-strategy:" + DEFAULT_LOGIN_FAIL_STRATEGY + "}")
    public void setLoginFailStrategy(String loginFailStrategy) {
        LOGIN_FAIL_STRATEGY = loginFailStrategy;
    }

    @Value("${easy.auth.login-success-redirect-uri:" + DEFAULT_LOGIN_SUCCESS_REDIRECT_URI + "}")
    public void setLoginSuccessRedirectUri(String loginSuccessRedirectUri) {
        LOGIN_SUCCESS_REDIRECT_URI = loginSuccessRedirectUri;
    }

    @Value("${easy.auth.captcha-uri:" + DEFAULT_CAPTCHA_URI + "}")
    public void setCaptchaUri(String captchaUri) {
        CAPTCHA_URI = captchaUri;
    }

    @Value("${easy.auth.users[0]:" + DEFAULT_USER + "}")
    public void setMemoeryUsers(List<String> users) {
        for (String user : users) {
            String[] userDetails = StringUtils.split(user, "@@");
            MEMOERY_USERS.add(new EasyUserDefinition(userDetails[0], userDetails[1]));
        }
    }


}
