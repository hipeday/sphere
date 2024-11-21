package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.callback.OnActive;
import org.hipeday.sphere.core.callback.OnHeartbeat;
import org.hipeday.sphere.core.callback.OnMessage;
import org.hipeday.sphere.core.context.SphereContext;

/**
 * 监听器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Listener extends OnActive, OnHeartbeat, OnMessage {

    @Override
    default void activated(SphereContext context) {
        // do nothing
    }

    @Override
    default void heartbeat(SphereContext sphereContext, byte[] heartbeatMetadata) {
        // do nothing
    }

    @Override
    default void message(SphereContext context, byte[] message) {
        // do nothing
    }

}
