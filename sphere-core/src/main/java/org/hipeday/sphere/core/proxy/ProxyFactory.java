package org.hipeday.sphere.core.proxy;

import org.hipeday.sphere.core.context.ApplicationContext;
import org.hipeday.sphere.core.registry.support.InterfaceClientRegistry;

import java.lang.reflect.Proxy;

/**
 * 代理对象工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ProxyFactory<T> {

    private final Class<T> interfaceClass;
    private final InterfaceClientRegistry interfaceClientRegistry;

    public ProxyFactory(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        interfaceClientRegistry = applicationContext.getInterfaceClientRegistry();
    }

    /**
     * 创建代理实例
     *
     * @return 代理实例
     */
    @SuppressWarnings("unchecked")
    public T createProxy() {
        T proxy = (T) interfaceClientRegistry.getInstance(interfaceClass.getName());
        if (proxy == null)  {
            synchronized (interfaceClientRegistry) {
                proxy = (T) interfaceClientRegistry.getInstance(interfaceClass.getName());
                if (proxy == null) {
                    InterfaceProxyHandler<T> interfaceProxyHandler = new InterfaceProxyHandler<>(interfaceClass);
                    proxy = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass, SphereClientProxy.class}, interfaceProxyHandler);
                    interfaceClientRegistry.register(interfaceClass.getName(), proxy);
                }
            }
        }
        return proxy;
    }
}
