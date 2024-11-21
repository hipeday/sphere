package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.context.ApplicationContext;
import org.hipeday.sphere.core.registry.support.InterceptorRegistry;

/**
 * 拦截器注册表实现拦截器工厂实现
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractRegistryInterceptorFactory implements InterceptorFactory {

    protected final InterceptorRegistry interceptorRegistry;

    public AbstractRegistryInterceptorFactory() {
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        this.interceptorRegistry = applicationContext.getInterceptorRegistry();
    }
}
