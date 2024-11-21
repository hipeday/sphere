package org.hipeday.sphere.core.annotation;

import org.hipeday.sphere.core.interceptor.Interceptor;
import org.hipeday.sphere.core.listener.Listener;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sphere客户端注解
 *
 * @author jixiangup
 * @since 1.0.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SphereClient {

    /**
     * 客户端协议
     */
    ClientProtocol protocol();

    /**
     * 拦截器
     */
    Class<? extends Interceptor>[] interceptors() default {};

    /**
     * 监听器
     */
    Class<? extends Listener>[] listeners() default {};


}
