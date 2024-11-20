package org.hipeday.sphere.core.proxy;

/**
 * Sphere 客户端代理
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface SphereClientProxy<T> {

    InterfaceProxyHandler<T> getProxyHandler();

}
