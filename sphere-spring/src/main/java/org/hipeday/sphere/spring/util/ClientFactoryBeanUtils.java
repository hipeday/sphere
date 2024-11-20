package org.hipeday.sphere.spring.util;

import org.hipeday.sphere.core.util.StringUtils;
import org.hipeday.sphere.spring.beans.ClientFactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ParserContext;

/**
 * Sphere 接口客户端工厂Bean工具类
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ClientFactoryBeanUtils {

    private final static Class<?> CLIENT_FACTORY_BEAN_CLASS = ClientFactoryBean.class;

    public static void setupClientFactoryBean(AbstractBeanDefinition beanDefinition, String clientClassName) {
        beanDefinition.setBeanClass(CLIENT_FACTORY_BEAN_CLASS);
        beanDefinition.getPropertyValues().add("interfaceClass", clientClassName);
        beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
    }

    public static String getBeanId(String id, Class<?> beanClass, ParserContext parserContext) {
        return getBeanId(id, beanClass, parserContext.getRegistry());
    }

    public static String getBeanId(String id, Class<?> beanClass, BeanDefinitionRegistry registry) {
        if (!StringUtils.hasText(id)) {
            String generatedBeanName = beanClass.getName();
            id = generatedBeanName;
            int counter = 2;
            while (registry.containsBeanDefinition(id)) {
                id = generatedBeanName + (counter++);
            }
        }
        return id;
    }

}
