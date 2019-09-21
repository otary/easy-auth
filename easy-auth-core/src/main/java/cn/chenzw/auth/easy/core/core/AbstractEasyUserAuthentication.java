package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.constants.enums.EasyAuthenticationExceptionContext;
import cn.chenzw.auth.easy.core.definition.EasyUserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.exception.EasyAuthenticationException;
import cn.chenzw.auth.easy.core.support.LoginTimesCacheHolder;
import org.apache.commons.lang3.StringUtils;

/**
 * 抽象用户认证
 *
 * @author chenzw
 */
public abstract class AbstractEasyUserAuthentication implements EasyUserAuthentication {

    private EasyUserAuthenticationDefinition userAuthenticationDefinition;

    public AbstractEasyUserAuthentication() {
        this.userAuthenticationDefinition = new EasyUserAuthenticationDefinition();
    }

    public abstract boolean checkUsernameAndPassword(EasyUserAuthenticationDefinition easyUserAuthenticationDefinition);

    /**
     * 登录校验
     *
     * @return
     */
    private boolean checkUsernameAndPassword() {
        return checkUsernameAndPassword(userAuthenticationDefinition);
    }

    /**
     * 校验验证码
     *
     * @return
     */
    protected boolean checkCaptcha() {
        if (StringUtils.isEmpty(userAuthenticationDefinition.getCaptcha())) {
            throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.CAPTCHA_EMPTY);
        }

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
    @Override
    public boolean checkLoginFailedTimes() {
        int loginTimes = LoginTimesCacheHolder.getLoginTimes(userAuthenticationDefinition);
        if (loginTimes > EasyAuthenticationConstants.MAX_LOGIN_FALIED_TIMES) {
            return false;
        }
        return true;
    }

    private void incrementLoginTimes() {
        LoginTimesCacheHolder.incrementLoginTimes(userAuthenticationDefinition);
    }

    private void clearLoginTimes() {
        LoginTimesCacheHolder.clearLoginTimes(userAuthenticationDefinition);
    }

    /**
     * 登录逻辑
     */
    private void doLoginInternal() {
        if (!checkUsernameAndPassword()) {
            // 登录失败
            incrementLoginTimes();

            throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.ACCOUNT_OR_PWD_INVALID);
        }

        // 登录成功
        clearLoginTimes();
    }


    /**
     * 带验证码的登录
     */
    private void doLoginWithCaptchaInternal() {
        if (!checkCaptcha()) {
            throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.CAPTCHA_INVALID);
        }
        doLoginInternal();
    }

    private void doLoginLockInternal() {
        throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.LOGIN_LOCK);
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
            if ("captcha".equalsIgnoreCase(EasyAuthenticationConstants.LOGIN_FAIL_STRATEGY)) {
                // 验证码策略
                doLoginWithCaptchaInternal();
            } else if ("lock".equalsIgnoreCase(EasyAuthenticationConstants.LOGIN_FAIL_STRATEGY)) {
                // 锁定5分钟
                doLoginLockInternal();
            }
        }
    }
}
