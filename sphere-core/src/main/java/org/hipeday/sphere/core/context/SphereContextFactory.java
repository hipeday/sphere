package org.hipeday.sphere.core.context;

/**
 * Sphere 函数上下文工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
@FunctionalInterface
public interface SphereContextFactory {

    /**
     * 创建上下文
     *
     * @return SphereContext
     */
    SphereContext createContext();

}
