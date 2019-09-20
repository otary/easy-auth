package cn.chenzw.auth.easy.core.exception;

import cn.chenzw.auth.easy.core.constants.enums.EasyAuthenticationExceptionContext;

/**
 * 认证异常
 *
 * @author chenzw
 */
public class EasyAuthenticationException extends RuntimeException {

    private Integer code;
    private String msg;


    public EasyAuthenticationException(Integer code, String message) {
        super(message);

        this.code = code;
        this.msg = message;
    }

    public EasyAuthenticationException(EasyAuthenticationExceptionContext aec) {
        super(aec.getMsg());

        this.code = aec.getCode();
        this.msg = aec.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
