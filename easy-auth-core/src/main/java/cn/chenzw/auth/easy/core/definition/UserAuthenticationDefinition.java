package cn.chenzw.auth.easy.core.definition;


import cn.chenzw.auth.easy.core.constants.AuthenticationConstants;
import cn.chenzw.toolkit.http.HttpHolder;
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
public class UserAuthenticationDefinition {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public UserAuthenticationDefinition() {
        this.request = HttpHolder.getRequest();
        this.response = HttpHolder.getResponse();

        this.init();
    }

    public UserAuthenticationDefinition(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.init();
    }

    private void init() {
        try {
            this.userName = ServletRequestUtils
                    .getStringParameter(request, AuthenticationConstants.USER_NAME_IDENTIFIER);
            this.pwd = ServletRequestUtils.getStringParameter(request, AuthenticationConstants.PASSWORD_IDENTIFIER);
            this.captcha = ServletRequestUtils.getStringParameter(request, AuthenticationConstants.CAPTCHA_IDENTIFIER);
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

    /**
     * 获取会话中的验证码
     *
     * @return
     */
    public String getSessionCaptcha() {
        return (String) request.getSession(true).getAttribute(AuthenticationConstants.LOGIN_CAPTCHA_SESSION);
    }

}
