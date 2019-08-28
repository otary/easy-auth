package cn.chenzw.auth.easy.api.support.condition;

import cn.chenzw.user.registration.constants.UserRegistrationConstants;
import cn.chenzw.user.registration.spring.service.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author chenzw
 */
public class MissingUserRegistrationService implements Condition {

    private static final Logger logger = LoggerFactory.getLogger(MissingUserRegistrationService.class);


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getBeanFactory().containsBean(UserRegistrationConstants.DEFAULT_USER_REGISTRATION_SERVICE)) {
            logger.debug("Use EasyUserRegistrationService: {}", context.getBeanFactory().getBean(UserRegistrationConstants.DEFAULT_USER_REGISTRATION_SERVICE));
            return false;
        }
        logger.debug("Missing EasyUserRegistrationService, use {}", UserRegistrationService.class.getCanonicalName());
        return true;
    }
}
