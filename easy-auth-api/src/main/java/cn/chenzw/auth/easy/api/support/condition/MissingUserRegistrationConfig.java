package cn.chenzw.auth.easy.api.support.condition;

import cn.chenzw.user.registration.constants.UserRegistrationConstants;
import cn.chenzw.user.registration.spring.config.UserRegistrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author chenzw
 */
public class MissingUserRegistrationConfig implements Condition {

    private static final Logger logger = LoggerFactory.getLogger(MissingUserRegistrationConfig.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getBeanFactory().containsBean(UserRegistrationConstants.DEFAULT_USER_REGINSTATION_CONFIG)) {
            logger.debug("Use EasyUserRegistrationConfig: {}", context.getBeanFactory().getBean(UserRegistrationConstants.DEFAULT_USER_REGINSTATION_CONFIG));
            return false;
        }
        logger.debug("Missing EasyUserRegistrationConfig, use {}", UserRegistrationConfig.class.getCanonicalName());
        return true;
    }
}
