package cn.chenzw.auth.easy.api.config;

import cn.chenzw.auth.easy.core.core.EasyUserAuthentication;
import cn.chenzw.auth.easy.core.examples.DefaultEasyUserAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class EasyUserAuthenticationConfig {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public EasyUserAuthentication defaultUserAuthentication() {
        return new DefaultEasyUserAuthentication();
    }
}
