package cn.chenzw.auth.easy.core.definition;


import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.constants.enums.EasyAuthenticationExceptionContext;
import cn.chenzw.auth.easy.core.exception.EasyAuthenticationException;
import cn.chenzw.toolkit.http.HttpHolder;
import cn.chenzw.toolkit.http.RequestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 认证信息封装
 *
 * @author chenzw
 */
public class EasyUserAuthenticationDefinition {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private ObjectMapper objectMapper;

    public EasyUserAuthenticationDefinition() {
        this.request = HttpHolder.getRequest();
        this.response = HttpHolder.getResponse();
        this.objectMapper = new ObjectMapper();

        this.init();
    }

    public EasyUserAuthenticationDefinition(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.objectMapper = new ObjectMapper();

        this.init();
    }

    private void init() {
        try {
            String body = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
            if (!StringUtils.isEmpty(body)) {
                Map<String, String> bodyMap = objectMapper.readValue(body, Map.class);

                this.userName = MapUtils.getString(bodyMap, EasyAuthenticationConstants.USER_NAME_IDENTIFIER);
                if (StringUtils.isEmpty(userName)) {
                    throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.USERNAME_EMPTY);
                }

                this.pwd = MapUtils.getString(bodyMap, EasyAuthenticationConstants.PASSWORD_IDENTIFIER);
                if (StringUtils.isEmpty(pwd)) {
                    throw new EasyAuthenticationException(EasyAuthenticationExceptionContext.PASSWORD_EMPTY);
                }
                this.captcha = MapUtils.getString(bodyMap, EasyAuthenticationConstants.CAPTCHA_IDENTIFIER);
                this.rememberMe = MapUtils.getBoolean(bodyMap, EasyAuthenticationConstants.REMEMBER_ME_IDENTIFIER);

                this.extParams = bodyMap;
            }
        } catch (IOException e) {
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
    private Map<String, String> extParams;


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

    public Map<String, String> getExtParams() {
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
