package org.hipeday.sphere.core.network.tcp;

import org.hipeday.sphere.core.network.AbstractClientFactory;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.network.NetworkClientConfig;

/**
 * tcp客户端工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class TCPClientFactory extends AbstractClientFactory {

    @Override
    protected Client createInstance(NetworkClientConfig<?> config) {
        return new TCPClient(config);
    }

}
