package cn.chenzw.auth.easy.core.core;

import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.constants.enums.EasyAuthenticationExceptionContext;
import cn.chenzw.auth.easy.core.definition.EasyUserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.exception.EasyAuthenticationException;
import cn.chenzw.auth.easy.core.support.LoginTimesCacheHolder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 抽象用户认证
 *
 * @author chenzw
 */
public abstract class AbstractEasyUserAuthentication implements EasyUserAuthentication {


    private ThreadLocal<EasyUserAuthenticationDefinition> userAuthenticationDefinitionTL = new ThreadLocal<>();

    protected EasyUserAuthenticationDefinition getUserAuthenticationDefinition() {
        if (userAuthenticationDefinitionTL.get() == null) {
            userAuthenticationDefinitionTL.set(new EasyUserAuthenticationDefinition());
        }
        return userAuthenticationDefinitionTL.get();
    }

    public abstract boolean checkUsernameAndPassword(EasyUserAuthenticationDefinition easyUserAuthenticationDefinition);

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
        EasyUserAuthenticationDefinition easyUserAuthenticationDefinition = getUserAuthenticationDefinition();

        if (StringUtils.isEmpty(easyUserAuthenticationDefinition.getCaptcha())) {
            throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.CAPTCHA_EMPTY);
        }

        if (StringUtils.equalsIgnoreCase(easyUserAuthenticationDefinition.getCaptcha(), easyUserAuthenticationDefinition.getSessionCaptcha())) {
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
        int loginTimes = LoginTimesCacheHolder.getLoginTimes(getUserAuthenticationDefinition());
        if (loginTimes > EasyAuthenticationConstants.MAX_LOGIN_FALIED_TIMES) {
            return false;
        }
        return true;
    }

    private void incrementLoginTimes() {
        LoginTimesCacheHolder.incrementLoginTimes(getUserAuthenticationDefinition());
    }

    private void clearLoginTimes() {
        LoginTimesCacheHolder.clearLoginTimes(getUserAuthenticationDefinition());
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

    @Override
    public String randomCaptchaCode() {
        return RandomStringUtils.randomAlphanumeric(4);
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
