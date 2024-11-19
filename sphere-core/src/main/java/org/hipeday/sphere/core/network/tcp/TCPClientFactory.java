package org.hipeday.sphere.core.network.tcp;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.network.ClientFactory;
import org.hipeday.sphere.core.network.SphereClientConfig;

/**
 * tcp客户端工厂
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class TCPClientFactory<T> implements ClientFactory<T> {

    @Override
    public Client createClient(SphereClientConfig<T> config, SphereConfiguration configuration) {
        return new TCPClient(config, configuration);
    }
}
