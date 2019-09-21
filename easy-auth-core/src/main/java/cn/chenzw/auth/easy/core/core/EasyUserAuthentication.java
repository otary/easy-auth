package cn.chenzw.auth.easy.core.core;

/**
 * 用户认证
 *
 * @author chenzw
 */
public interface EasyUserAuthentication {

    /**
     * 检测登录失败次数
     *
     * @return
     */
    boolean checkLoginFailedTimes();

    /**
     * 登录处理
     */
    void login();


}
