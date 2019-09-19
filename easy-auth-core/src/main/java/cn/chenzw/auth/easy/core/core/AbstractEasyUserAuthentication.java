package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.AuthenticationConstants;
import cn.chenzw.auth.easy.core.constants.enums.AuthenticationExceptionContext;
import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.exception.AuthenticationException;
import cn.chenzw.auth.easy.core.support.LoginTimesCacheHolder;
import org.apache.commons.lang3.StringUtils;

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

    /**
     * 登录校验
     *
     * @return
     */
    private boolean checkUsernameAndPassword() {
        return checkUsernameAndPassword(getUserAuthenticationDefinition());
    }

    /**
     * 校验验证码
     *
     * @return
     */
    protected boolean checkCaptcha() {
        UserAuthenticationDefinition userAuthenticationDefinition = userAuthenticationDefinitionTL.get();

        if (StringUtils.equalsIgnoreCase(userAuthenticationDefinition.getCaptcha(), userAuthenticationDefinition.getSessionCaptcha())) {
            return true;
        }
        return false;
    }

    /**
     * 检测登录失败次数
     *
     * @return
     */
    protected boolean checkLoginFailedTimes() {
        int loginTimes = LoginTimesCacheHolder.getLoginTimes(userAuthenticationDefinitionTL.get());
        if (loginTimes > AuthenticationConstants.MAX_LOGIN_FALIED_TIMES) {
            return false;
        }
        return true;
    }

    private void incrementLoginTimes() {
        LoginTimesCacheHolder.incrementLoginTimes(userAuthenticationDefinitionTL.get());
    }

    private void clearLoginTimes() {
        LoginTimesCacheHolder.clearLoginTimes(userAuthenticationDefinitionTL.get());
    }

    /**
     * 登录逻辑
     */
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
     * 默认处理
     */
    public void login() {

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
