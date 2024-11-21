package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.context.ApplicationContext;
import org.hipeday.sphere.core.registry.support.NetworkClientRegistry;

/**
 * 抽象 {@linkplain Client 网络客户端} 工厂实现
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractClientFactory implements ClientFactory {

    protected final NetworkClientRegistry networkClientRegistry;

    protected AbstractClientFactory() {
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        this.networkClientRegistry = applicationContext.getNetworkClientRegistry();
    }

    /**
     * 创建网络客户端实例
     *
     * @param config 网络客户端配置
     * @return {@linkplain Client 网络客户端}
     */
    protected abstract Client createInstance(NetworkClientConfig<?> config);

    @Override
    public Client getClient(NetworkClientConfig<?> config) {
        Client client = networkClientRegistry.getInstance(config.clientId());
        if (client == null) {
            synchronized (networkClientRegistry) {
                client = networkClientRegistry.getInstance(config.clientId());
                if (client == null) {
                    client = createInstance(config);
                    networkClientRegistry.register(config.clientId(), client);
                }
            }
        }
        return client;
    }
}
