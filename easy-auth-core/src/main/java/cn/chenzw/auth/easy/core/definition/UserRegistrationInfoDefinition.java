package cn.chenzw.auth.easy.core.definition;

import cn.chenzw.auth.easy.core.constants.UserRegistrationConstants;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 用户注册信息封装类
 *
 * @author chenzw
 */
public class UserRegistrationInfoDefinition {

    private HttpServletRequest request;

    public UserRegistrationInfoDefinition() {
        this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.init();
    }

    public UserRegistrationInfoDefinition(HttpServletRequest request) {
        this.request = request;
        this.init();
    }

    private void init() {
        try {
            this.userName = ServletRequestUtils
                    .getStringParameter(request, UserRegistrationConstants.USER_NAME_IDENTIFIER);
            this.phoneNo = ServletRequestUtils
                    .getStringParameter(request, UserRegistrationConstants.PHONE_NO_IDENTIFIER);
            this.pwd = ServletRequestUtils.getStringParameter(request, UserRegistrationConstants.PASSWORD_IDENTIFIER);
            this.pwds = request.getParameterValues(UserRegistrationConstants.PASSWORD_IDENTIFIER);
            this.email = ServletRequestUtils.getStringParameter(request, UserRegistrationConstants.EMAIL_IDENTIFIER);
            this.smsCode = ServletRequestUtils.getStringParameter(request, UserRegistrationConstants.SMS_CODE_IDENTIFIER);

            this.extParams = request.getParameterMap();

        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册用户名
     */
    private String userName;

    /**
     * 注册手机号
     */
    private String phoneNo;

    /**
     * 注册邮箱
     */
    private String email;


    /**
     * 密码
     */
    private String pwd;

    /**
     * 包含确认密码
     */
    private String[] pwds;


    /**
     * 其余的请求参数
     */
    private Map<String, String[]> extParams;

    /**
     * 短信验证码
     */
    private String smsCode;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Map<String, String[]> getExtParams() {
        return extParams;
    }

    public void setExtParams(Map<String, String[]> extParams) {
        this.extParams = extParams;
    }

    public String[] getPwds() {
        return pwds;
    }

    public void setPwds(String[] pwds) {
        this.pwds = pwds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getRandonSmsCode() {
        Object oRandomCode = request.getSession().getAttribute(UserRegistrationConstants.SMS_CODE_SESSION_IDENTIFIER);

        if (oRandomCode != null) {
            return (String) oRandomCode;
        }
        return null;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "UserRegistrationInfoDefinition{" + "request=" + request + ", userName='" + userName + '\''
                + ", phoneNo='" + phoneNo + '\'' + ", email='" + email + '\'' + ", pwd='" + pwd + '\'' + ", pwds="
                + Arrays.toString(pwds) + ", extParams=" + extParams + ", smsCode='" + smsCode + '\'' + '}';
    }
}
