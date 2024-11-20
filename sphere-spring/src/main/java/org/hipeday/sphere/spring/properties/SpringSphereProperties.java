package org.hipeday.sphere.spring.properties;

import org.hipeday.sphere.core.config.SphereProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * Spring Sphere配置文件属性
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class SpringSphereProperties extends SphereProperties implements ApplicationContextAware {

    private Environment environment;

    @Override
    public String getProperty(String key, String defaultValue) {
        if (environment == null) {
            return super.getProperty(key, defaultValue);
        } else {
            return environment.getProperty(key, defaultValue);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        environment = applicationContext.getEnvironment();
    }
}
