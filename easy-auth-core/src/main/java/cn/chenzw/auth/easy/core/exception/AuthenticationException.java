package cn.chenzw.auth.easy.core.exception;

import cn.chenzw.auth.easy.core.constants.enums.AuthenticationExceptionContext;

/**
 * 认证异常
 *
 * @author chenzw
 */
public class AuthenticationException extends RuntimeException {

    private Integer code;
    private String msg;


    public AuthenticationException(Integer code, String message) {
        super(message);

        this.code = code;
        this.msg = message;
    }

    public AuthenticationException(AuthenticationExceptionContext exceptionContext) {
        this.code = exceptionContext.getCode();
        this.msg = exceptionContext.getMsg();
    }
}
