package cn.chenzw.auth.easy.core.core;

/**
 * 用户注册核心接口
 *
 * @author chenzw
 */
public interface EasyUserRegistration {

    /**
     * 验证用户名格式
     *
     * @return
     */
    boolean checkUserNameFormat();

    /**
     * 验证用户名是否已存在
     *
     * @return
     */
    boolean checkUserNameExists();


    /**
     * 验证手机号码格式
     *
     * @return
     */
    boolean checkPhoneNoFormat();

    /**
     * 验证手机号是否已存在
     *
     * @return
     */
    boolean checkPhoneNoExists();

    /**
     * 验证密码格式
     *
     * @return
     */
    boolean checkPwdFormat();

    /**
     * 确认密码
     *
     * @return
     */
    boolean confirmPwd();


    /**
     * 后置验证器
     *
     * @return
     */
    boolean postValidate();

    /**
     * 发送短信验证码
     *
     * @return
     */
    void sendSMS();


    /**
     * 校验短信验证码
     */
    boolean checkSmsCode();


    /**
     * 保存用户信息
     *
     * @return
     */
    void saveUserInfo();

}
