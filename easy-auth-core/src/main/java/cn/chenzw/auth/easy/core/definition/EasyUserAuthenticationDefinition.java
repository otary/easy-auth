package cn.chenzw.auth.easy.core.definition;


import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.constants.enums.EasyAuthenticationExceptionContext;
import cn.chenzw.auth.easy.core.exception.EasyAuthenticationException;
import cn.chenzw.toolkit.http.HttpHolder;
import cn.chenzw.toolkit.http.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 认证信息封装
 *
 * @author chenzw
 */
public class EasyUserAuthenticationDefinition {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public EasyUserAuthenticationDefinition() {
        this.request = HttpHolder.getRequest();
        this.response = HttpHolder.getResponse();

        this.init();
    }

    public EasyUserAuthenticationDefinition(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.init();
    }

    private void init() {
        try {
            this.userName = ServletRequestUtils
                    .getStringParameter(request, EasyAuthenticationConstants.USER_NAME_IDENTIFIER);
            if (StringUtils.isEmpty(userName)) {
                throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.USERNAME_EMPTY);
            }

            this.pwd = ServletRequestUtils.getStringParameter(request, EasyAuthenticationConstants.PASSWORD_IDENTIFIER);
            if (StringUtils.isEmpty(pwd)) {
                throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.PASSWORD_EMPTY);
            }
            this.captcha = ServletRequestUtils.getStringParameter(request, EasyAuthenticationConstants.CAPTCHA_IDENTIFIER);
            this.rememberMe = ServletRequestUtils.getBooleanParameter(request, EasyAuthenticationConstants.REMEMBER_ME_IDENTIFIER);
            this.extParams = request.getParameterMap();
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码
     */
    private String pwd;


    /**
     * 其余的请求参数
     */
    private Map<String, String[]> extParams;


    /**
     * 验证码
     */
    private String captcha;

    /**
     * 记住
     */
    private Boolean rememberMe;


    public String getUserName() {
        return userName;
    }


    public String getPwd() {
        return pwd;
    }

    public Map<String, String[]> getExtParams() {
        return extParams;
    }


    public String getCaptcha() {
        return captcha;
    }


    public HttpServletRequest getRequest() {
        return request;
    }


    public HttpServletResponse getResponse() {
        return response;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    /**
     * 获取会话中的验证码
     *
     * @return
     */
    public String getSessionCaptcha() {
        return (String) request.getSession(true).getAttribute(EasyAuthenticationConstants.LOGIN_CAPTCHA_SESSION);
    }


    /**
     * 获取客户端IP
     *
     * @return
     */
    public String getClientIp() {
        return RequestUtils.getClientIp(request);
    }

}
