package org.hipeday.sphere.core.proxy;

import org.hipeday.sphere.core.config.SphereConfiguration;

import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ProxyFactory<T> {

    private final SphereConfiguration configuration;

    private final Class<T> interfaceClass;

    private InterfaceProxyHandler<T> interfaceProxyHandler;

    public ProxyFactory(SphereConfiguration configuration, Class<T> interfaceClass) {
        this.configuration = configuration;
        this.interfaceClass = interfaceClass;
    }

    /**
     * 创建代理实例
     *
     * @return 代理实例
     */
    public T createProxy() {
        T proxy = (T) configuration.getInstanceCache().get(interfaceClass);

        boolean cacheEnabled = configuration.isCacheEnabled();
        if (cacheEnabled && proxy != null) {
            return proxy;
        }

        synchronized (configuration.getInstanceCache()) {
            proxy = (T) configuration.getInstanceCache().get(interfaceClass);
            if (cacheEnabled && proxy != null) {
                return proxy;
            }
            interfaceProxyHandler = new InterfaceProxyHandler<>(configuration, interfaceClass);
            proxy = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass, SphereClientProxy.class}, interfaceProxyHandler);
            if (cacheEnabled) {
                configuration.getInstanceCache().put(interfaceClass, proxy);
            }
        }
        return proxy;
    }
}
