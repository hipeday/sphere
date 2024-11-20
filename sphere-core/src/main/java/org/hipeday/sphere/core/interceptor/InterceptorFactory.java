package org.hipeday.sphere.core.interceptor;

/**
 * 拦截器工厂
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public interface InterceptorFactory {

    InterceptorChain getInterceptorChain();

    <T extends Interceptor> T getInterceptor(Class<T> clazz);

}
