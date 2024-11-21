package org.hipeday.sphere.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Method 工具类
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class MethodUtils {

    /**
     * 获取 {@linkplain java.lang.reflect.Method Method对象实例} 的指定注解
     *
     * @param annotationClass 注解类
     * @param method          {@linkplain java.lang.reflect.Method Method对象实例}
     * @return 注解对象
     * @param <T> 注释的类型
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationClass, Method method) {
        return method.getAnnotation(annotationClass);
    }

}
