package cn.chenzw.auth.easy.core.exception.handler;

import cn.chenzw.auth.easy.core.core.EasyUserAuthentication;
import cn.chenzw.auth.easy.core.exception.EasyAuthenticationException;
import cn.chenzw.auth.easy.core.support.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一认证异常处理类
 *
 * @author chenzw
 */
@ControllerAdvice
public class EasyAuthenticationExceptionHandler {

    @Autowired
    EasyUserAuthentication userAuthentication;

    @ResponseBody
    @ExceptionHandler(EasyAuthenticationException.class)
    public HttpResult handleAuthenctionException(EasyAuthenticationException e) {
        if (userAuthentication.checkLoginFailedTimes()) {
            return new HttpResult(e.getCode(), e.getMsg());
        }
        return new HttpResult(e.getCode(), e.getMsg(), true);
    }
}
