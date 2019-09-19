package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.AuthenticationConstants;
import cn.chenzw.auth.easy.core.constants.enums.AuthenticationExceptionContext;
import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.exception.AuthenticationException;
import cn.chenzw.auth.easy.core.support.LoginTimesCacheHolder;

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

    private void incrementLoginTimes() {
        LoginTimesCacheHolder.incrementLoginTimes(userAuthenticationDefinitionTL.get().getRequest());
    }

    private void clearLoginTimes() {
        LoginTimesCacheHolder.clearLoginTimes(userAuthenticationDefinitionTL.get().getRequest());
    }

    // 登录逻辑
    private void doLoginInternal() {
        if (!checkUsernameAndPassword()) {
            // 登录失败
            incrementLoginTimes();
            throw new AuthenticationException(AuthenticationExceptionContext.ACCOUNT_OR_PWD_INVALID);
        }

        // 登录成功
        clearLoginTimes();
    }


    private void doLoginWithCaptchaInternal() {
        if (!checkCaptcha()) {
            throw new AuthenticationException(AuthenticationExceptionContext.CAPTCHA_INVALID);
        }
        doLoginInternal();
    }

    private void doLoginLockInternal() {
        throw new AuthenticationException(AuthenticationExceptionContext.LOGIN_LOCK);
    }

    /**
     * 默认处理器
     */
    public void defaultHandler() {

        // 校验是否已登录失败超过次数
        if (checkLoginFailedTimes()) {
            doLoginInternal();
        } else {
            // 登录失败超次数，则使用相应的策略
            if ("captcha".equalsIgnoreCase(AuthenticationConstants.LOGIN_FAIL_STRATEGY)) {
                // 验证码策略
                doLoginWithCaptchaInternal();
            } else if ("lock".equalsIgnoreCase(AuthenticationConstants.LOGIN_FAIL_STRATEGY)) {
                // 锁定5分钟
                doLoginLockInternal();
            }
        }
    }
}
