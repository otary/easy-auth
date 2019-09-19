package cn.chenzw.auth.easy.core.support;

import cn.chenzw.toolkit.http.RequestUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 登录次数缓存
 *
 * @author chenzw
 */
public class LoginTimesCacheHolder {

    public static final Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterAccess(15, TimeUnit.MINUTES).build();

    /**
     * 登录次数+1
     *
     * @param request
     */
    public static final void incrementLoginTimes(HttpServletRequest request) {
        String clientIp = RequestUtils.getClientIp(request);
        Integer counts = cache.getIfPresent(clientIp);
        if (counts == null) {
            counts = 0;
        }
        cache.put(clientIp, counts++);
    }

    /**
     * 清空登录次数
     *
     * @param request
     */
    public static final void clearLoginTimes(HttpServletRequest request) {
        cache.invalidate(RequestUtils.getClientIp(request));
    }

    /**
     * 获取登录次数
     *
     * @return
     */
    public static final int getLoginTimes(HttpServletRequest request) {
        String clientIp = RequestUtils.getClientIp(request);
        return ObjectUtils.defaultIfNull(cache.getIfPresent(clientIp), 0);
    }
}



