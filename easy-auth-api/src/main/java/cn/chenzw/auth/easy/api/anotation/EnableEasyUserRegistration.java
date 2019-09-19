package cn.chenzw.auth.easy.api.anotation;

import java.lang.annotation.*;

/**
 * 开启注册服务
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@ComponentScan(basePackages = {UserRegistrationConstants.BASE_PACKAGE})
//@Import({EasyUserRegistrationConfig.class})
public @interface EnableEasyUserRegistration {

}
