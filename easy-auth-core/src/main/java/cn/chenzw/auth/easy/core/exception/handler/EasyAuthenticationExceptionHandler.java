package cn.chenzw.auth.easy.core.exception.handler;

import cn.chenzw.auth.easy.core.exception.EasyAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class EasyAuthenticationExceptionHandler {


    @ResponseBody
    @ExceptionHandler(EasyAuthenticationException.class)
    public Object handleAuthenctionException(EasyAuthenticationException e) {
        return new HashMap() {
            {
                put("code", e.getCode());
                put("msg", e.getMsg());
            }
        };
    }
}
