package org.hipeday.sphere.core.constants;

/**
 * 应用程序上下文常量
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface ApplicationContextConstants {

    /**
     * 拦截器注册表名称
     */
    String INTERCEPTOR_REGISTRY_NAME = "interceptor-registry";

    /**
     * 网络客户端注册表名称
     */
    String NETWORK_CLIENT_REGISTRY_NAME = "network-client-registry";

    /**
     * 拦截器调用链注册表名称
     */
    String INTERCEPTOR_CHAIN_REGISTRY_NAME = "interceptor-chain-registry";

    /**
     * Sphere 函数注册表名称
     */
    String FUNCTION_REGISTRY_NAME = "function-registry";

    /**
     * 接口客户端注册表名称
     */
    String INTERFACE_CLIENT_REGISTRY_NAME = "interface-client-registry";

    /**
     * Sphere 函数上下文注册表名称
     */
    String SPHERE_CONTEXT_REGISTRY_NAME = "sphere-context-registry";

    /**
     * 监听器调用链注册表名称
     */
    String LISTENER_CHAIN_REGISTRY_NAME = "listener-chain-registry";

    /**
     * 监听器注册表名称
     */
    String LISTENER_REGISTRY_NAME = "listener-registry";


}
