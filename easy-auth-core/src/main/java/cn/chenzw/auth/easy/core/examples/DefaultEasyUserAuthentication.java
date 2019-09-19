package cn.chenzw.auth.easy.core.examples;

import cn.chenzw.auth.easy.core.core.AbstractEasyUserAuthentication;
import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;

/**
 * 默认认证
 *
 * @author chenzw
 */
public class DefaultEasyUserAuthentication extends AbstractEasyUserAuthentication {


    @Override
    public boolean checkUsernameAndPassword(UserAuthenticationDefinition userAuthenticationDefinition) {
        return false;
    }
}
