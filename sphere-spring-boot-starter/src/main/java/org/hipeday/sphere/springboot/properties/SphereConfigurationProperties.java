package org.hipeday.sphere.springboot.properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * Sphere 自动装配配置文件属性
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConfigurationProperties(prefix = "sphere", ignoreInvalidFields = true)
public class SphereConfigurationProperties {

}
