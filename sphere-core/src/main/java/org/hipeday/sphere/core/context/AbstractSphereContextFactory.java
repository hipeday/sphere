package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.network.ClientFactories;
import org.hipeday.sphere.core.network.ClientFactory;
import org.hipeday.sphere.core.network.NetworkClientConfig;
import org.hipeday.sphere.core.reflection.Function;
import org.hipeday.sphere.core.registry.support.SphereContextRegistry;

/**
 * 抽象 Sphere 应用上下文工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractSphereContextFactory<T> implements SphereContextFactory {

    /**
     * Sphere 函数上下文注册表
     */
    protected final SphereContextRegistry sphereContextRegistry;


    /**
     * 网络客户端配置
     */
    protected final NetworkClientConfig<T> networkClientConfig;

    /**
     * 客户端工厂
     */
    protected final ClientFactory clientFactory;

    protected final Function<?> function;

    /**
     * 构造函数
     *
     * @param networkClientConfig 网络客户端配置
     *                            这里需要网络客户端配置是因为每一个网络客户端 都有自己的配置
     *                            因为不同的服务器可能是TCP也可能是串口等等 并且服务器的地址和端口也不一样
     *                            所以这里需要传入网络客户端配置
     *
     */
    protected AbstractSphereContextFactory(NetworkClientConfig<T> networkClientConfig, Function<?> function) {
        this.networkClientConfig = networkClientConfig;
        this.function = function;
        this.clientFactory = ClientFactories.getClientFactory(networkClientConfig.protocol());
        this.sphereContextRegistry = ApplicationContext.getApplicationContext().getSphereContextRegistry();
    }

    /**
     * 创建网络客户端
     *
     * @return 网络客户端
     */
    protected Client createClient() {
        return clientFactory.getClient(networkClientConfig);
    }

}
