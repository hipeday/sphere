package org.hipeday.sphere.core.config;

import org.hipeday.sphere.core.hook.ShutdownHook;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.proxy.ProxyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sphere配置
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class SphereConfiguration {

    private final static SphereConfiguration INSTANCE = new SphereConfiguration();

    private final static ShutdownHook SHUTDOWN_HOOK = new ShutdownHook();

    /**
     * 请求接口的实例缓存，用于缓存请求接口的实例
     * key: 请求接口的Class对象
     * value: 请求接口的实例
     */
    private final static Map<Class<?>, Object> INSTANCE_CACHE = new ConcurrentHashMap<>();

    /**
     * 网络客户端缓存，用于缓存网络客户端
     * key: 网络客户端的唯一ID {@link org.hipeday.sphere.core.annotation.ClientId()}
     * value: 网络客户端实例
     */
    private final static Map<String, Client> NETWORK_CLIENT_CACHE = new ConcurrentHashMap<>();

    /**
     * 请求接口的代理工厂缓存，用于缓存请求接口的代理工厂
     * key: 请求接口的Class对象
     * value: 请求接口的代理工厂
     */
    private final static Map<Class<?>, ProxyFactory<?>> CLIENT_PROXY_FACTORY_CACHE = new ConcurrentHashMap<>();

    /**
     * 是否启用缓存请求接口的实例
     */
    private Boolean cacheEnabled = true;

    private Boolean networkClientCacheEnabled = true;

    public static SphereConfiguration configuration() {
        Runtime.getRuntime().addShutdownHook(new Thread(SHUTDOWN_HOOK, "SphereShutdownHook"));
        return INSTANCE;
    }

    public Map<String, Client> getNetworkClientCache() {
        return NETWORK_CLIENT_CACHE;
    }

    public Map<Class<?>, Object> getInstanceCache() {
        return INSTANCE_CACHE;
    }

    /**
     * 创建客户端代理工厂
     *
     * @param clazz 客户端接口
     * @return 客户端代理工厂
     * @param <T> 客户端类型
     */
    public <T> ProxyFactory<T> createClient(Class<T> clazz) {
       return (ProxyFactory<T>) CLIENT_PROXY_FACTORY_CACHE.computeIfAbsent(clazz, k -> new ProxyFactory<>(this, clazz));
    }

    public static void cacheNetworkClient(String clientId, Client client) {
        NETWORK_CLIENT_CACHE.put(clientId, client);
        SHUTDOWN_HOOK.addClient(client);
    }

    /**
     * 是否缓存接口实例
     *
     * @return 是否缓存接口实例
     */
    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public Boolean getCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(Boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public boolean isNetworkClientCacheEnabled() {
        return networkClientCacheEnabled;
    }

    public Boolean getNetworkClientCacheEnabled() {
        return networkClientCacheEnabled;
    }

    public void setNetworkClientCacheEnabled(Boolean networkClientCacheEnabled) {
        this.networkClientCacheEnabled = networkClientCacheEnabled;
    }
}
