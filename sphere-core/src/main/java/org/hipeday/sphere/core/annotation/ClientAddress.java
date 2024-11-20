package org.hipeday.sphere.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于标注客户端地址
 *
 * @author jixiangup
 * @since 1.0.0
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientAddress {
}
