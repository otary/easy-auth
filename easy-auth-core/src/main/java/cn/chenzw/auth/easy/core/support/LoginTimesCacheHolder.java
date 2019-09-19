package cn.chenzw.auth.easy.core.support;

import cn.chenzw.auth.easy.core.definition.UserAuthenticationDefinition;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * 登录次数缓存
 *
 * @author chenzw
 */
public class LoginTimesCacheHolder {

    public static final Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    private static final String CACHE_KEY = "%s@@%s";

    private static String generateCacheKey(String clientIp, String userName) {
        return String.format(CACHE_KEY, clientIp, userName);
    }

    /**
     * 登录次数+1
     *
     * @param userAuthenticationDefinition
     */
    public static final void incrementLoginTimes(UserAuthenticationDefinition userAuthenticationDefinition) {
        String cacheKey = generateCacheKey(userAuthenticationDefinition.getClientIp(), userAuthenticationDefinition.getUserName());
        Integer counts = cache.getIfPresent(cacheKey);
        if (counts == null) {
            counts = 0;
        }
        cache.put(cacheKey, ++counts);
    }

    /**
     * 清空登录次数
     *
     * @param userAuthenticationDefinition
     */
    public static final void clearLoginTimes(UserAuthenticationDefinition userAuthenticationDefinition) {
        String cacheKey = generateCacheKey(userAuthenticationDefinition.getClientIp(), userAuthenticationDefinition.getUserName());
        cache.invalidate(cacheKey);
    }

    /**
     * 获取登录次数
     *
     * @return
     */
    public static final int getLoginTimes(UserAuthenticationDefinition userAuthenticationDefinition) {
        String cacheKey = generateCacheKey(userAuthenticationDefinition.getClientIp(), userAuthenticationDefinition.getUserName());
        return ObjectUtils.defaultIfNull(cache.getIfPresent(cacheKey), 0);
    }
}



