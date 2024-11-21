package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.interceptor.support.DefaultInterceptorFactory;
import org.hipeday.sphere.core.interceptor.InterceptorFactory;
import org.hipeday.sphere.core.listener.ListenerFactory;
import org.hipeday.sphere.core.listener.support.DefaultListenerFactory;
import org.hipeday.sphere.core.registry.support.FunctionRegistry;
import org.hipeday.sphere.core.registry.support.InterceptorChainRegistry;
import org.hipeday.sphere.core.registry.support.InterceptorRegistry;
import org.hipeday.sphere.core.registry.support.InterfaceClientRegistry;
import org.hipeday.sphere.core.registry.support.ListenerChainRegistry;
import org.hipeday.sphere.core.registry.support.ListenerRegistry;
import org.hipeday.sphere.core.registry.support.NetworkClientRegistry;
import org.hipeday.sphere.core.registry.support.SphereContextRegistry;

/**
 * 应用程序上下文
 *
 * @author jixiangup
 * @since 1.0.0
 */
public final class ApplicationContext {

    /**
     * 网络客户端注册表
     */
    private static final NetworkClientRegistry NETWORK_CLIENT_REGISTRY = new NetworkClientRegistry();

    /**
     * 拦截器注册表
     */
     private static final InterceptorRegistry INTERCEPTOR_REGISTRY = new InterceptorRegistry();

    /**
     * 拦截器调用链注册表
     */
     private static final InterceptorChainRegistry INTERCEPTOR_CHAIN_REGISTRY = new InterceptorChainRegistry();

    /**
     * 接口客户端注册表
     */
    private static final InterfaceClientRegistry INTERFACE_CLIENT_REGISTRY = new InterfaceClientRegistry();

    /**
     * 函数注册表
     */
     private static final FunctionRegistry FUNCTION_REGISTRY = new FunctionRegistry();

    /**
     * 函数上下文注册表
     */
     private static final SphereContextRegistry SPHERE_CONTEXT_REGISTRY = new SphereContextRegistry();

    /**
     * 监听器调用链注册表
     */
    private static final ListenerChainRegistry LISTENER_CHAIN_REGISTRY = new ListenerChainRegistry();

    /**
     * 监听器注册表
     */
    private static final ListenerRegistry LISTENER_REGISTRY = new ListenerRegistry();

    /**
     * 拦截器工厂实例
     */
    private static InterceptorFactory INTERCEPTOR_FACTORY = new DefaultInterceptorFactory();

    /**
     * 监听器工厂实例
     */
    private static ListenerFactory LISTENER_FACTORY = new DefaultListenerFactory();

    /**
     * 应用程序上下文持有者
     */
    private static final class ApplicationContextHolder {
        private static final ApplicationContext INSTANCE = new ApplicationContext();
    }

    /**
     * 获取应用程序上下文
     *
     * @return {@linkplain ApplicationContext 应用程序上下文}
     */
    public static ApplicationContext getApplicationContext() {
        return ApplicationContextHolder.INSTANCE;
    }

    /**
     * 应用程序上下文应该是单例的全局只有一个
     */
    private ApplicationContext() {
    }

    /**
     * 获取网络客户端注册表
     *
     * @return {@linkplain NetworkClientRegistry 网络客户端注册表}
     */
    public NetworkClientRegistry getNetworkClientRegistry() {
        return NETWORK_CLIENT_REGISTRY;
    }

    /**
     * 获取拦截器注册表
     *
     * @return {@linkplain InterceptorRegistry 拦截器注册表}
     */
    public InterceptorRegistry getInterceptorRegistry() {
        return INTERCEPTOR_REGISTRY;
    }

    /**
     * 获取拦截器调用链注册表
     *
     * @return {@linkplain InterceptorChainRegistry 拦截器调用链注册表}
     */
    public InterceptorChainRegistry getInterceptorChainRegistry() {
        return INTERCEPTOR_CHAIN_REGISTRY;
    }

    /**
     * 获取接口客户端注册表
     *
     * @return {@linkplain InterfaceClientRegistry 接口客户端注册表}
     */
    public InterfaceClientRegistry getInterfaceClientRegistry() {
        return INTERFACE_CLIENT_REGISTRY;
    }

    /**
     * 获取函数注册表
     *
     * @return {@linkplain FunctionRegistry 函数注册表}
     */
    public FunctionRegistry getFunctionRegistry() {
        return FUNCTION_REGISTRY;
    }

    /**
     * 获取函数上下文注册表
     *
     * @return {@linkplain SphereContextRegistry 函数上下文注册表}
     */
    public SphereContextRegistry getSphereContextRegistry() {
        return SPHERE_CONTEXT_REGISTRY;
    }

    /**
     * 获取拦截器调用链注册表
     *
     * @return {@linkplain ListenerChainRegistry 拦截器上下文注册表}
     */
    public ListenerChainRegistry getListenerChainRegistry() {
        return LISTENER_CHAIN_REGISTRY;
    }

    public InterceptorFactory getInterceptorFactory() {
        return INTERCEPTOR_FACTORY;
    }

    public ListenerRegistry getListenerRegistry() {
        return LISTENER_REGISTRY;
    }

    public void setInterceptorFactory(InterceptorFactory interceptorFactory) {
        INTERCEPTOR_FACTORY = interceptorFactory;
    }

    public ListenerFactory getListenerFactory() {
        return LISTENER_FACTORY;
    }

    public void setListenerFactory(ListenerFactory listenerFactory) {
        LISTENER_FACTORY = listenerFactory;
    }
}
