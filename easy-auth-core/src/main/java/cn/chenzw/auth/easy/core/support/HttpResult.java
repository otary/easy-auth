package cn.chenzw.auth.easy.core.support;

import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;

/**
 * 响应类
 *
 * @author chenzw
 */
public class HttpResult {

    private Integer code;
    private String msg;
    private Boolean showCaptcha = Boolean.FALSE;
    private String captchaUri;

    public HttpResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResult(Integer code, String msg, boolean showCaptcha) {
        this.code = code;
        this.msg = msg;
        this.showCaptcha = showCaptcha;
        this.captchaUri = EasyAuthenticationConstants.CAPTCHA_URI;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getShowCaptcha() {
        return showCaptcha;
    }

    public void setShowCaptcha(Boolean showCaptcha) {
        this.showCaptcha = showCaptcha;
    }

    public String getCaptchaUri() {
        return captchaUri;
    }

    public void setCaptchaUri(String captchaUri) {
        this.captchaUri = captchaUri;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", showCaptcha=" + showCaptcha +
                ", captchaUri='" + captchaUri + '\'' +
                '}';
    }
}
