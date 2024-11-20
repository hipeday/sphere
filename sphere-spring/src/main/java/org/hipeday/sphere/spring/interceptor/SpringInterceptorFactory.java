package org.hipeday.sphere.spring.interceptor;

import org.hipeday.sphere.core.interceptor.DefaultInterceptorFactory;
import org.hipeday.sphere.core.interceptor.Interceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring拦截器工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SpringInterceptorFactory extends DefaultInterceptorFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public <T extends Interceptor> T getInterceptor(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Throwable th) {
            return super.getInterceptor(clazz);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
