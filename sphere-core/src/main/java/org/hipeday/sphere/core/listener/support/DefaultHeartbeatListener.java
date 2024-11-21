package org.hipeday.sphere.core.listener.support;

import org.hipeday.sphere.core.annotation.ListenerHandler;
import org.hipeday.sphere.core.annotation.Order;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.listener.Listener;

/**
 * 默认心跳监听器
 *
 * @author jixiangup
 * @since 1.0.0
 */
@ListenerHandler
@Order(Integer.MIN_VALUE)
public class DefaultHeartbeatListener implements Listener {

    @Override
    public void listener(SphereContext context, byte[] message) {
        // do nothing
    }

    @Override
    public void activated(SphereContext context) {

    }
}
