package org.hipeday.sphere.core.context.support;

import org.hipeday.sphere.core.context.AbstractSphereContextFactory;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.network.NetworkClientConfig;
import org.hipeday.sphere.core.reflection.Function;

/**
 * TCP Sphere 应用上下文工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class TCPSphereContextFactory<T> extends AbstractSphereContextFactory<T> {

    public TCPSphereContextFactory(NetworkClientConfig<T> networkClientConfig, Function<?> function) {
        super(networkClientConfig, function);
    }

    @Override
    public SphereContext createContext() {
        if (sphereContextRegistry.isRegistered(networkClientConfig.clientId())) {
            return sphereContextRegistry.getInstance(networkClientConfig.clientId());
        }
        synchronized (sphereContextRegistry) {
            if (sphereContextRegistry.isRegistered(networkClientConfig.clientId())) {
                return sphereContextRegistry.getInstance(networkClientConfig.clientId());
            }
            // 创建网络客户端
            Client client = createClient();
            TCPClientContext tcpClientContext = new TCPClientContext(client, super.function);
            sphereContextRegistry.register(networkClientConfig.clientId(), tcpClientContext);
            return tcpClientContext;
        }
    }


}
