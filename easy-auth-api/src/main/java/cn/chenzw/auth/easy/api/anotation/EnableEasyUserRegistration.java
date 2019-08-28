package cn.chenzw.auth.easy.api.anotation;

import cn.chenzw.auth.easy.api.config.EasyUserRegistrationConfig;
import cn.chenzw.auth.easy.core.constants.UserRegistrationConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启注册服务
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackages = {UserRegistrationConstants.BASE_PACKAGE})
@Import({EasyUserRegistrationConfig.class})
public @interface EnableEasyUserRegistration {

}
