package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.interceptor.Interceptor;

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
        this.listeners = listeners;
        // TODO 为监听器排序
    }

    @Override
    public void listener(SphereContext context, byte[] message) {
        for (Listener listener : listeners) {
            listener.listener(context, message);
        }
    }

    @Override
    public void activated(SphereContext context) {
        for (Listener listener : listeners) {
            if (listener instanceof Interceptor) {
                listener.activated(context);
            }
        }
    }
}
