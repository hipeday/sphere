package org.hipeday.sphere.core.interceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认拦截器工厂
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class DefaultInterceptorFactory implements InterceptorFactory {

    /**
     * 拦截器实例缓存
     */
    protected final Map<Class<?>, Interceptor> INTERCEPTOR_CACHE = new ConcurrentHashMap<>();

    /**
     * 拦截器调用链
     */
    protected final InterceptorChain interceptorChain = new InterceptorChain();

    @Override
    public InterceptorChain getInterceptorChain() {
        return interceptorChain;
    }

    @Override
    public <T extends Interceptor> T getInterceptor(Class<T> clazz) {
        return (T) INTERCEPTOR_CACHE.computeIfAbsent(clazz, k -> {
            try {
                Constructor<? extends Interceptor> constructor = clazz.getConstructor();
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new IllegalArgumentException("Failed to create interceptor instance", e);
            }
        });
    }

}
