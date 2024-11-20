package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.annotation.ClientProtocol;
import org.hipeday.sphere.core.network.tcp.TCPClientFactory;

import java.util.Map;

/**
 * 客户端工厂缓存实例
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ClientFactories {

    private static final Map<ClientProtocol, ClientFactory> CLIENT_FACTORY_CACHE = Map.of(
            ClientProtocol.TCP, new TCPClientFactory()
    );

    private ClientFactories() {
    }

    public static ClientFactory getClientFactory(ClientProtocol protocol) {
        ClientFactory clientFactory = CLIENT_FACTORY_CACHE.get(protocol);
        if (clientFactory == null) {
            throw new IllegalArgumentException("Unsupported protocol: " + protocol);
        }
        return clientFactory;
    }
}
