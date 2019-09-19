package cn.chenzw.auth.easy.api.config;

import cn.chenzw.auth.easy.core.core.EasyUserAuthentication;
import cn.chenzw.auth.easy.core.examples.DefaultEasyUserAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan(basePackages = {"cn.chenzw.auth.easy"})
public class EasyUserAuthenticationConfig {

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public EasyUserAuthentication defaultUserAuthentication() {
        return new DefaultEasyUserAuthentication();
    }
}
