package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.AuthenticationConstants;
import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.support.LoginTimesCacheHolder;

/**
 * 抽象用户认证
 *
 * @author chenzw
 */
public abstract class AbstractEasyUserAuthentication implements EasyUserAuthentication {

    protected static LoginTimesCacheHolder loginTimesCacheHolder;

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
        int loginTimes = loginTimesCacheHolder.getLoginTimes(userAuthenticationDefinition.getRequest());
        if (loginTimes > AuthenticationConstants.MAX_LOGIN_FALIED) {
            return false;
        }
        return true;
    }
}
