package org.hipeday.sphere.core.listener.support;

import org.hipeday.sphere.core.annotation.ListenerHandler;
import org.hipeday.sphere.core.annotation.Order;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.listener.Listener;

/**
 * 默认连接成功监听器
 *
 * @author jixiangup
 * @since 1.0.0
 */
@ListenerHandler
@Order(Integer.MIN_VALUE)
public class DefaultConnectedListener implements Listener {

    @Override
    public void listener(SphereContext context, byte[] message) {
        // do nothing
    }

    @Override
    public void activated(SphereContext context) {

    }
}
