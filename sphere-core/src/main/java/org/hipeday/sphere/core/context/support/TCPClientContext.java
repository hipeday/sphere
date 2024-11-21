package org.hipeday.sphere.core.context.support;

import org.hipeday.sphere.core.context.AbstractSphereContext;
import org.hipeday.sphere.core.reflection.Function;
import org.hipeday.sphere.core.network.Client;

/**
 * tcp客户端上下文
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class TCPClientContext extends AbstractSphereContext {

    public TCPClientContext(Client client, Function<?> function) {
        super(client, function);
    }
}
