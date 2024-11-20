package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.network.Client;

/**
 * tcp客户端上下文
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class TCPClientContext implements SphereContext {

    private final Client client;

    public TCPClientContext(Client client) {
        this.client = client;
    }

    @Override
    public Client getClient() {
        return this.client;
    }

    @Override
    public Session getSession() {
        return this.client.getSession();
    }

    @Override
    public SphereConfiguration getConfiguration() {
        return getClient().getConfiguration();
    }
}
