package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;

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
        return false;
    }

    @Override
    public boolean check() {
        return false;
    }
}
