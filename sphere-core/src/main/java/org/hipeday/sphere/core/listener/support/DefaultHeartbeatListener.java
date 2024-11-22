package org.hipeday.sphere.core.listener.support;

import org.hipeday.sphere.core.annotation.Order;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.listener.Listener;
import org.hipeday.sphere.core.logging.SphereLogger;

import java.nio.charset.StandardCharsets;

/**
 * 默认心跳监听器
 *
 * @author jixiangup
 * @since 1.0.0
 */
@Order(Integer.MIN_VALUE)
public class DefaultHeartbeatListener implements Listener {

    private final static SphereLogger log = SphereLogger.getLogger(DefaultHeartbeatListener.class);

    @Override
    public void heartbeat(SphereContext sphereContext, byte[] heartbeatMetadata) {
        log.debug("client {} trigger heartbeat, metadata {}", sphereContext.getClient().clientId(), new String(heartbeatMetadata, StandardCharsets.UTF_8));
    }
}
