package org.hipeday.sphere.core.annotation;

import org.hipeday.sphere.core.interceptor.Interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sphere客户端注解
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SphereClient {

    /**
     * 客户端类型
     */
    ClientProtocol protocol();

    /**
     * 拦截器
     */
    Class<? extends Interceptor>[] interceptors() default {};

}
