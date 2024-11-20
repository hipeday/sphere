package org.hipeday.sphere.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 回调函数优先级注解
 *
 * @author jixiangup
 * @since 1.0.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    /**
     * 优先级
     *
     * <p>
     *     数字越小优先级越高
     *     默认值为最低优先级
     *     优先级范围为[Integer.MIN_VALUE, Integer.MAX_VALUE]
     *     优先级相同的情况下，后注册的回调函数优先级高
     *     优先级相同的情况下，回调函数的执行顺序不确定
     * </p>
     *
     * @return 优先级
     */
    int value() default Integer.MAX_VALUE;

}
