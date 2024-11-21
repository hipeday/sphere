package org.hipeday.sphere.core.util;

import java.lang.annotation.Annotation;

/**
 * Class 工具类
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ClassUtils {

    /**
     * 获取字节码对象的指定注解
     *
     * @param annotationClass 注解类
     * @param clazz          字节码对象
     * @return 注解对象
     * @param <T> 注释的类型
     */
    public static <T extends Annotation> T getAnnotation(Class<T> annotationClass, Class<?> clazz) {
        return clazz.getAnnotation(annotationClass);
    }

}
