package org.hipeday.sphere.springboot;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.util.StringUtils;
import org.hipeday.sphere.spring.interceptor.SpringInterceptorFactory;
import org.hipeday.sphere.spring.properties.SpringSphereProperties;
import org.hipeday.sphere.spring.reflection.SpringSphereObjectFactory;
import org.hipeday.sphere.spring.scanner.ClassPathClientScanner;
import org.hipeday.sphere.springboot.annotation.SphereScannerRegister;
import org.hipeday.sphere.springboot.properties.SphereConfigurationProperties;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.util.List;
import java.util.Objects;

/**
 * Sphere Bean注册器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereBeanRegister implements ResourceLoaderAware {

    private final ConfigurableApplicationContext applicationContext;
    private final SphereConfigurationProperties configurationProperties;
    private final SpringSphereObjectFactory sphereObjectFactory;
    private final SpringInterceptorFactory interceptorFactory;
    private final SpringSphereProperties properties;

    private ResourceLoader resourceLoader;

    public SphereBeanRegister(ConfigurableApplicationContext applicationContext, SphereConfigurationProperties configurationProperties, SpringSphereObjectFactory sphereObjectFactory, SpringInterceptorFactory interceptorFactory, SpringSphereProperties properties) {
        this.applicationContext = applicationContext;
        this.configurationProperties = configurationProperties;
        this.sphereObjectFactory = sphereObjectFactory;
        this.interceptorFactory = interceptorFactory;
        this.properties = properties;
    }

    public void registerScanner() {
        List<String> scanPackages = SphereScannerRegister.getScanPackages();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getBeanFactory();

        ClassPathClientScanner scanner = new ClassPathClientScanner(registry);
        if (resourceLoader != null) {
            scanner.setResourceLoader(resourceLoader);
        }

        if (scanPackages.isEmpty()) {
            return;
        }
        scanner.doScan(Objects.requireNonNull(StringUtils.toStringArray(scanPackages)));
    }

    public void registerSphereConfiguration() {
        String beanName = "sphereConfiguration";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SphereConfiguration.class);
        beanDefinitionBuilder.setLazyInit(false);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();

        beanFactory.registerBeanDefinition(beanName, beanDefinition);

        SphereConfiguration configuration = applicationContext.getBean(beanName, SphereConfiguration.class);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public SphereConfigurationProperties getConfigurationProperties() {
        return configurationProperties;
    }

    public SpringSphereObjectFactory getSphereObjectFactory() {
        return sphereObjectFactory;
    }

    public SpringInterceptorFactory getInterceptorFactory() {
        return interceptorFactory;
    }

    public SpringSphereProperties getProperties() {
        return properties;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
