package org.hipeday.sphere.core.interceptor.support;

import org.hipeday.sphere.core.interceptor.AbstractRegistryInterceptorFactory;
import org.hipeday.sphere.core.interceptor.Interceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 默认拦截器工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class DefaultInterceptorFactory extends AbstractRegistryInterceptorFactory {

    public DefaultInterceptorFactory() {
        super();
    }

    @Override
    public <T extends Interceptor> T getInterceptor(Class<T> clazz) {
        return (T) interceptorRegistry.computeIfUnregister(clazz, k -> {
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
