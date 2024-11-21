package org.hipeday.sphere.core.annotation;

import org.hipeday.sphere.core.interceptor.Interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于指定当前参数作用于心跳指令
 *
 * @author jixiangup
 * @since 1.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SphereFunction {

    /**
     * 拦截器
     */
    Class<? extends Interceptor>[] interceptors() default {};


}
