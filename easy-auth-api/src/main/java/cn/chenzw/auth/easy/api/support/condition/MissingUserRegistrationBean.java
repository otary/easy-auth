package cn.chenzw.auth.easy.api.support.condition;


import cn.chenzw.user.registration.core.UserRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author chenzw
 */
public class MissingUserRegistrationBean implements Condition {

    private static final Logger logger = LoggerFactory.getLogger(MissingUserRegistrationBean.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, UserRegistration> beans = context.getBeanFactory().getBeansOfType(UserRegistration.class);
        if (beans == null || beans.isEmpty()) {
            logger.debug("Missing EasyUserRegistration bean");
            return false;
        }
        logger.debug("use EasyUserRegistration bean: {}", beans);
        return true;
    }
}
