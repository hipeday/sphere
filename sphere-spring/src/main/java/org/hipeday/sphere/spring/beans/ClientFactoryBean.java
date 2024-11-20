package org.hipeday.sphere.spring.beans;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

/**
 * 接口客户端工厂bean
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ClientFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private Class<T> clientInterface;

    private ApplicationContext applicationContext;

    private volatile SphereConfiguration configuration;

    public void setClientInterface(Class<T> clientInterface) {
        this.clientInterface = clientInterface;
    }

    @Override
    public T getObject() throws Exception {
        if (configuration == null) {
            synchronized (this) {
                if (configuration == null) {
                    try {
                        configuration = applicationContext.getBean(SphereConfiguration.class);
                    } catch (BeansException ignored) {}
                    if (configuration == null) {
                        configuration = SphereConfiguration.getDefaultConfiguration();
                    }
                }
            }
        }
        return configuration.createClient(clientInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return clientInterface;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
