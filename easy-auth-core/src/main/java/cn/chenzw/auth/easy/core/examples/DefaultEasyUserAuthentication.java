package cn.chenzw.auth.easy.core.examples;

import cn.chenzw.auth.easy.core.constants.AuthenticationConstants;
import cn.chenzw.auth.easy.core.core.AbstractEasyUserAuthentication;
import cn.chenzw.auth.easy.core.definition.EasyUserDefinition;
import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import org.apache.commons.lang3.StringUtils;

/**
 * 默认认证
 *
 * @author chenzw
 */
public class DefaultEasyUserAuthentication extends AbstractEasyUserAuthentication {


    @Override
    public boolean checkUsernameAndPassword(UserAuthenticationDefinition userAuthenticationDefinition) {

        for (EasyUserDefinition memoeryUser : AuthenticationConstants.MEMOERY_USERS) {
            if (StringUtils.equals(memoeryUser.getUserName(), userAuthenticationDefinition.getUserName())
                    && StringUtils.equals(memoeryUser.getPassword(), userAuthenticationDefinition.getPwd())) {
                return true;
            }
        }
        return false;
    }
}
