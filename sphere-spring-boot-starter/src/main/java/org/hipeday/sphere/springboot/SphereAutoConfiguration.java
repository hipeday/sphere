package org.hipeday.sphere.springboot;

import org.hipeday.sphere.spring.interceptor.SpringInterceptorFactory;
import org.hipeday.sphere.spring.properties.SpringSphereProperties;
import org.hipeday.sphere.spring.reflection.SpringSphereObjectFactory;
import org.hipeday.sphere.springboot.properties.SphereConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sphere 自动装备
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@Configuration
@EnableConfigurationProperties({SphereConfigurationProperties.class})
public class SphereAutoConfiguration {

    @Bean
    public SpringInterceptorFactory sphereInterceptorFactory() {
        return new SpringInterceptorFactory();
    }

    @Bean
    public SpringSphereObjectFactory sphereObjectFactory() {
        return new SpringSphereObjectFactory();
    }

    @Bean
    public SpringSphereProperties sphereProperties() {
        return new SpringSphereProperties();
    }

}
