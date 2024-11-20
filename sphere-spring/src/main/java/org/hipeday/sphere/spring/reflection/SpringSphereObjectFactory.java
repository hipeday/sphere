package org.hipeday.sphere.spring.reflection;

import org.hipeday.sphere.core.reflection.DefaultObjectFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring Sphere对象工厂
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class SpringSphereObjectFactory extends DefaultObjectFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T getObject(Class<T> clazz) {
        T bean = getObjectFromCache(clazz);
        if (bean == null) {
            try {
                bean = applicationContext.getBean(clazz);
            } catch (BeansException ignored) {}
            if (bean == null) {
                bean = super.getObject(clazz);
            }
        }
        return bean;
    }
}
