package cn.chenzw.auth.easy.core.constants.enums;

public enum AuthenticationExceptionContext {

    LOGIN_LOCK(1, "帐号登录锁定,请5分钟后重试"),
    ACCOUNT_OR_PWD_INVALID(2, "用户名或密码不正确!"),
    CAPTCHA_INVALID(3, "验证码不正确!"),
    USERNAME_EMPTY(5, "用户名为空!"),
    PASSWORD_EMPTY(12, "密码为空!"),
    CAPTCHA_EMPTY(6, "验证码为空!");

    private Integer code;
    private String msg;

    AuthenticationExceptionContext(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
