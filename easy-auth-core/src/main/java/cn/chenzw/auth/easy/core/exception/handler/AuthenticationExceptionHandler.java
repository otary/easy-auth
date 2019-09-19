package cn.chenzw.auth.easy.core.exception.handler;

import cn.chenzw.auth.easy.core.exception.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class AuthenticationExceptionHandler {


    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthenctionException(AuthenticationException e) {
        return new HashMap() {
            {
                put("code", e.getCode());
                put("msg", e.getMsg());
            }
        };
    }
}
