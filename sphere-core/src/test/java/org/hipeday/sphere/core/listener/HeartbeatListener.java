package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.context.SphereContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳监听器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class HeartbeatListener extends DefaultHeartbeatListener {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatListener.class);

    @Override
    public void listener(SphereContext context, byte[] message) {
        // do nothing
        log.info("HeartbeatListener listener {}", new String(message));
    }
}
