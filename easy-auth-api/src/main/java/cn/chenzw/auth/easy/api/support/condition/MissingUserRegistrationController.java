package cn.chenzw.auth.easy.api.support.condition;

import cn.chenzw.user.registration.constants.UserRegistrationConstants;
import cn.chenzw.user.registration.spring.controllers.UserRegistrationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author chenzw
 */
public class MissingUserRegistrationController implements Condition {

    private static final Logger logger = LoggerFactory.getLogger(MissingUserRegistrationController.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        if (context.getBeanFactory().containsBean(UserRegistrationConstants.DEFAULT_USER_REGISTRATION_CONTROLLER)) {
            logger.debug("Use UserRegistrationController: {}", context.getBeanFactory().getBean(UserRegistrationConstants.DEFAULT_USER_REGISTRATION_CONTROLLER));
            return false;
        }
        logger.debug("Missing UserRegistrationController, use {}", UserRegistrationController.class.getCanonicalName());
        return true;
    }
}
