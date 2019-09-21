package cn.chenzw.auth.easy.core.examples;

import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.core.AbstractEasyUserAuthentication;
import cn.chenzw.auth.easy.core.definition.EasyUserAuthenticationDefinition;
import cn.chenzw.auth.easy.core.definition.EasyUserDefinition;
import org.apache.commons.lang3.StringUtils;

/**
 * 默认认证
 *
 * @author chenzw
 */
public class DefaultEasyUserAuthentication extends AbstractEasyUserAuthentication {

    @Override
    public boolean checkUsernameAndPassword(EasyUserAuthenticationDefinition easyUserAuthenticationDefinition) {
        for (EasyUserDefinition memoeryUser : EasyAuthenticationConstants.MEMOERY_USERS) {
            if (StringUtils.equals(memoeryUser.getUserName(), easyUserAuthenticationDefinition.getUserName())
                    && StringUtils.equals(memoeryUser.getPassword(), easyUserAuthenticationDefinition.getPwd())) {
                return true;
            }
        }
        return false;
    }
}
