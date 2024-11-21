package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 监听器调用链
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ListenerChain implements Listener {

    private final List<Listener> listeners;

    public ListenerChain(List<Listener> listeners) {
        if (CollectionUtils.isEmpty(listeners)) {
            this.listeners = Collections.emptyList();
        } else {
            this.listeners = listeners;
        }
    }

    @Override
    public void heartbeat(SphereContext sphereContext, byte[] heartbeatMetadata) {
        for (Listener listener : listeners) {
            listener.heartbeat(sphereContext, heartbeatMetadata);
        }
    }

    @Override
    public void activated(SphereContext context) {
        for (Listener listener : listeners) {
            listener.activated(context);
        }
    }
}
