package cn.chenzw.auth.easy.api.config;

import cn.chenzw.auth.easy.api.support.condition.MissingUserRegistrationBean;
import cn.chenzw.auth.easy.api.support.condition.MissingUserRegistrationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration(UserRegistrationConstants.DEFAULT_USER_REGINSTATION_CONFIG)
@Conditional(MissingUserRegistrationConfig.class)
public class EasyUserRegistrationConfig {

    @Bean
    @Conditional(MissingUserRegistrationBean.class)
    public EasyUserRegistration defaultUserRegistration() {
        return new DefaultEasyUserRegistration();
    }

}