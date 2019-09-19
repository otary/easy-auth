package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.AuthenticationConstants;
import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.support.LoginTimesCacheHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象用户认证
 *
 * @author chenzw
 */
public abstract class AbstractEasyUserAuthentication implements EasyUserAuthentication {


    private ThreadLocal<UserAuthenticationDefinition> userAuthenticationDefinitionTL = new ThreadLocal<>();

    protected UserAuthenticationDefinition getUserAuthenticationDefinition() {
        if (userAuthenticationDefinitionTL.get() == null) {
            userAuthenticationDefinitionTL.set(new UserAuthenticationDefinition());
        }
        return userAuthenticationDefinitionTL.get();
    }

    public abstract boolean checkUsernameAndPassword(UserAuthenticationDefinition userAuthenticationDefinition);

    public abstract boolean checkCaptcha(UserAuthenticationDefinition userAuthenticationDefinition);


    @Override
    public boolean checkUsernameAndPassword() {
        return checkUsernameAndPassword(getUserAuthenticationDefinition());
    }

    @Override
    public boolean checkCaptcha() {
        return checkCaptcha(getUserAuthenticationDefinition());
    }

    @Override
    public boolean checkLoginFailedTimes() {
        UserAuthenticationDefinition userAuthenticationDefinition = userAuthenticationDefinitionTL.get();
        int loginTimes = LoginTimesCacheHolder.getLoginTimes(userAuthenticationDefinition.getRequest());
        if (loginTimes > AuthenticationConstants.MAX_LOGIN_FALIED_TIMES) {
            return false;
        }
        return true;
    }

    /**
     * 默认处理器
     */
    public void defaultHandler() {
        UserAuthenticationDefinition userAuthenticationDefinition = userAuthenticationDefinitionTL.get();
        HttpServletRequest request = userAuthenticationDefinition.getRequest();

        // 校验是否已登录失败超过次数
        if (checkLoginFailedTimes()) {
            if (checkUsernameAndPassword()) {
                // 登录成功

                LoginTimesCacheHolder.clearLoginTimes(request);
            } else {
                // 登录失败

                LoginTimesCacheHolder.incrementLoginTimes(request);

            }
        } else {

            // 验证码策略
            if (checkCaptcha()) {

            }

        }


        // 登录失败超次数，则使用相应的策略

        // 验证码策略
        // 检测用户名、密码和验证码


        // 检测用户名和密码
    }
}
