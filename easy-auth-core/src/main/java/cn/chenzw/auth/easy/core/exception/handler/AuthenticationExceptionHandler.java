package cn.chenzw.auth.easy.core.exception.handler;

import cn.chenzw.auth.easy.core.exception.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthenctionException(AuthenticationException e) {
        System.out.println("-----------------------异常");

        return "aaaaaaaaaaaaaaaaaaa";
        /*return new HashMap() {
            {
                put("code", e.getCode());
                put("msg", e.getMsg());
            }
        };*/
    }
}
