package cn.chenzw.auth.easy.api.anotation;

import cn.chenzw.auth.easy.api.config.EasyUserAuthenticationConfig;
import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启简易认证服务
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackages = {EasyAuthenticationConstants.BASE_PACKAGE})
@Import({EasyUserAuthenticationConfig.class})
public @interface EnableEasyUserAuthentication {
}
