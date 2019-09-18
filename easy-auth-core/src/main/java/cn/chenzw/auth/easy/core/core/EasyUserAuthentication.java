package cn.chenzw.auth.easy.core.core;

/**
 * 用户认证
 *
 * @author chenzw
 */
public interface EasyUserAuthentication {

    /**
     * 登录校验
     *
     * @return
     */
    boolean checkUsernameAndPassword();


    /**
     * 校验验证码
     *
     * @return
     */
    boolean checkCaptcha();

    boolean check();

}
