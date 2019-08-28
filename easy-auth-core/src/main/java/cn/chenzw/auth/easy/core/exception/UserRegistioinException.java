package cn.chenzw.auth.easy.core.exception;

/**
 * 用户注册异常类
 *
 * @author chenzw
 */
public class UserRegistioinException extends RuntimeException {

    public UserRegistioinException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistioinException(String message) {
        super(message);
    }
}
