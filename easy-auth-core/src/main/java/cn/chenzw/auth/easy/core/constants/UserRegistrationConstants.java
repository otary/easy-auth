package cn.chenzw.auth.easy.core.constants;

/**
 * 用户注册相关常量
 *
 * @author chenzw
 */
public abstract class UserRegistrationConstants {

    public static final String USER_NAME_IDENTIFIER = "userName";
    public static final String PHONE_NO_IDENTIFIER = "phoneNo";
    public static final String PASSWORD_IDENTIFIER = "pwd";
    public static final String EMAIL_IDENTIFIER = "email";
    public static final String SMS_CODE_IDENTIFIER = "smsCode";

    /**
     * 短信发送间隔
     */
    public static final int SMS_DELIVERY_INTERVAL = 60000;


    /**
     * 上次短信验证码发送时间SESSION标识符
     */
    public static final String SMS_CODE_LIAST_SEND_TIME_SESSION_IDENTIFIER = "SMS_CODE_LAST_SEND_TIME";

    /**
     * 随机生成的短信验证码
     */
    public static final String SMS_CODE_SESSION_IDENTIFIER = "SMS_CODE_SESSION";


    public static final String BASE_PACKAGE = "cn.chenzw.user.registration";

    public static final String DEFALUT_USER_REGISTRATION_URL_MAPPGING = "/user-registration";

    public static final String DEFAULT_USER_REGINSTATION_CONFIG = "easyUserRegistrationConfig";

    public static final String DEFAULT_USER_REGISTRATION_CONTROLLER = "userRegistrationController";

    public static final String DEFAULT_USER_REGISTRATION_SERVICE = "userRegistrationService";

}
