package org.hipeday.sphere.core.reflection;

/**
 * @author jixiangup
 * @since 1.1.0.20
 */
@FunctionalInterface
public interface ObjectConstructor<T> {

    /**
     * 构造对象
     *
     * @return 对象
     */
    T construct();
}
